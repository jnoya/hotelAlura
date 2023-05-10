package modelo;

public class FormaPagamento {

	private Long id;
	private String formaPagamento;
	private boolean ativo;
	
	public FormaPagamento(Long id, String formaPagamento, boolean ativo) {

		this.id = id;
		this.formaPagamento = formaPagamento;
		this.ativo = ativo;

	}

	public FormaPagamento(String formaPagamento) {

		this.formaPagamento = formaPagamento;

	}

	public String getFormaPagamento() {

		return formaPagamento;

	}

	public void setFormaPagamento(String formaPagamento) {

		this.formaPagamento = formaPagamento;

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
	public String toString() {

		return this.formaPagamento;

	}

}
