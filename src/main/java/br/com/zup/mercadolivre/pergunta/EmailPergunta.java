package br.com.zup.mercadolivre.pergunta;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.security.usuario.Usuario;

public class EmailPergunta {

    private PerguntaProduto perguntaProduto;
    private Produto produto;
    private Usuario donoDoProduto;

    public EmailPergunta(Produto produto) {
        this.produto = produto;
        this.perguntaProduto = produto.retornaUltimapergunta();
        this.donoDoProduto = produto.getUsuario();
    }

    public void enviaEmail(){
        String subject = "Envio de pergunta sobre o produdo id: " + this.produto.getId() +
                " - nome: " + this.produto.getNome() + " efetuada em:  " + this.perguntaProduto.getInstanteCriacao();
        String nomeDstinatario = this.donoDoProduto.getNome();
        String emailDestinatario = this.donoDoProduto.getEmail();
        String textoPergunta = this.perguntaProduto.getTítulo();

        System.out.println("Assunto       :  " + subject);
        System.out.println("Destinatario  :  " + nomeDstinatario);
        System.out.println("email         :  " + emailDestinatario);
        System.out.println("pergunta      :  " + textoPergunta);
    }
}
