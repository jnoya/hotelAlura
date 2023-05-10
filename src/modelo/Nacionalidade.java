package modelo;

public class Nacionalidade {

	private Long id;
	private String nacionalidade;
	private boolean ativo;

	public Nacionalidade(Long id, String nacionalidade, boolean ativo) {

		this.id = id;
		this.nacionalidade = nacionalidade;
		this.ativo = ativo;

	}

	public Nacionalidade(String nacionalidade) {

		this.nacionalidade = nacionalidade;

	}

	public String getNacionalidade() {

		return nacionalidade;

	}

	public void setNacionalidade(String nacionalidade) {

		this.nacionalidade = nacionalidade;
	}

	public boolean getAtivo() {

		return ativo;

	}

	public Long getId() {

		return id;

	}

	public void setId(long id) {

		this.id = id;

	}

	public String toString() {

		return this.nacionalidade;

	}

}
