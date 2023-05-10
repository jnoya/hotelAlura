package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.ReservaController;
import modelo.Reserva;
import modelo.ReservaComCliente;

public class ReservaDAO {

	private Connection connection;

	public ReservaDAO(Connection connection) {

		this.connection = connection;

	}

	public Double calculaTotalReserva(LocalDate dataEntrada, LocalDate dataSaida, Long tipoHabitacaoId) {

		LocalDate hoje = LocalDate.now();

		if (!dataEntrada.isBefore(hoje)) {

			if (dataEntrada.isBefore(dataSaida)) {

				Double totalReserva = getValorDiaria(dataEntrada, tipoHabitacaoId);

				if (totalReserva > 0) {
					LocalDate data = dataEntrada.plusDays(1);
					while (true) {

						if (data.isBefore(dataSaida)) {

							Double valor = getValorDiaria(data, tipoHabitacaoId);
							if (valor > 0) {

								totalReserva += valor;
								data = data.plusDays(1);

							} else {

								ReservaController.mensaje("O dia " + data + " para o tipo de habitacao "
										+ getDescricao(tipoHabitacaoId) + " no tiene registro em Diarias");
								return 0d;
							}

						} else {

							break;

						}

					}

					return totalReserva;

				}

				ReservaController.mensaje("O dia " + dataEntrada + " para o tipo de habitacao "
						+ getDescricao(tipoHabitacaoId) + " no tiene registro em Diarias");
				return 0d;

			} else {

				ReservaController.mensaje("La data de saida debe ser maior que a data de entrada");
				return 0d;

			}

		} else {

			ReservaController.mensaje("La data de entrada não pode ser menor que hoje");
			return 0d;

		}

	}

