package br.com.zup.mercadolivre.repository;


import br.com.zup.mercadolivre.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByLogin(String email);

}
