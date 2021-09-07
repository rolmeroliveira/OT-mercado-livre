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
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    PerfilRepo repo;

    @PostMapping
    public ResponseEntity<PerfilResp> insere(@RequestBody @Valid PerfilReq perfilReq, UriComponentsBuilder ucb){
        Perfil perfil  = perfilReq.toModel();
        Perfil perfilSalvo = repo.save(perfil);
        PerfilResp perfilResp = new PerfilResp(perfilSalvo);
        URI uri = ucb.path("perfis/{id}").buildAndExpand(perfilResp.getId()).toUri();
        return ResponseEntity.created(uri).body(perfilResp);
    }

    @PostMapping(path = "/concedeperfil")
    public ResponseEntity<PerfilResp> concedeperfil(@RequestBody @Valid PerfilReq perfilReq, UriComponentsBuilder ucb){
        Perfil perfil  = perfilReq.toModel();
        Perfil perfilSalvo = repo.save(perfil);
        PerfilResp perfilResp = new PerfilResp(perfilSalvo);
        URI uri = ucb.path("perfis/{id}").buildAndExpand(perfilResp.getId()).toUri();
        return ResponseEntity.created(uri).body(perfilResp);
    }
}
