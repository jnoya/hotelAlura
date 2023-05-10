package modelo;

import java.time.LocalDate;

public class Diaria {

	private Long id;
	private LocalDate data;
	private long tipoHabitacaoId;
	private Double valor;
	private boolean ativo;

	public Diaria(LocalDate data, String valorS, long tipoHabitacaoIdS) {

		this.data = data;
		this.tipoHabitacaoId = Long.valueOf(tipoHabitacaoIdS).longValue();
		this.valor = Double.valueOf(valorS).doubleValue();

	}

	public Diaria(long id, LocalDate data, long tipoHabitacaoId, double valor, boolean ativo) {

		this.id = id;
		this.data = data;
		this.tipoHabitacaoId = tipoHabitacaoId;
		this.valor = valor;
		this.ativo = ativo;

	}

	public LocalDate getData() {

		return data;

	}

	public void setData(LocalDate data) {

		this.data = data;

	}

	public long getTipoHabitacaoId() {

		return tipoHabitacaoId;

	}

	public void setTipoHabitacaoId(long tipoHabitacaoId) {

		this.tipoHabitacaoId = tipoHabitacaoId;

	}

	public Double getValor() {

		return valor;

	}

	public void setValor(Double valor) {

		this.valor = valor;

	}

	public void setId(Long id) {

		this.id = id;

	}

	public boolean getAtivo() {

		return ativo;

	}

	public Long getId() {

		return id;

	}

}
