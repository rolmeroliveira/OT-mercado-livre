package br.com.zup.mercadolivre.produto;

import br.com.zup.mercadolivre.opiniao.OpinaoProdutoRequest;
import br.com.zup.mercadolivre.opiniao.OpiniaoProduto;
import br.com.zup.mercadolivre.pergunta.EmailPergunta;
import br.com.zup.mercadolivre.pergunta.PerguntaProduto;
import br.com.zup.mercadolivre.pergunta.PerguntaProdutoRequest;
import br.com.zup.mercadolivre.pergunta.PerguntaProdutoResponse;
import br.com.zup.mercadolivre.repository.ProdutoRepository;
import br.com.zup.mercadolivre.security.usuarios.Usuario;
import br.com.zup.mercadolivre.security.usuarios.UsuarioRepository;
import br.com.zup.mercadolivre.validacao.CustomNotFoundException;
import br.com.zup.mercadolivre.validacao.GeneralBusinesException;
import br.com.zup.mercadolivre.validacao.NomeRepetidoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    Logger logger = LoggerFactory.getLogger(ProdutoController.class);

    @Autowired
    ProdutoRepository repository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EntityManager em;

    @InitBinder(value = "produtoRequest")
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

    //Lembre-se de que, para testar o método a seguir, é preciso que um usuário esteja logado
    //faça um request usando POST para  localhost:8080/auth, passando um usuário e senha válidos
    //Depois de logar, use o token para fazer o request para o método abaixo
    @PostMapping(path = "/{id}/imagens")
    @Transactional
    public ResponseEntity<String> adiconaImagens( @PathVariable("id") Long id, List<MultipartFile> imagens, UriComponentsBuilder ucb){
        Produto produto = repository.findById(id).get();
        Usuario usuarioLogado =  retornaUsuarioLogado();
        if( usuarioLogado == null) return ResponseEntity.badRequest().body("Usuário precisa estar logado");
        if( produto == null) return ResponseEntity.badRequest().body("Produto não existe");
        if (!produtoPertenceAoUsuarioLogado(produto, usuarioLogado)) return ResponseEntity.badRequest().body("Produto não pertence ao usuário");
        if (imagens.size() < 1) return ResponseEntity.badRequest().body("Pelo menos uma imagem deve ser definida");

        //Chamo um método em Produto, que grava as imagens em disco e
        // retorna um lista de UNCs das imagens
        //Uso a lista retornada para invocar um método, também em Produto
        //que salva os caminhos UNC numa lista de imagens do produto
        produto.vinculaImagens(produto.gravaImagens(imagens));
        //persisto o produto, pra que as imagens - que na verdade são uma lista de caminhos,
        // (e, juntas, são um atributo de Produto)
        // sejam persistidas junto
        repository.save(produto);
        URI uri = ucb.path("produto/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body("Imagem vinculada ao produto com sucesso");
    }


    @PostMapping(path = "/{id}/opinioes")
    //@Transactional
    public ResponseEntity<String> adiconaOpiniao(@PathVariable("id") Long id, @RequestBody @Valid OpinaoProdutoRequest opiniaoRequest, UriComponentsBuilder ucb){
        Usuario usuarioLogado =  retornaUsuarioLogado();
        if( usuarioLogado == null) return ResponseEntity.badRequest().body("Usuário precisa estar logado");
        Produto produto = repository.findById(id).get();
        if( produto == null) return ResponseEntity.badRequest().body("Produto não existe");
        OpiniaoProduto perguntaModel = opiniaoRequest.toModel(usuarioLogado, produto );
        produto.vinculaOpinioes(perguntaModel);
        repository.save(produto);
        URI uri = ucb.path("produto/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body("Opiniao incluída com sucesso");
    }


    @PostMapping(path = "/{id}/perguntas")
    public ResponseEntity<PerguntaProdutoResponse> adiconaPergunta(@PathVariable("id") Long id, @RequestBody @Valid PerguntaProdutoRequest perguntaProdutoRequest){
        logger.trace("Usuário tentou incluir uma pergunta sobre o produto com id: " + id );
        Produto produtoRetornado = repository.findById(id).orElseThrow(() -> {
                logger.error("Usuário tentou perguntar sobre um produto que não existe");
                throw new CustomNotFoundException("cartao", "Este produto não existe no sistema");}
        );

        Usuario usuarioPergunta = retornaUsuarioLogado();
        if( usuarioPergunta == null) {
            logger.error("Tentativa de fazer pergunta sem efetuar login");
            throw new GeneralBusinesException(null, "O usuário precisa estar logado para fazer perguntas");
        }

        Usuario donoDoProduto = retornaDonoDoProduto(produtoRetornado);
        if( usuarioPergunta == null) {
            logger.error("Não foi possível encontar o dono do produto id:  " + produtoRetornado.getId() + " - nome: " + produtoRetornado.getNome());
            throw new GeneralBusinesException(null, "Não foi possível encontrar o dono do produto, para encaminhar a pergunta");
        }

        PerguntaProduto perguntaProduto = perguntaProdutoRequest.toModel(usuarioPergunta,produtoRetornado);
        produtoRetornado.incluiPergunta(perguntaProduto);
        repository.save(produtoRetornado);

        EmailPergunta emailPergunta = new EmailPergunta(produtoRetornado);
        emailPergunta.enviaEmail();

        PerguntaProdutoResponse perguntaProdutoResponse = new PerguntaProdutoResponse(perguntaProduto);

        return ResponseEntity.ok().body(perguntaProdutoResponse);
    }

    private boolean produtoPertenceAoUsuarioLogado(Produto produto, Usuario usuario){
        if(usuario.getId() != produto.getUsuario().getId()) return false;
        return true;
    }

    private Usuario retornaUsuarioLogado (){
        Object userDetails  = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if( userDetails != "anonymousUser")
            return (Usuario) userDetails;
        else
            return null;
    }

        private Usuario retornaDonoDoProduto(Produto produto){
        Long idDonoProduto = produto.getUsuario().getId();
        Usuario usuario = usuarioRepository.findById(idDonoProduto).orElseThrow(
                ()->{
                    logger.error("Tentou fazer pergunta sobre produto sem dono, ou dono do produto não encontrado");
                    throw new GeneralBusinesException("Vendedor do produto", "Não foi possível encontrar o vendedor do produto");
                }
        );
        return usuario;
    }


}
