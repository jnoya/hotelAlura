package modelo;

public class TipoHabitacao {

	public Long id;
	public String descricao;
	public boolean ativo;

	public TipoHabitacao(Long id, String descricao, boolean ativo) {

		this.id = id;
		this.descricao = descricao;
		this.ativo = ativo;

	}

	public TipoHabitacao(String descricao) {
		
		this.descricao = descricao;
		
	}
	
	public String getDescricao() {

		return descricao;

	}

	public void setDescricao(String descricao) {

		this.descricao = descricao;

	}

	public Long getId() {

		return id;
	
	}

	public void setId(long id) {

		this.id = id;
		
	}
	
	public boolean getAtivo() {
		
		return ativo;
	
	}

	public Object getTipoHabitacao() {

		return id;
		
	}

	public String toString() {

		return this.descricao;

	}

}
