package modelo;

public class Usuario {
	
	private Long id;
	private String login;
	private String senha;
	private Long nivelAcessoId;
	private boolean ativo;
	
	public Usuario(String login, String senha, Long nivelAcessoId) {

		this.login = login;
		this.senha = senha;
		this.nivelAcessoId = nivelAcessoId;
	
	}
	
	public Usuario(Long id, String login, String senha, Long nivelAcessoId, boolean ativo) {

		this.id = id;
		this.login = login;
		this.senha = senha;
		this.nivelAcessoId = nivelAcessoId;
		this.ativo = ativo;
	
	}

	public String getLogin() {
	
		return login;
	
	}
	
	public void setLogin(String login) {
	
		this.login = login;
	
	}
	
	public String getSenha() {
	
		return senha;
	
	}
	
	public void setSenha(String senha) {
	
		this.senha = senha;
	
	}
	
	public Long getNivelAcessoId() {
	
		return nivelAcessoId;
	
	}
	
	public void setNivelAcessoId(Long nivelAcessoId) {
	
		this.nivelAcessoId = nivelAcessoId;
	
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
		
	}

	public boolean getAtivo() {

		return ativo;
	
	}

}
