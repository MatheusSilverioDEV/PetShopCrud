package br.com.Petshop.petshop.domain.produto;

import jakarta.persistence.*;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int quantidade;
    private float valor;

    @ManyToOne
    @JoinColumn(name = "compra_id") // Certifique-se de que o nome da coluna esteja correto
    private Compra compra;
    public Produto(DadosCadastroProduto dados) {
        this.nome = dados.nome();
        this.valor = dados.valor();
        this.quantidade = dados.quantidade();
    }

    public Produto() {}

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public float getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }



    // Setters
    // ...

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }


    public void atualizaDados(DadosEdicaoProduto dados) {
        this.nome = dados.nome();
        this.valor = dados.valor();
        this.quantidade = dados.quantidade();
    }

    public double calcularValorTotal() {
        return valor * quantidade;
    }
}