package br.com.zup.mercadolivre.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class UsuarioRequest {


    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Size(min=8)
    private String senha;

    public Usuario toModel(){
        return new Usuario(this.login, new BCryptPasswordEncoder().encode(this.senha));
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
