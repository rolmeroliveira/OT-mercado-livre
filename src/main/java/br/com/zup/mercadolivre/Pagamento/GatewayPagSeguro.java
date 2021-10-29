package br.com.zup.mercadolivre.Pagamento;

import br.com.zup.mercadolivre.compra.Carrinho;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class GatewayPagSeguro implements GatewayPagamento {

    @Override
    public String gerarPagamento(Long idCarrinho) {
        String uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/pagseguro/{id}")
                .buildAndExpand(idCarrinho)
                .toUriString();
        return String.format("pagseguro.com?returnId=%d&redirectUrl=%s", idCarrinho, uri);
    }


}
