package br.com.zup.mercadolivre.repository;

import br.com.zup.mercadolivre.compra.Carrinho;
import br.com.zup.mercadolivre.compra.StatusDaCompra;
import br.com.zup.mercadolivre.security.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    Optional <Carrinho> findFirstByCompradorAndStatusDaCompra(Usuario compardor, StatusDaCompra statusDaCompra);

}
