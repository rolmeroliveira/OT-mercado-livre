package br.com.zup.mercadolivre.security.usuario;
import javax.persistence.*;


@Entity
@Table(name = "usuario")
public class Usuario {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String cpfCnpj;

	public Usuario() {
	}

	public Usuario(Long id, String nome, String email, String cpfCnpj) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfCnpj = cpfCnpj;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}
}
