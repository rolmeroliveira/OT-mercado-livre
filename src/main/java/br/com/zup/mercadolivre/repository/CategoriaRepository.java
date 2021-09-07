package br.com.zup.mercadolivre.repository;


import br.com.zup.mercadolivre.categoria.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	Optional<Categoria> findByNome(String nome);

}
