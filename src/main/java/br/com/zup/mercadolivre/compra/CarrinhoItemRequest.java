package br.com.zup.mercadolivre.compra;

import br.com.zup.mercadolivre.Pagamento.Gateway;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class CarrinhoItemRequest {

    @NotNull
    private Long idProduto;
    @NotNull
    @Positive
    private BigDecimal quantidade;
    @NotNull
    private Gateway gateway;


    public CarrinhoItemRequest() {
    }

    public CarrinhoItemRequest(Long idProduto, BigDecimal quantidade, Gateway gateway) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.gateway = gateway;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public Gateway getGateway() {
        return gateway;
    }
}
