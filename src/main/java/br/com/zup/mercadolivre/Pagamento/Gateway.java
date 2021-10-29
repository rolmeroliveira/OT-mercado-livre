package br.com.zup.mercadolivre.Pagamento;

public enum Gateway {

    PAGSEGURO(new GatewayPagSeguro()),
    PAYPAL(new GatewayPayPal());

    private GatewayPagamento gatewayPagamento;

    Gateway(GatewayPagamento gatewayPagamento) {
        this.gatewayPagamento = gatewayPagamento;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

}
