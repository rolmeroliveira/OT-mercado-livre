package br.com.zup.mercadolivre.produto;

import java.math.BigDecimal;

public enum TipoMovimentacaoEstoque {
    VENDA_AO_CLIENTE("Quando o cliente inclui um produto no carrinho", new BigDecimal(-1)),
    CACELAMENTO_DE_VENDA_CLIENTE("Quando a venda é cancelada pelo cliente ou por prazo",  new BigDecimal(1)),
    DEVOLUCAO_FEITA_PELO_CLIENTE("Quando o cliente devolve a compra porque não ficou satisfeito", new BigDecimal(1)),
    REENVIO_POR_EXTRAVIO_NA_REMESSA_AO_CLIENTE("Quando a compra não chega ao cliente por falha de logística", new BigDecimal(-1)),

    AQUISICAO_NOSSA("Quando realizamos um aquisição para compor o estoque", new BigDecimal(1)),
    DEVOLUCAO_AO_FORNECEDOR("Quando devolvemos produtos ao fornecedor", new BigDecimal(-1)),
    QUEBRA_AVARIA("Quando uma quantiadde do produto é baixada do estoque porque não tem condições de ser vendida", new BigDecimal(-1)),
    SOBRA_CONTAGGEM_ESTOQUE("Quantidade a ser acrescida ao estoque, porque um SOBRA foi ferificada na contagem", new BigDecimal(1)),
    FALTA_CONTAGEM_ESTOQUE("Quantidade a ser subtraida do estoque, porque um FALTA foi ferificada na contagem", new BigDecimal(-1));

    //O fator de multiplicação vai apontar se a operação aumenta ou diminui o estoque,
    // sem que seja necessário que o dev precise discernir sobre isso

    private String descricao;
    private BigDecimal fatorMultiplicaao;


    private TipoMovimentacaoEstoque(String descricao, BigDecimal fatorMultiplicaao) {

        this.descricao = descricao;
        this.fatorMultiplicaao = fatorMultiplicaao;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getFatorMultiplicaao() {
        return fatorMultiplicaao;
    }
}

