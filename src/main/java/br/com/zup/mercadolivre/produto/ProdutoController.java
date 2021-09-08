package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.repository.ProdutoRepository;
import br.com.zup.mercadolivre.security.usuarios.Usuario;
import br.com.zup.mercadolivre.validacao.NomeRepetidoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;
    @Autowired
    EntityManager em;

    @InitBinder
    public void iniciaComController(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new NomeRepetidoValidator());
    }

    /**
     *
     * @param produtoRequest
     * @return
     * Para testar este método, é necessário antes, fazer login no sistema
     * usando a uri apropriada (localhost:8080/auth), em seguida, fazer a inclusão do produto
     * Isso é necessário, porque o usuario que está sendo vinculado ao produto
     * na qualidade de dono, é recuparado do usuário atualmente logado
     */
    @PostMapping
    public ResponseEntity<ProdutoResponse> insere(@RequestBody @Valid ProdutoRequest produtoRequest){
        Usuario usuario  = (Usuario) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
             Produto produto  = produtoRequest.toModel(em, usuario);
              Produto produtoSalvo = repository.save(produto);
              ProdutoResponse produtoResponse = new ProdutoResponse(produtoSalvo);
//            URI uri = ucb.path("produto/{id}").buildAndExpand(produtoResponse.getId()).toUri();
            return ResponseEntity.ok(produtoResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> lista(UriComponentsBuilder ucb){
        List<ProdutoResponse> lista = repository.findAll().stream().map(c -> new ProdutoResponse(c) ).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
}
