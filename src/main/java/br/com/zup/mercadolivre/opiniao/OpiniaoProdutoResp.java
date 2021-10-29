package br.com.zup.mercadolivre.opiniao;

public class OpiniaoProdutoResp {

    private Long id;
    private Integer nota;
    private String título;
    private String descricao;

    public OpiniaoProdutoResp(OpiniaoProduto o) {
        this.id = o.getId();
        this.nota = o.getNota();
        this.título = o.getTítulo();
        this.descricao = o.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTítulo() {
        return título;
    }

    public String getDescricao() {
        return descricao;
    }
}
