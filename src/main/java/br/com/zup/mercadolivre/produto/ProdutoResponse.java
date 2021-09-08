package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.caracteristica.CaracteristicaProduto;
import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.security.usuarios.Usuario;
import br.com.zup.mercadolivre.security.usuarios.UsuarioResp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoResponse {

    private Long id;
    private String nome;
    private Integer quantidade;
    private BigDecimal valor;
    private String descricao;
    private Categoria categoria;
    private List<CaracteristicaProduto> caracteristicasProduto  = new ArrayList<CaracteristicaProduto>();
    private UsuarioResp usuarioResp;

    public ProdutoResponse(Produto produto) {

        this.id = produto.getId();
        this.nome = produto.getNome();
        this.quantidade = produto.getQuantidade();
        this.valor = produto.getValor();
        this.descricao = produto.getDescricao();
        this.categoria = produto.getCategoria();
        this.caracteristicasProduto.addAll(produto.getCaracteristicasProduto()) ;
        this.usuarioResp = new UsuarioResp(produto.getUsuario()) ;
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

    public UsuarioResp getUsuarioResp() {
        return usuarioResp;
    }
}
