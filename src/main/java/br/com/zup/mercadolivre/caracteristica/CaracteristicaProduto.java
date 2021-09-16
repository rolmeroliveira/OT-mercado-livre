package br.com.zup.mercadolivre.caracteristica;

import br.com.zup.mercadolivre.produto.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;

@Entity
public class CaracteristicaProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Produto produto;
    private String nome;
    private String descricao;

    public CaracteristicaProduto() {
    }

    public CaracteristicaProduto(Produto produto, String nome, String descricao) {
        this.produto = produto;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
