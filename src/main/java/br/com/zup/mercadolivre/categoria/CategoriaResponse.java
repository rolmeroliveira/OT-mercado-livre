package br.com.zup.mercadolivre.categoria;

public class CategoriaResponse {
    private Long id;
    private String nome;
    private Categoria categoriaMae;

    public CategoriaResponse(Categoria model) {
        this.id = model.getId();
        this.nome = model.getNome();
        this.categoriaMae = model.getCategoriaMae();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }

}
