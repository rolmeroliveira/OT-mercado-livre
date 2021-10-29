package br.com.zup.mercadolivre.compra;

import br.com.zup.mercadolivre.Pagamento.Gateway;
import br.com.zup.mercadolivre.security.usuario.Usuario;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class CarrinhoResponse {

    private Long id;
    private Usuario comprador;
    private LocalDateTime momentoAbertura = LocalDateTime.now();
    private List<CarrinhoItemResponse> itemsDoCarrinho;
    private StatusDaCompra statusDaCompra = StatusDaCompra.INICIADA;
    private LocalDateTime momentoEncerramento;
    private Gateway gateway;

    public CarrinhoResponse(Carrinho carrinho) {
        this.id = carrinho.getId();
        this.comprador = carrinho.getComprador();
        this.momentoAbertura = carrinho.getMomentoAbertura();
        this.itemsDoCarrinho = carrinho.getItemsDoCarrinho().stream().map(i -> new CarrinhoItemResponse(i)).collect(Collectors.toList());
        this.statusDaCompra = carrinho.getStatusDaCompra();
        this.momentoEncerramento = carrinho.getMomentoEncerramento();
        this.gateway = carrinho.getGateway();
    }

}
