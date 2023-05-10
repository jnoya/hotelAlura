package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.TipoHabitacao;

public class TipoHabitacaoDAO {

	private Connection connection;

	public TipoHabitacaoDAO(Connection connection) {
		this.connection = connection;
	}

	public String alterar(Long id, String tipoHabitacao) {

		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE TIPOSHABITACAO T SET T.DESCRICAO = ? WHERE ID = ?")) {

			stm.setString(1, tipoHabitacao);
			stm.setLong(2, id);

			if (tipoHabitacaoNaoExiste(tipoHabitacao)) {

				stm.execute();
				return "Tipo de habitação alterada com sucesso!";

			} else {

				return "Tipo de habitação já existe";

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	private boolean tipoHabitacaoNaoExiste(String tipoHabitacao) {

		try {

			String sql = "SELECT ID FROM TIPOSHABITACAO WHERE DESCRICAO = ? AND ATIVO = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setString(1, tipoHabitacao);
				pstm.setBoolean(2, true);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					if (rst.next()) {

						return false;

					} else {

						return true;

					}

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public boolean deletar(Long id) {

		try (PreparedStatement stm = connection
				.prepareStatement("DELETE FROM TIPOSHABITACAO WHERE ID = ?")) {

			if (desimpedido(id)) {

				if (desimpedido2(id)) {

					stm.setLong(1, id);
					stm.execute();
					return true;

				}
			}

			return false;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public List<TipoHabitacao> listar() {

		List<TipoHabitacao> tiposHabitacao = new ArrayList<TipoHabitacao>();
		try {

			String sql = "SELECT ID, DESCRICAO, ATIVO FROM TIPOSHABITACAO WHERE ATIVO = ? ORDER BY DESCRICAO";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setBoolean(1, true);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					while (rst.next()) {

						TipoHabitacao tipoHabitacao = new TipoHabitacao(rst.getLong(1), rst.getString(2),
								rst.getBoolean(3));

						tiposHabitacao.add(tipoHabitacao);

					}

				}

			}

			return tiposHabitacao;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public String salvar(TipoHabitacao tipoHabitacao) {

		try {
			String sql = "INSERT INTO TIPOSHABITACAO (DESCRICAO, ATIVO) VALUES (?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				if (tipoHabitacaoNaoExiste(tipoHabitacao.getDescricao())) {

					pstm.setString(1, tipoHabitacao.getDescricao());
					pstm.setBoolean(2, true);
					pstm.execute();

					try (ResultSet rst = pstm.getGeneratedKeys()) {

						rst.next();
						tipoHabitacao.setId(rst.getLong(1));

					}

					return "Tipo de Habitação salvo com sucesso!";

				} else {

					return "Registro já existe na base de dados!";

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	private boolean desimpedido(Long id) {

		try {

			String sql = "SELECT ID FROM RESERVAS WHERE TIPOHABITACAOID = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setLong(1, id);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					if (rst.next()) {

						return false;

					} else {

						return true;

					}

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	private boolean desimpedido2(Long id) {

		try {

			String sql = "SELECT ID FROM DIARIAS WHERE TIPOHABITACAOID = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setLong(1, id);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					if (rst.next()) {

						return false;

					} else {

						return true;

					}

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public String getDescricaoComId(Long tipoHabitacaoId) {

		try {

			String sql = "SELECT DESCRICAO FROM TIPOSHABITACAO WHERE ID = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setLong(1, tipoHabitacaoId);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					if (rst.next()) {

						return rst.getString(1);

					} else {

						return null;

					}

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public Long getIdComDescricao(String descricao) {

		try {

			String sql = "SELECT ID FROM TIPOSHABITACAO WHERE DESCRICAO = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setString(1, descricao);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					if (rst.next()) {

						return rst.getLong(1);

					} else {

						return null;

					}

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

}
