package modelo;

import java.time.LocalDate;

public class Reserva {

	private Long id;
	private Long tipoHabitacaoId;
	private LocalDate dataEntrada;
	private LocalDate dataSaida;
	private Double valorReserva;
	private Long formaPagamentoId;
	private String codigoReserva;
	private Long clienteId;
	private boolean ativo;

	public Reserva(Long id, Long tipoHabitacaoId, LocalDate dataEntrada, LocalDate dataSaida, Double valorReserva, Long formaPagamentoId, String codigoReserva, Long clienteId, Boolean ativo) {

		this.id = id;
		this.tipoHabitacaoId = tipoHabitacaoId;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valorReserva = valorReserva;
		this.formaPagamentoId = formaPagamentoId;
		this.codigoReserva = codigoReserva;
		this.clienteId = clienteId;
		this.ativo = ativo;

	}

	public LocalDate getDataEntrada() {

		return dataEntrada;

	}

	public void setDataEntrada(LocalDate dataEntrada) {

		this.dataEntrada = dataEntrada;

	}

	public LocalDate getDataSaida() {

		return dataSaida;

	}

	public void setDataSaida(LocalDate dataSaida) {

		this.dataSaida = dataSaida;

	}

	public Double getValorReserva() {

		return valorReserva;

	}

	public void setValorReserva(Double valor) {

		this.valorReserva = valor;

	}

	public Long getFormaPagamentoId() {

		return formaPagamentoId;

	}

	public void setFormaPagamentoId(Long formaPagamentoId) {

		this.formaPagamentoId = formaPagamentoId;

	}

	public Long getTipoHabitacaoId() {
		return tipoHabitacaoId;
	}

	public void setTipoHabitacaoId(Long tipoHabitacaoId) {
		this.tipoHabitacaoId = tipoHabitacaoId;
	}

	public String getCodigoReserva() {
		return codigoReserva;
	}

	public void setCodigoReserva(String codigoReserva) {
		this.codigoReserva = codigoReserva;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public void setId(long id) {

		this.id = id;
		
	}

	public Long getId() {
		
		return id;
		
	}

	
}
