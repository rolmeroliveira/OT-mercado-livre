package br.com.zup.mercadolivre.usuario;

import br.com.zup.mercadolivre.validacao.ValorExclusivo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class UsuarioRequest {

    @NotBlank
    @Email
    @ValorExclusivo(domainClass = Usuario.class, fieldName = "login", message = "email não pode se repetir")
    private String login;

    @NotBlank
    @Size(min=8)
    private String senha;

    public Usuario toModel(){
        Assert.hasText(login, "Login inválido");
        return new Usuario(this.login, new BCryptPasswordEncoder().encode(this.senha));
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
