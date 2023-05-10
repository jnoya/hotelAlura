package modelo;

import java.time.LocalDate;

public class ReservaComCliente {

	private String codigoReserva;
	private String nome;
	private String sobrenome;
	private LocalDate dataEntrada;
	private LocalDate dataSaida;
	private Double valorReserva;
	private Boolean ativo;
	
	public ReservaComCliente(String codigoReserva, String nome, String sobrenome, LocalDate dataEntrada, LocalDate dataSaida, Double valorReserva, Boolean ativo) {
		
		this.codigoReserva = codigoReserva;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valorReserva = valorReserva;
		this.ativo = ativo;
		
	}

	public String getCodigoReserva() {
		return codigoReserva;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public Double getValorReserva() {
		return valorReserva;
	}

	public Boolean getAtivo() {
		return ativo;
	}
	
}
