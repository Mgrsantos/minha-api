package com.gapi.minha_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gapi.minha_api.model.Produto;
import com.gapi.minha_api.service.ProdutoService;
import com.gapi.minha_api.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
@ContextConfiguration(classes = {ProdutoController.class, ProdutoControllerTest.TestSecurityConfig.class})
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProdutoService service;

    @MockBean
    private JwtUtil jwtUtil;

    @Configuration
    static class TestSecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
            return http.build();
        }
    }

    @Test
    @WithMockUser
    void deveListarProdutos() throws Exception {
        Produto p = new Produto();
        p.setNome("Notebook");
        p.setPreco(3500.00);

        Mockito.when(service.listarTodos()).thenReturn(List.of(p));

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Notebook"));
    }

    @Test
    @WithMockUser
    void deveCriarProduto() throws Exception {
        Produto produto = new Produto();
        produto.setNome("Mouse");
        produto.setPreco(150.00);

        Produto criado = new Produto();
        criado.setNome("Mouse");
        criado.setPreco(150.00);

        Mockito.when(service.criar(Mockito.any())).thenReturn(criado);

        mockMvc.perform(post("/produtos")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Mouse"));
    }

    @Test
    @WithMockUser
    void deveRetornar404QuandoProdutoNaoExiste() throws Exception {
        Mockito.when(service.buscarPorId(999L)).thenReturn(null);

        mockMvc.perform(get("/produtos/999"))
                .andExpect(status().isNotFound());
    }
}