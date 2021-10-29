package br.com.zup.mercadolivre.security.usuario;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class UsuarioUtil {

    public static Usuario getUsuarioLogado (){
        Object userDetails  = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        if( userDetails != "anonymousUser")
            return (Usuario) userDetails;
        else
            return null;
    }

    public static Usuario getUsuarioLogado (Boolean teste){

        Usuario usuario = new Usuario(1L,"Jacozinho", "jacozinho@email.com", "11111111111");
        return usuario;
    }




}
