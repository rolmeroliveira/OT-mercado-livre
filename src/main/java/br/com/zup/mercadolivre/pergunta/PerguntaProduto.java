package br.com.zup.mercadolivre.pergunta;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.security.usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class PerguntaProduto {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @NotBlank
   private String título;
   private LocalDateTime instanteCriacao = LocalDateTime.now();

   @ManyToOne(fetch = FetchType.LAZY)
   @JsonIgnore
   @NotNull
   private Usuario usuario;

   @ManyToOne(fetch = FetchType.LAZY)
   @JsonIgnore
   @NotNull
   private Produto produto;

   public PerguntaProduto() {
   }

   public PerguntaProduto(String título, Usuario usuario, Produto produto) {
      this.título = título;
      this.usuario = usuario;
      this.produto = produto;
   }

   public Long getId() {
      return id;
   }

   public String getTítulo() {
      return título;
   }

   public LocalDateTime getInstanteCriacao() {
      return instanteCriacao;
   }

   public Usuario getUsuario() {
      return usuario;
   }

   public Produto getProduto() {
      return produto;
   }
}
