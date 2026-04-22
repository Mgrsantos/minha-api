package com.gapi.minha_api.service;

import com.gapi.minha_api.model.Produto;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProdutoService {
    private List<Produto> lista = new ArrayList<>();
    private Long proximoId = 1L;

    public List<Produto> listarTodos() { return lista; }

    public Produto buscarPorId(Long id) {
        return lista.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public Produto criar(Produto produto) {
        produto.setId(proximoId++);
        lista.add(produto);
        return produto;
    }

    public Produto atualizar(Long id, Produto novo) {
        Produto produto = buscarPorId(id);
        if (produto != null) {
            produto.setNome(novo.getNome());
            produto.setPreco(novo.getPreco());
        }
        return produto;
    }

    public boolean deletar(Long id) {
        return lista.removeIf(p -> p.getId().equals(id));
    }
}