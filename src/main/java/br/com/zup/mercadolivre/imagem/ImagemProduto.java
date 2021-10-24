package br.com.zup.mercadolivre.imagem;

import br.com.zup.mercadolivre.produto.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ImagemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Produto produto;
    private String CaminhoImagem;

    public ImagemProduto() {
    }

    public ImagemProduto(Produto produto, String caminhoImagem) {
        this.produto = produto;
        CaminhoImagem = caminhoImagem;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getCaminhoImagem() {
        return CaminhoImagem;
    }
}
