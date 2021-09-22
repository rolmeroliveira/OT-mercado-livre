package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.security.usuarios.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class OpinaoProdutoRequest {

    @Min(1)
    @Max(5)
    @NotNull
    private Integer nota;

    @NotBlank
    private String título;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    public OpinaoProdutoRequest() {
    }

    public OpiniaoProduto toModel(Usuario usuario, Produto produto) {
        OpiniaoProduto pergunta = new OpiniaoProduto(
            this.nota,
            this.título,
            this.descricao,
            usuario,
            produto
        );

        return pergunta;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public void setTítulo(String título) {
        this.título = título;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
