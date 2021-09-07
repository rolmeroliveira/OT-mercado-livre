package br.com.zup.mercadolivre.usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UsuarioResponse {
    private Long id;
    private String login;
    private String dataCadastro;

    public UsuarioResponse(Usuario usuario) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.dataCadastro = usuario.getDataCadastro().format(f).toString();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }
}
