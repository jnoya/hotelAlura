package modelo;

import java.time.LocalDate;

public class Cliente {

	private Long id;
	private String nome;
	private String sobrenome;
	private LocalDate dataNascimento;
	private Long nacionalidadeId;
	private String telefone;
	private Boolean ativo;

	public Cliente(Long id, String nome, String sobrenome, LocalDate dataNascimento, Long nacionalidadeId, String telefone,
			Boolean ativo) {

		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.nacionalidadeId = nacionalidadeId;
		this.telefone = telefone;
		this.ativo = ativo;

	}

	public String getNome() {

		return nome;

	}

	public void setNome(String nome) {

		this.nome = nome;

	}

	public String getSobrenome() {

		return sobrenome;

	}

	public void setSobrenome(String sobrenome) {

		this.sobrenome = sobrenome;

	}

	public LocalDate getDataNascimento() {

		return dataNascimento;

	}

	public void setDataNascimento(LocalDate dataNascimento) {

		this.dataNascimento = dataNascimento;

	}

	public Long getNacionalidadeId() {

		return nacionalidadeId;

	}

	public void setNacionalidadeId(Long nacionalidadeId) {

		this.nacionalidadeId = nacionalidadeId;

	}

	public String getTelefone() {

		return telefone;

	}

	public void setTelefone(String telefone) {

		this.telefone = telefone;

	}

	public void setId(long id) {

		this.id = id;
	}

	public Long getId() {

		return id;

	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
