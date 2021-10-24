package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.caracteristica.CaracteristicaProduto;
import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.imagem.ImagemProduto;
import br.com.zup.mercadolivre.opiniao.OpiniaoProduto;
import br.com.zup.mercadolivre.pergunta.PerguntaProduto;
import br.com.zup.mercadolivre.security.usuarios.Usuario;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String nome;
    @Positive
    private Integer quantidade;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotBlank
    @Size(max=1000)
    private String descricao;
    @NotNull
    @OneToOne
    private Categoria categoria;
    @NotNull
    @Size(min = 3)
    @OneToMany(mappedBy = "produto")
    private List<CaracteristicaProduto> caracteristicasProduto = new ArrayList<>();
    @ManyToOne
    private Usuario usuario;
    @NotNull
    @Size(min = 1) //Esta validação não dispensa a de cima
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ImagemProduto> imagensProduto = new ArrayList();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<OpiniaoProduto> opinioes = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<PerguntaProduto> perguntas = new ArrayList<>();


    public Produto() {
    }

    public Produto(String nome, Integer quantidade, BigDecimal valor,
                   String descricao, Categoria categoria, Usuario usuario
                   )
    {

        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public List<CaracteristicaProduto> getCaracteristicasProduto() {
        return caracteristicasProduto;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setCaracteristicasProduto(List<CaracteristicaProduto> caracteristicasProduto) {
        this.caracteristicasProduto = caracteristicasProduto;
    }

    public List<String> gravaImagens(List<MultipartFile> imagens){
        List<String> listaNomes = new ArrayList<>();
        String momento = LocalDateTime.now().toString();
        for (MultipartFile file: imagens){
            String nomeFile =  System.getProperty("user.dir").toString() + File.separator +
                    "img-" +  momento.replaceAll(":", "-") + "-" + file.getOriginalFilename();
            Path pathFile =  Paths.get(nomeFile);
            try {
                file.transferTo(pathFile.toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            listaNomes.add(pathFile.toString());
        }
        return listaNomes;
    }

    public void vinculaImagens(List<String> listaDeCaminhosDasImagens){
        for (String  umCaminho : listaDeCaminhosDasImagens) {
            this.imagensProduto.add(new ImagemProduto(this, umCaminho ));
        }
    }

    public void vinculaOpinioes(OpiniaoProduto pergunta){
            this.opinioes.add(pergunta);
    }
    public void incluiPergunta(PerguntaProduto pergunta){
        this.perguntas.add(pergunta);
    }
    public PerguntaProduto retornaUltimapergunta (){
        PerguntaProduto perguntaProduto = this.perguntas.get(this.perguntas.size() -1);
        return perguntaProduto;
    }

    public List<ImagemProduto> getImagensProduto() {
        return imagensProduto;
    }

    public List<OpiniaoProduto> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaProduto> getPerguntas() {
        return perguntas;
    }


    public Map<String, Double> getDetalhesDoProduto(){
        //este map serã usado para retornar os detalhes do produto
        //Total das notas
        //Qtd de notas
        //média das notas
        Map<String, Double> detalhes = new HashMap<>();

        //lista de notas válidas convertidas para double
        List<Double> lista =  opinioes.stream()
                .map(OpiniaoProduto::getNota)
                .filter(Objects::nonNull)
                .map(nota -> (double) nota)
                .collect(Collectors.toList());

        double totalDasNotas = lista.stream().reduce(0.0, Double::sum);
        double qtdNotas = lista.size();
        double mediaNotas = totalDasNotas == 0 || qtdNotas == 0 ? 0 : totalDasNotas / qtdNotas;

        detalhes.put("TotalDasNotas", totalDasNotas);
        detalhes.put("qtdNotas", qtdNotas);
        detalhes.put("mediaNotas", mediaNotas);
        
        return detalhes;
    }



}
