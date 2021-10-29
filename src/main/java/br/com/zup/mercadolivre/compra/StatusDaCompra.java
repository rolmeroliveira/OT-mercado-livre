package br.com.zup.mercadolivre.compra;

public enum StatusDaCompra {
    INICIADA("Produtos estão sendo inseridos. Não ocorreu pagamento ainda"),
    PAGA("Produtos já toram pagos, aguardam por remessa"),
    EXPEDIDA("Rematida para entrega"),
    ENTREGUE("Comprador já recebu a compra");

    String descricao;

    private StatusDaCompra(String descricao){
        this.descricao = descricao;
    };

    public String getDescricao() {
        return descricao;
    }
}
