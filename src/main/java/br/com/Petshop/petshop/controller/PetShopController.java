package br.com.Petshop.petshop.controller;

import br.com.Petshop.petshop.domain.produto.ProdutoRepository;
import br.com.Petshop.petshop.domain.produto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/petshop")
public class PetShopController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private CompraService compraService;

    @GetMapping
    public String carregaPaginaDeCompra(Long id, Model model) {
        if (id != null) {
            var produto = produtoRepository.findById(id).orElse(null);
            model.addAttribute("produto", produto);
            return "petshop/editar";
        }

        Compra compra = (Compra) model.getAttribute("compra");
        if (compra != null) {
            model.addAttribute("lista", compra.getProdutos());
        } else {
            List<Produto> produtos = produtoRepository.findAll();
            model.addAttribute("lista", produtos);
            model.addAttribute("compra", new Compra());
        }

        model.addAttribute("produto", new Produto());

        return "petshop/compras";
    }

    @PutMapping("/editar-produto")
    public String editaProduto(DadosEdicaoProduto dados) {
        var produto = produtoRepository.findById(dados.id()).orElse(null);
        if (produto != null) {
            produto.atualizaDados(dados);
            produtoRepository.save(produto);
        }
        return "redirect:/petshop";
    }

    @PostMapping
    public String cadastraProduto(@ModelAttribute Produto produto, Model model) {
        Compra compra = (Compra) model.getAttribute("compra");
        compra.adicionarProduto(produto);


        List<Produto> produtos = produtoRepository.findAll();
        model.addAttribute("lista", produtos);

        return "redirect:/petshop"; // Redirecionar de volta para a página "compras.html"
    }
    @Transactional
    @DeleteMapping("/produto/{id}")
    public String removeProduto(@PathVariable("id") Long id) {
        produtoRepository.deleteAssociationsByProdutoId(id);
        produtoRepository.deleteById(id);
        return "redirect:/petshop";
    }
    @GetMapping("/editar-produto")
    public String carregaPaginaDeEdicao(@RequestParam Long id, Model model) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        model.addAttribute("produto", produto);
        return "petshop/editar";
    }

    @PostMapping("/fechar-compra")
    public String fecharCompra(Model model) {
        Compra compra = (Compra) model.getAttribute("compra");

        if (compra != null && !compra.getProdutos().isEmpty()) {
            double valorTotal = 0.0;
            for (Produto produto : compra.getProdutos()) {
                valorTotal += produto.getValor() * produto.getQuantidade();
            }
            compra.setValorTotal(valorTotal);

            compraService.salvarCompra(compra); // Salve a compra no banco de dados
            model.addAttribute("compra", new Compra()); // Crie uma nova instância de Compra

            System.out.println("Compra finalizada com sucesso!");
            System.out.println(compra);
            return "redirect:/petshop/registros"; // Redirecione para a página "registros.html"
        }

        System.out.println("Carrinho vazio");
        System.out.println(compra);

        return "redirect:/petshop"; // Redirecione de volta para a página "compras.html"
    }

    @GetMapping("/registros")
    public String carregaPaginaDeRegistros(Model model) {
        List<Compra> compras = compraRepository.findAll();
        model.addAttribute("compras", compras);
        return "petshop/registros";
    }
}