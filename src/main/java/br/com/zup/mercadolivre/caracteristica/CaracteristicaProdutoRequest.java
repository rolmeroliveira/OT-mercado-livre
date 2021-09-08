package br.com.zup.mercadolivre.caracteristica;

public class CaracteristicaProdutoRequest {
    private String nome;
    private String descricao;

    public CaracteristicaProdutoRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }


}
