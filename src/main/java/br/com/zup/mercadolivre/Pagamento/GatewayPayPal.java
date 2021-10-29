package br.com.zup.mercadolivre.Pagamento;

import br.com.zup.mercadolivre.compra.Carrinho;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class GatewayPayPal implements GatewayPagamento {

    @Override
    public String gerarPagamento(Long idCarrinho) {
        String uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/paypal/{id}")
                .buildAndExpand(idCarrinho)
                .toUriString();
        return String.format("paypal.com?buyerId=%d&redirectUrl=%s", idCarrinho, uri);
    }

}
