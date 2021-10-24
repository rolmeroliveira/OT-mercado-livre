package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.caracteristica.CaracteristicaProdutoResp;
import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.imagem.ImagemProdutoResp;
import br.com.zup.mercadolivre.opiniao.OpiniaoProdutoResp;
import br.com.zup.mercadolivre.pergunta.PerguntaProdutoResp;
import br.com.zup.mercadolivre.security.usuarios.UsuarioResp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProdutoResponse {

    private Long id;
    private String nome;
    private Integer quantidade;
    private BigDecimal valor;
    private String descricao;
    private Categoria categoria;
    private UsuarioResp usuario;
    private List<CaracteristicaProdutoResp> caracteristicas = new ArrayList<>();
    private List<ImagemProdutoResp> imagens = new ArrayList();
    private List<OpiniaoProdutoResp> opinioes = new ArrayList<>();
    private List<PerguntaProdutoResp> perguntas = new ArrayList<>();

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

        //converto as lista de model para listas de responses
        caracteristicas = produto.getCaracteristicasProduto().stream().map(c -> new CaracteristicaProdutoResp(c)).collect(Collectors.toList());
        imagens = produto.getImagensProduto().stream().map(i -> new ImagemProdutoResp(i)).collect(Collectors.toList());
        opinioes = produto.getOpinioes().stream().map(o -> new OpiniaoProdutoResp(o)).collect(Collectors.toList());
        perguntas = produto.getPerguntas().stream().map(p -> new PerguntaProdutoResp(p)).collect(Collectors.toList());

        //recupero um map com os detalhes do produto que precisam ser calculados
        Map<String, Double> detalhes = produto.getDetalhesDoProduto();

        //sá um split no map de detalhes do produto, para passar itens separados no Json
        totalDasNotas = detalhes.get("totalDasNotas");
        qtdNotas = detalhes.get("qtdNotas");
        mediaNotas = detalhes.get("mediaNotas");
    }

}
