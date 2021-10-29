package br.com.zup.mercadolivre.pergunta;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.security.usuario.Usuario;


public class PerguntaProdutoRequest {

   private String título;
   private Long usuario;
   private Long produto;

   public PerguntaProdutoRequest() {
   }

   public  PerguntaProduto toModel(Usuario usuario, Produto produto) {
      PerguntaProduto perguntaProduto = new PerguntaProduto(
              this.título,
              usuario,
              produto );
      return perguntaProduto;
   }

   public String getTítulo() {
      return título;
   }

   public void setTítulo(String título) {
      this.título = título;
   }

   public Long getUsuario() {
      return usuario;
   }

   public void setUsuario(Long usuario) {
      this.usuario = usuario;
   }

   public Long getProduto() {
      return produto;
   }

   public void setProduto(Long produto) {
      this.produto = produto;
   }
}
