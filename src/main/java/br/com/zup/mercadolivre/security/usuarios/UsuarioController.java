package br.com.zup.mercadolivre.security.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository repo;

    @PostMapping
    public ResponseEntity<UsuarioResp> insere(@RequestBody @Valid UsuarioReq usuarioReq  , UriComponentsBuilder ucb){
            Usuario usuario  = usuarioReq.toModel();
            Usuario usuarioSalvo = repo.save(usuario);
            UsuarioResp usuarioResp = new UsuarioResp(usuarioSalvo);
            URI uri = ucb.path("usuarios/{id}").buildAndExpand(usuarioResp.getId()).toUri();
            return ResponseEntity.created(uri).body(usuarioResp);
    }
}
