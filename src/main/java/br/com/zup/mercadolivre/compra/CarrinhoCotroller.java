package br.com.zup.mercadolivre.compra;

import br.com.zup.mercadolivre.Pagamento.Gateway;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.TipoMovimentacaoEstoque;
import br.com.zup.mercadolivre.repository.CarrinhoRepository;
import br.com.zup.mercadolivre.repository.ProdutoRepository;
import br.com.zup.mercadolivre.security.usuario.Usuario;
import br.com.zup.mercadolivre.security.usuario.UsuarioUtil;
import br.com.zup.mercadolivre.validacao.CustomNotFoundException;
import br.com.zup.mercadolivre.validacao.GeneralBusinesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoCotroller {


    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    CarrinhoRepository carrinhoRepository;

    @PostMapping("/compraitem")
    public String compraItem(@RequestBody @Valid CarrinhoItemRequest request){

        //Se tivermos um usuário logado, ele vai ser usado pra criar o carrinho
        Usuario usuarioLogado = Optional.ofNullable(UsuarioUtil.getUsuarioLogado(true)).orElseThrow(()->
                new CustomNotFoundException("Usuário", "Não há um usuário logado"));

        //checa se o produto existe
        Produto produto = produtoRepository.findById(request.getIdProduto()).orElseThrow(() ->
                new CustomNotFoundException("Produto", "Não foi possível identificar essa o produto"));

        //checa se o produto está disponível - estoque
        if(request.getQuantidade().compareTo(produto.getQuantidade()) < -1){
            new GeneralBusinesException("Produto", "Não foi possível identificar essa o produto");
        }

        //Obtém um carrinho
        Carrinho carrinho = retornaCarrinho(usuarioLogado, request.getGateway());

        //Cra o item que o para ser vendido
        CarrinhoItem carrinhoItem = new CarrinhoItem(produto,request.getQuantidade(),produto.getValor(),carrinho);

        //Adiciona o item ao carrinho
        carrinho.adicionaItem(carrinhoItem);

        //Diminui o estoque
        produto.alteraEstoque(request.getQuantidade(), TipoMovimentacaoEstoque.VENDA_AO_CLIENTE);

        //persiste a inclusão de itens no carrinho
        carrinhoRepository.save(carrinho);

        //Envia email para o dono do produto
        CompraEmailDonoProduto email = new CompraEmailDonoProduto(carrinho);
        email.enviaEmail();

        //Redireciona para o gatway de pagemanto
        return carrinho.getGateway().getGatewayPagamento().gerarPagamento(carrinho.getId());
    }


    //Retornar um carrinho ainda nõa encerrado, ou seja, uma compra me andamento
    //se não houver carrinho aberto (compra em andamento), retorna um novo carrinho
    private Carrinho retornaCarrinho(Usuario usuario, Gateway gateway){
        //Checa se tem um carrinho aberto, se não encontrar abre um carrinho
        Carrinho carrinho = carrinhoRepository.findFirstByCompradorAndStatusDaCompra(usuario, StatusDaCompra.INICIADA).orElse(
               carrinhoRepository.save(new Carrinho(usuario, gateway))
        );
        return carrinho;
    }

}
