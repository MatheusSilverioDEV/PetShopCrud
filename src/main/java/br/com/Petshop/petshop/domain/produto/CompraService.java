package br.com.Petshop.petshop.domain.produto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class CompraService {

    private final CompraRepository compraRepository;

    @Autowired
    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    // Método para adicionar produtos à compra
    public void adicionarProduto(Compra compra, Produto produto) {
        compra.adicionarProduto(produto);
    }

    // Método para calcular o valor total da compra
    public double calcularValorTotal(Compra compra) {
        return compra.getValorTotal();
    }

    // Método para salvar a compra no banco de dados
    public void salvarCompra(Compra compra) {
        compraRepository.save(compra);
        compra.setProdutos(new ArrayList<>()); // Limpa os produtos da compra após salvar
    }
}