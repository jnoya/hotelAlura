package modelo;

public class NivelAcesso {

	private Long id;
	private String nivelAcesso;
	private boolean ativo;
	
	public NivelAcesso(String nivelAcceso) {

		this.nivelAcesso = nivelAcceso;

	}

	public NivelAcesso(Long id, String nivelAcceso, boolean ativo) {

		this.id = id;
		this.nivelAcesso = nivelAcceso;
		this.ativo = ativo;

	}

	public String getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(String nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public Long getId() {

		return id;
	
	}

	public void setId(long id) {

		this.id = id;

	}

	public String toString() {

		return this.nivelAcesso;

	}

	public boolean getAtivo() {
		
		return ativo;
	
	}

}
