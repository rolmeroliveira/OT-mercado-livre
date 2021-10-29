package br.com.zup.mercadolivre.compra;
import java.math.BigDecimal;


public class CarrinhoItemResponse {

    private Long id;
    private String nomeProduto;
    private String descricaoProduto;
    private BigDecimal quantidde;
    private BigDecimal valorUnitarioProduto;
    private BigDecimal valorTotalItem;

    public CarrinhoItemResponse(CarrinhoItem carrinhoItem) {
        this.id = carrinhoItem.getId();
        this.nomeProduto = carrinhoItem.getProduto().getNome();
        this.descricaoProduto = carrinhoItem.getProduto().getDescricao();
        this.quantidde = carrinhoItem.getQuantidde();
        this.valorUnitarioProduto = carrinhoItem.getValorUnitarioProduto();
        this.valorTotalItem = carrinhoItem.getQuantidde().multiply(carrinhoItem.getValorUnitarioProduto()) ;

    }
}