	private Double getValorDiaria(LocalDate data, Long tipoHabitacaoId) {

		try (PreparedStatement pstm = connection
				.prepareStatement("SELECT VALOR FROM DIARIAS WHERE DATA = ? AND TIPOHABITACAOID = ? AND ATIVO = ?")) {

			pstm.setDate(1, java.sql.Date.valueOf(data));
			pstm.setLong(2, tipoHabitacaoId);
			pstm.setBoolean(3, true);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				return rs.getDouble(1);

			}

			return 0d;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public String getDescricao(Long tipoHabitacaoId) {

		try {

			String sql = "SELECT DESCRICAO FROM TIPOSHABITACAO WHERE ID = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setLong(1, tipoHabitacaoId);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					if (rst.next()) {

						return rst.getString(1);

					} else {

						return "Tipo de Habitacao não existe";

					}

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public Long ultimaReserva() {

		try {

			String sql = "SELECT MAX(ID) FROM RESERVAS WHERE ATIVO = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setBoolean(1, true);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					if (rst.next()) {

						return rst.getLong(1);

					} else {

						return 0l;

					}

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public void salvarReserva(Reserva reserva) {

		try {
			String sql = "INSERT INTO RESERVAS (TIPOHABITACAOID, DATAENTRADA, DATASAIDA, VALORRESERVA, FORMAPAGAMENTOID, CODIGORESERVA, CLIENTEID,  ATIVO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setLong(1, reserva.getTipoHabitacaoId());
				pstm.setDate(2, Date.valueOf(reserva.getDataEntrada()));
				pstm.setDate(3, Date.valueOf(reserva.getDataSaida()));
				pstm.setDouble(4, reserva.getValorReserva());
				pstm.setLong(5, reserva.getFormaPagamentoId());
				pstm.setString(6, reserva.getCodigoReserva());
				pstm.setLong(7, reserva.getClienteId());
				pstm.setBoolean(8, true);
				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {

					rst.next();
					reserva.setId(rst.getLong(1));

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public Reserva buscarReserva(String codigoReserva) {

		try {
			String sql = "SELECT ID, TIPOHABITACAOID, DATAENTRADA, DATASAIDA, VALORRESERVA, FORMAPAGAMENTOID, CODIGORESERVA, CLIENTEID FROM RESERVAS WHERE CODIGORESERVA = ? AND ATIVO = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setString(1, codigoReserva);
				pstm.setBoolean(2, true);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					if (rst.next()) {

						return new Reserva(rst.getLong(1), rst.getLong(2), rst.getDate(3).toLocalDate(),
								rst.getDate(4).toLocalDate(), rst.getDouble(5), rst.getLong(6), rst.getString(7),
								rst.getLong(8), true);

					}

					return null;

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public void deletarReserva(String id) {

		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE RESERVAS SET ATIVO = FALSE WHERE CODIGORESERVA = ?")) {

			stm.setString(1, id);
			stm.execute();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public void alterarReserva(Reserva reserva) {

		try (PreparedStatement stm = connection.prepareStatement(
				"UPDATE RESERVAS R SET R.DATAENTRADA = ?, R.DATASAIDA = ?, R.VALORRESERVA = ?, R.FORMAPAGAMENTOID = ?, R.TIPOHABITACAOID = ? WHERE R.ID = ?")) {

			stm.setDate(1, Date.valueOf(reserva.getDataEntrada()));
			stm.setDate(2, Date.valueOf(reserva.getDataSaida()));
			stm.setDouble(3, reserva.getValorReserva());
			stm.setLong(4, reserva.getFormaPagamentoId());
			stm.setLong(5, reserva.getTipoHabitacaoId());
			stm.setLong(6, reserva.getId());
			stm.execute();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public List<Reserva> listaReservas(LocalDate dataEntrada, LocalDate dataSaida) {

		try (PreparedStatement stm = connection.prepareStatement(
				"SELECT ID, TIPOHABITACAOID, DATAENTRADA, DATASAIDA, VALORRESERVA, FORMAPAGAMENTOID, CODIGORESERVA, CLIENTEID, ATIVO FROM RESERVAS WHERE DATASAIDA >= ? AND DATASAIDA <= ?")) {

			stm.setDate(1, Date.valueOf(dataEntrada));
			stm.setDate(2, Date.valueOf(dataSaida));
			stm.execute();

			try (ResultSet rst = stm.getResultSet()) {

				List<Reserva> listaReservas = new ArrayList<Reserva>();

				while (rst.next()) {

					Reserva reserva = new Reserva(rst.getLong(1), rst.getLong(2), rst.getDate(3).toLocalDate(),
							rst.getDate(4).toLocalDate(), rst.getDouble(5), rst.getLong(6), rst.getString(7),
							rst.getLong(8), rst.getBoolean(9));
					listaReservas.add(reserva);
				}

				return listaReservas;

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public String mostrarArrecadacao(LocalDate dataEntrada, LocalDate dataSaida) {

		try (PreparedStatement stm = connection.prepareStatement(
				"SELECT SUM(VALORRESERVA) FROM RESERVAS WHERE DATASAIDA >= ? AND DATASAIDA <= ? AND ATIVO = ?")) {

			stm.setDate(1, Date.valueOf(dataEntrada));
			stm.setDate(2, Date.valueOf(dataSaida));
			stm.setBoolean(3, true);
			stm.execute();

			try (ResultSet rst = stm.getResultSet()) {

				if (rst.next()) {

					return String.valueOf(rst.getDouble(1));
				}

				return "0";

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public List<ReservaComCliente> listaReservasComCliente(LocalDate dataEntrada, LocalDate dataSaida) {

		try (PreparedStatement stm = connection.prepareStatement(
				"SELECT R.CODIGORESERVA, C.NOME, C.SOBRENOME, R.DATAENTRADA, R.DATASAIDA, R.VALORRESERVA, R.ATIVO FROM RESERVAS R INNER JOIN CLIENTES C ON R.CLIENTEID = C.ID WHERE R.DATASAIDA >= ? AND R.DATASAIDA <= ? AND R.ATIVO = ?")) {

			stm.setDate(1, Date.valueOf(dataEntrada));
			stm.setDate(2, Date.valueOf(dataSaida));
			stm.setBoolean(3, false);
			stm.execute();

			try (ResultSet rst = stm.getResultSet()) {

				List<ReservaComCliente> listaReservasComCliente = new ArrayList<ReservaComCliente>();

				while (rst.next()) {

					ReservaComCliente reservaComCliente = new ReservaComCliente(rst.getString(1), rst.getString(2),
							rst.getString(3), rst.getDate(4).toLocalDate(), rst.getDate(5).toLocalDate(),
							rst.getDouble(6), rst.getBoolean(7));
					listaReservasComCliente.add(reservaComCliente);
				}

				return listaReservasComCliente;

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public String mostrarReservasApagadas(LocalDate dataEntrada, LocalDate dataSaida) {

		try (PreparedStatement stm = connection.prepareStatement(
				"SELECT COUNT(ID) FROM RESERVAS WHERE DATASAIDA >= ? AND DATASAIDA <= ? AND ATIVO = ?")) {

			stm.setDate(1, Date.valueOf(dataEntrada));
			stm.setDate(2, Date.valueOf(dataSaida));
			stm.setBoolean(3, false);
			stm.execute();

			try (ResultSet rst = stm.getResultSet()) {

				if (rst.next()) {

					return String.valueOf(rst.getInt(1));

				}

				return "0";

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public void ativarReserva(String codigoReserva) {

		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE RESERVAS R SET R.ATIVO = ? WHERE CODIGORESERVA = ?")) {

			stm.setBoolean(1, true);
			stm.setString(2, codigoReserva);
			stm.execute();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public Long getClienteId(String codigoReserva) {

		try {
			String sql = "SELECT CLIENTEID FROM RESERVAS WHERE CODIGORESERVA = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setString(1, codigoReserva);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					if (rst.next()) {

						return rst.getLong(1);
					}

					return null;

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public void deletarDefinitivo(String codigoReserva) {

		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM RESERVAS WHERE CODIGORESERVA = ?")) {

			stm.setString(1, codigoReserva);
			stm.execute();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

}