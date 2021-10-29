package br.com.zup.mercadolivre.security.usuario;


public class UsuarioResp {

	private String nome;
	private String email;
	private String cpfCnpj;

	public UsuarioResp() {
	}

	public UsuarioResp(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.cpfCnpj = usuario.getCpfCnpj();
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
