package br.com.zup.mercadolivre.security;


import br.com.zup.mercadolivre.produto.ProdutoController;
import br.com.zup.mercadolivre.security.usuarios.Usuario;
import br.com.zup.mercadolivre.security.usuarios.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(ProdutoController.class);

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repository.findByEmail(username).orElseThrow(() -> {
			logger.error("Tentou logar com um usuário que o sistema não conseguiu encontrar");
			throw new UsernameNotFoundException("Este usuário não está cadastrado!");
		});
		return usuario;
	}

}
