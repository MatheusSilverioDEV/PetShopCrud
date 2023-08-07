    package br.com.Petshop.petshop.domain.produto;

    import br.com.Petshop.petshop.domain.produto.Produto;
    import br.com.Petshop.petshop.domain.produto.ProdutoRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import java.util.List;

    @Service
    public class ProdutoService {

        private final ProdutoRepository produtoRepository;

        @Autowired
        public ProdutoService(ProdutoRepository produtoRepository) {
            this.produtoRepository = produtoRepository;
        }

        // Método para obter todos os produtos cadastrados
        public List<Produto> obterTodosProdutos() {
            return produtoRepository.findAll();
        }
    }