package br.com.zup.mercadolivre.pergunta;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;

public class PerguntaProdutoResponse {

   private Long id;
   private String título;
   private String DataDeCriacao;
   private String usuario;
   private String produto;

   public PerguntaProdutoResponse() {
   }

   public PerguntaProdutoResponse(PerguntaProduto perguntaProduto) {
      this.id = perguntaProduto.getId();
      this.título = perguntaProduto.getTítulo();
      this.DataDeCriacao = perguntaProduto.getInstanteCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
      this.usuario = perguntaProduto.getUsuario().getNome();
      this.produto = perguntaProduto.getProduto().getId().toString() + " - " + perguntaProduto.getProduto().getNome();
   }

   public String getTítulo() {
      return título;
   }
   public String getDataDeCriacao() {
      return DataDeCriacao;
   }
   public String getUsuario() {
      return usuario;
   }
   public String getProduto() {
      return produto;
   }
}
