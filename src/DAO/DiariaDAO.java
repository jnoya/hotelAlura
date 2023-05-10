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
import modelo.Diaria;

public class DiariaDAO {

	private Connection connection;

	public DiariaDAO(Connection connection) {

		this.connection = connection;

	}

	public void alterar(Long id, Double valor) {

		try (PreparedStatement stm = connection.prepareStatement("UPDATE DIARIAS D SET D.VALOR = ? WHERE D.ID = ?")) {

			stm.setDouble(1, valor);
			stm.setLong(2, id);
			stm.execute();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public void deletar(Long id) {

		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM DIARIAS WHERE ID = ?")) {

			stm.setLong(1, id);
			stm.execute();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public String salvar(Diaria diaria) {

		Long tipoHabitacaoId = diaria.getTipoHabitacaoId();
		try {
			String sql = "INSERT INTO DIARIAS (DATA, TIPOHABITACAOID, VALOR, ATIVO) VALUES (?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				if (diariaNaoExiste(diaria.getData(), tipoHabitacaoId)) {

					pstm.setDate(1, Date.valueOf(diaria.getData()));
					pstm.setLong(2, tipoHabitacaoId);
					pstm.setDouble(3, diaria.getValor());
					pstm.setBoolean(4, true);
					pstm.execute();

					try (ResultSet rst = pstm.getGeneratedKeys()) {

						rst.next();
						diaria.setId(rst.getLong(1));

					}

					return "Diaria salva com sucesso!";

				} else {

					return "Registro já existe na base de dados!";

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	private boolean diariaNaoExiste(LocalDate data, Long tipoHabitacaoId) {

		try (PreparedStatement pstm = connection
				.prepareStatement("SELECT DATA FROM DIARIAS WHERE DATA = ? AND TIPOHABITACAOID = ? AND ATIVO = ?")) {

			pstm.setDate(1, Date.valueOf(data));
			pstm.setLong(2, tipoHabitacaoId);
			pstm.setBoolean(3, true);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				return false;

			}

			return true;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public List<Diaria> listar() {

		List<Diaria> diarias = new ArrayList<Diaria>();
		try {

			String sql = "SELECT ID, DATA, TIPOHABITACAOID, VALOR, ATIVO FROM DIARIAS WHERE ATIVO = ? ORDER BY DATA";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setBoolean(1, true);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					while (rst.next()) {

						Diaria diaria = new Diaria(rst.getLong(1),
								rst.getDate(2).toLocalDate(), rst.getLong(3),
								rst.getDouble(4), rst.getBoolean(5));

						diarias.add(diaria);

					}

				}

			}

			return diarias;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public String getTipoHabitacao(Long tipoHabitacaoId) {

		try (PreparedStatement pstm = connection
				.prepareStatement("SELECT TIPOHABITACAO FROM TIPOSHABITACAO WHERE ID = ?")) {

			pstm.setLong(1, tipoHabitacaoId);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				return rs.getString(1);

			}

			return "Tipo de Habitação não encontrado";

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

}
