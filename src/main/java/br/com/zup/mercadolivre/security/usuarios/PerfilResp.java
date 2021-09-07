package br.com.zup.mercadolivre.security.usuarios;

public class PerfilResp {

	private Long id;
	private String nome;

	public PerfilResp(Perfil perfil) {
		this.id = perfil.getId();
		this.nome = perfil.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
}
