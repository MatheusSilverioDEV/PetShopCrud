    package br.com.Petshop.petshop.domain.produto;

    import jakarta.persistence.*;
    import java.util.ArrayList;
    import java.util.List;

    @Entity
    @Table(name = "compra")
    public class Compra {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Produto> produtos;

        private double valorTotal;

        public Compra() {
            produtos = new ArrayList<>();
            valorTotal = 0.0;
        }

        public List<Produto> getProdutos() {
            return produtos;
        }

        public void setProdutos(List<Produto> produtos) {
            this.produtos = produtos;
        }

        public double getValorTotal() {
            return valorTotal;
        }

        public void setValorTotal(double valorTotal) {
            this.valorTotal = valorTotal;
        }

        public void adicionarProduto(Produto produto) {
            produtos.add(produto);
            valorTotal += produto.calcularValorTotal();
        }

        public void removerProduto(Produto produto) {
            produtos.remove(produto);
            valorTotal -= produto.calcularValorTotal();
        }

        @Override
        public String toString() {
            return "Compra{" +
                    "id=" + id +
                    ", produtos=" + produtos +
                    ", valorTotal=" + valorTotal +
                    '}';
        }




    }