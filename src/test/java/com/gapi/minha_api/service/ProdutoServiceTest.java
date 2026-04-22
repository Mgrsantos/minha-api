package com.gapi.minha_api.service;

import com.gapi.minha_api.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoServiceTest {

    private ProdutoService service;

    @BeforeEach
    void setUp() {
        service = new ProdutoService();
    }

    @Test
    void deveCriarProdutoComSucesso() {
        Produto produto = new Produto();
        produto.setNome("Notebook");
        produto.setPreco(3500.00);

        Produto criado = service.criar(produto);

        assertNotNull(criado.getId());
        assertEquals("Notebook", criado.getNome());
        assertEquals(3500.00, criado.getPreco());
    }

    @Test
    void deveListarTodosProdutos() {
        Produto p1 = new Produto();
        p1.setNome("Mouse");
        p1.setPreco(150.00);

        Produto p2 = new Produto();
        p2.setNome("Teclado");
        p2.setPreco(300.00);

        service.criar(p1);
        service.criar(p2);

        List<Produto> lista = service.listarTodos();

        assertEquals(2, lista.size());
    }

    @Test
    void deveBuscarProdutoPorId() {
        Produto produto = new Produto();
        produto.setNome("Monitor");
        produto.setPreco(1200.00);

        Produto criado = service.criar(produto);
        Produto encontrado = service.buscarPorId(criado.getId());

        assertNotNull(encontrado);
        assertEquals("Monitor", encontrado.getNome());
    }

    @Test
    void deveRetornarNullQuandoProdutoNaoExiste() {
        Produto encontrado = service.buscarPorId(999L);
        assertNull(encontrado);
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        Produto produto = new Produto();
        produto.setNome("Headset");
        produto.setPreco(500.00);
        Produto criado = service.criar(produto);

        Produto novo = new Produto();
        novo.setNome("Headset Gamer");
        novo.setPreco(750.00);

        Produto atualizado = service.atualizar(criado.getId(), novo);

        assertEquals("Headset Gamer", atualizado.getNome());
        assertEquals(750.00, atualizado.getPreco());
    }

    @Test
    void deveDeletarProdutoComSucesso() {
        Produto produto = new Produto();
        produto.setNome("Webcam");
        produto.setPreco(200.00);
        Produto criado = service.criar(produto);

        boolean deletado = service.deletar(criado.getId());

        assertTrue(deletado);
        assertNull(service.buscarPorId(criado.getId()));
    }

    @Test
    void deveRetornarFalseAoDeletarProdutoInexistente() {
        boolean deletado = service.deletar(999L);
        assertFalse(deletado);
    }
}