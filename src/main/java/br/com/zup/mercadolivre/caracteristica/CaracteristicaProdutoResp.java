package br.com.zup.mercadolivre.caracteristica;

public class CaracteristicaProdutoResp {
    private Long id;
    private String nome;
    private String descricao;

    public CaracteristicaProdutoResp(CaracteristicaProduto c) {
        this.id = c.getId();
        this.nome = c.getNome();
        this.descricao = c.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}