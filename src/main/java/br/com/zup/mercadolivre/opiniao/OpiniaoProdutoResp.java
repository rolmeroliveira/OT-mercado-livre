package br.com.zup.mercadolivre.opiniao;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.security.usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
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
