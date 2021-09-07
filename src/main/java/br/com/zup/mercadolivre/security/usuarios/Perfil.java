package br.com.zup.mercadolivre.security.usuarios;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Perfil implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@ManyToMany(mappedBy = "perfis")
	private List<Usuario> usuarios = new ArrayList<>();


	public Perfil() {
	}

	public Perfil(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Usuario> getUsuarios() {

		return this.usuarios;
	}

	@Override
	public String getAuthority() {
		return nome;
	}
	
}
