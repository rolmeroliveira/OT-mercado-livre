package br.com.zup.mercadolivre.produto;

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
}
