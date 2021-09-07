package br.com.zup.mercadolivre.usuario;

import br.com.zup.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @PostMapping
    public ResponseEntity<UsuarioResponse> insere(@RequestBody @Valid UsuarioRequest usuarioRequest, UriComponentsBuilder ucb){
            Usuario usuario  = usuarioRequest.toModel();
            Usuario usuarioSalvo = repository.save(usuario);
            UsuarioResponse usuarioResp = new UsuarioResponse(usuarioSalvo);
            URI uri = ucb.path("usuarios/{id}").buildAndExpand(usuarioResp.getId()).toUri();
            return ResponseEntity.created(uri).body(usuarioResp);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> lista( UriComponentsBuilder ucb){
        List<UsuarioResponse> lista = repository.findAll().stream().map(u -> new UsuarioResponse(u) ).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }
}
