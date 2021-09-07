package br.com.zup.mercadolivre.categoria;

import br.com.zup.mercadolivre.validacao.ValorExclusivo;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank
    @ValorExclusivo(domainClass = Categoria.class, fieldName = "nome", message = "nome não pode se repetir")
    private String nome;
    private Long categoriaMae;

    public Categoria toModel(EntityManager em){
        Assert.hasText(nome, "Nome inválido");
        Categoria categoriaEncontrada = null;
        if (categoriaMae != null){
            categoriaEncontrada = em.find(Categoria.class, categoriaMae);
            Assert.notNull(categoriaEncontrada, "O id da categoria mae precisa ser válido.");
        }

        Categoria categoria = new Categoria(nome, categoriaEncontrada);
        return categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCategoriaMae(Long categoriaMae) {
        this.categoriaMae = categoriaMae;
    }
}
