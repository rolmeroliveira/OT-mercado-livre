package br.com.zup.mercadolivre.compra;

import br.com.zup.mercadolivre.Pagamento.Gateway;
import br.com.zup.mercadolivre.Pagamento.GatewayPagamento;
import br.com.zup.mercadolivre.security.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Requeriodos para abertura do carrinho.  Não pode começar uma compra sem isso
    @NotNull
    @ManyToOne
    private Usuario comprador;

    private LocalDateTime momentoAbertura = LocalDateTime.now();

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
    private List<CarrinhoItem> itemsDoCarrinho;

    @Enumerated(EnumType.STRING)
    private StatusDaCompra statusDaCompra = StatusDaCompra.INICIADA;

    @Enumerated(EnumType.STRING)
    private Gateway gateway;

    //Para preenchimebnto somente no encerramento da compra
    private LocalDateTime momentoEncerramento;

    public Carrinho(Usuario comprador, Gateway gateway) {
        this.comprador = comprador;
        this.gateway = gateway;
    }

    public Carrinho() {
    }

    public Carrinho(Long id,
                    Usuario comprador,
                    LocalDateTime momentoAbertura,
                    List<CarrinhoItem> itemsDoCarrinho,
                    StatusDaCompra statusDaCompra,
                    LocalDateTime momentoEncerramento,
                    Gateway gateway) {
        this.id = id;
        this.comprador = comprador;
        this.momentoAbertura = momentoAbertura;
        this.itemsDoCarrinho = itemsDoCarrinho;
        this.statusDaCompra = statusDaCompra;
        this.momentoEncerramento = momentoEncerramento;
        this.gateway = gateway;
    }

    public void adicionaItem(CarrinhoItem carrinhoItem){
        this.itemsDoCarrinho.add(carrinhoItem);
    }

    public Long getId() {
        return id;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public LocalDateTime getMomentoAbertura() {
        return momentoAbertura;
    }

    public List<CarrinhoItem> getItemsDoCarrinho() {
        return itemsDoCarrinho;
    }

    public StatusDaCompra getStatusDaCompra() {
        return statusDaCompra;
    }

    public LocalDateTime getMomentoEncerramento() {
        return momentoEncerramento;
    }

    public Gateway getGateway() {
        return gateway;
    }
}
