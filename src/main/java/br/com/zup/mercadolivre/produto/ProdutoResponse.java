package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.caracteristica.CaracteristicaProduto;
import br.com.zup.mercadolivre.caracteristica.CaracteristicaProdutoResp;
import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.imagem.ImagemProduto;
import br.com.zup.mercadolivre.opiniao.OpiniaoProduto;
import br.com.zup.mercadolivre.pergunta.PerguntaProduto;
import br.com.zup.mercadolivre.security.usuarios.UsuarioResp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProdutoResponse {

    private Long id;
    private String nome;
    private Integer quantidade;
    private BigDecimal valor;
    private String descricao;
    private Categoria categoria;
    private UsuarioResp usuario;
    private List<CaracteristicaProduto> caracteristicas = new ArrayList<>();
    private List<ImagemProduto> imagens = new ArrayList();
    private List<OpiniaoProduto> opinioes = new ArrayList<>();
    private List<PerguntaProduto> perguntas = new ArrayList<>();

    double totalDasNotas;
    double qtdNotas;
    double mediaNotas;

    public ProdutoResponse(Produto produto) {

        this.id = produto.getId();
        this.nome = produto.getNome();
        this.quantidade = produto.getQuantidade();
        this.valor = produto.getValor();
        this.descricao = produto.getDescricao();
        this.categoria = produto.getCategoria();
        this.usuario = new UsuarioResp(produto.getUsuario());

        caracteristicas =  produto.getCaracteristicasProduto();
        imagens = produto.getImagensProduto();
        opinioes = produto.getOpinioes();
        perguntas = produto.getPerguntas();

        Map<String, Double> detalhes = produto.getDetalhesDoProduto();

        totalDasNotas = detalhes.get("totalDasNotas");
        qtdNotas = detalhes.get("qtdNotas");
        mediaNotas = detalhes.get("mediaNotas");

    }

}
