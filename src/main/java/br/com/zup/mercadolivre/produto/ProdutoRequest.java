package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.caracteristica.CaracteristicaProduto;
import br.com.zup.mercadolivre.caracteristica.CaracteristicaProdutoRequest;
import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.security.usuarios.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public class ProdutoRequest {


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
    private Long categoria;
    @Size(min = 3, message = "defina pelo menos 3 caracteristicas")
    private List<CaracteristicaProdutoRequest> caracteristicaProdutoRequest = new ArrayList<>();
    private Usuario usuario;

    public ProdutoRequest( String nome, Integer quantidade,
                          BigDecimal valor,
                          String descricao, Long categoria,
                          List<CaracteristicaProdutoRequest> caracteristicas) {

        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicaProdutoRequest.addAll(caracteristicas);
    }


    public Produto toModel( EntityManager em, Usuario usuario) {
        //encontra uma categoria no db, compatível com o id passado
        Categoria categoriaEncontrada = em.find(Categoria.class, categoria);
        Produto produto = new Produto(
        this.nome,
        this.quantidade,
        this.valor,
        this.descricao,
        categoriaEncontrada,
        usuario
        );

        List<CaracteristicaProduto> lista = this.caracteristicaProdutoRequest.stream().map(cpr -> new CaracteristicaProduto(produto, cpr.getNome(), cpr.getDescricao())).collect(Collectors.toList());
        produto.setCaracteristicasProduto(lista);
        return produto;
    }

    public List<String> retornaCaracteristicasRepetidas(){
        HashSet<String> nomes = new HashSet<>();
        List<String> repetidas = new ArrayList<>();
        for(CaracteristicaProdutoRequest carac: caracteristicaProdutoRequest){
            if(!nomes.add(carac.getNome())){
                repetidas.add(carac.getNome());
            }
        }
        return repetidas;
    }
}
