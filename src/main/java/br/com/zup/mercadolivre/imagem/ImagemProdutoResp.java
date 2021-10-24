package br.com.zup.mercadolivre.imagem;

public class ImagemProdutoResp {
    private Long id;
    private String CaminhoImagem;

    public ImagemProdutoResp() {
    }

    public ImagemProdutoResp(ImagemProduto imagemProduto) {
        CaminhoImagem = imagemProduto.getCaminhoImagem();
    }
}
