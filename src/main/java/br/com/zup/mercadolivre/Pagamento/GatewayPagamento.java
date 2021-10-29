package br.com.zup.mercadolivre.Pagamento;

import br.com.zup.mercadolivre.compra.Carrinho;

public interface GatewayPagamento {

    String gerarPagamento(Long idCarrinho);
}
