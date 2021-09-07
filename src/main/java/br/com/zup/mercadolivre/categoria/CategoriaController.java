package br.com.zup.mercadolivre.categoria;

import br.com.zup.mercadolivre.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository repository;
    @Autowired
    EntityManager em;

    @PostMapping
    public ResponseEntity<CategoriaResponse> insere(@RequestBody @Valid CategoriaRequest categoriaRequest, UriComponentsBuilder ucb){
            Categoria categoria  = categoriaRequest.toModel(em);
            Categoria categoriaSalva = repository.save(categoria);
            CategoriaResponse categoriaResponse = new CategoriaResponse(categoriaSalva);
            URI uri = ucb.path("categoria/{id}").buildAndExpand(categoriaResponse.getId()).toUri();
            return ResponseEntity.created(uri).body(categoriaResponse);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> lista(UriComponentsBuilder ucb){
        List<CategoriaResponse> lista = repository.findAll().stream().map(c -> new CategoriaResponse(c) ).collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }
}
