package br.com.zup.mercadolivre.compra;

import br.com.zup.mercadolivre.pergunta.PerguntaProduto;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.security.usuario.Usuario;

import java.util.List;

public class CompraEmailDonoProduto {

    //Destina-se à simulação de envio de email para o dono de um produto
    //por ocasião do início de um processo de compra

    private Carrinho carrinho;

    public CompraEmailDonoProduto(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public void enviaEmail(){
        String subject = "Nova venda de um dos seus produtos está em andamento: ";
        String nomeDstinatario = this.carrinho.getItemsDoCarrinho().get(0).getProduto().getUsuario().getNome();
        String emailDestinatario = this.carrinho.getItemsDoCarrinho().get(0).getProduto().getUsuario().getEmail();
        String momentoAberturaCarrinho = this.carrinho.getMomentoAbertura().toString();

        List<CarrinhoItem> items = carrinho.getItemsDoCarrinho();


        System.out.println("Assunto         :  " + subject);
        System.out.println("Destinatario    :  " + nomeDstinatario);
        System.out.println("email           :  " + emailDestinatario);
        System.out.println("Carrinho Abert  :  " + momentoAberturaCarrinho);
        items.stream().forEach(System.out::println);


    }
}
