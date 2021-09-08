package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.caracteristica.CaracteristicaProduto;
import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.security.usuarios.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String nome;
    @Positive
    private Integer quantidade;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotBlank
    @Size(max=1000)
    private String descricao;
    @NotNull
    @OneToOne
    private Categoria categoria;
    @NotNull
    @Size(min = 3)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "produto", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<CaracteristicaProduto> caracteristicasProduto = new ArrayList<>();
    @ManyToOne
    private Usuario usuario;

    public Produto() {
    }

    public Produto(String nome, Integer quantidade, BigDecimal valor,
                   String descricao, Categoria categoria, Usuario usuario
                   )
    {

        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public List<CaracteristicaProduto> getCaracteristicasProduto() {
        return caracteristicasProduto;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setCaracteristicasProduto(List<CaracteristicaProduto> caracteristicasProduto) {
        this.caracteristicasProduto = caracteristicasProduto;
    }
}
