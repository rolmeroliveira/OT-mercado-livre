package br.com.zup.mercadolivre.repository;


import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	Optional<Categoria> findByNome(String nome);

}
