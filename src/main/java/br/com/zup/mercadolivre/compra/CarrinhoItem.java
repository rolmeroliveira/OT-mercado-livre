package br.com.zup.mercadolivre.compra;

import br.com.zup.mercadolivre.produto.Produto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;


@Entity
public class CarrinhoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private Produto produto;
    @NotNull
    @Positive
    private BigDecimal quantidde;
    @NotNull
    @Positive
    private BigDecimal valorUnitarioProduto;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Carrinho carrinho;

    public CarrinhoItem() {
    }

    public CarrinhoItem(Produto produto, BigDecimal quantidde, BigDecimal valorUnitarioProduto, Carrinho carrinho) {
        this.produto = produto;
        this.quantidde = quantidde;
        this.valorUnitarioProduto = valorUnitarioProduto;
        this.carrinho = carrinho;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public BigDecimal getQuantidde() {
        return quantidde;
    }

    public BigDecimal getValorUnitarioProduto() {
        return valorUnitarioProduto;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }


    @Override
    public String toString() {
        return "CarrinhoItem  " +
                "produto=" + produto.getNome() +
                "| quantidde=" + quantidde +
                "! valorUnitarioProduto=" + valorUnitarioProduto;
    }
}
