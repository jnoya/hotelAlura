package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Nacionalidade;

public class NacionalidadeDAO {

	private Connection connection;

	public NacionalidadeDAO(Connection connection) {
		this.connection = connection;
	}

	public String alterar(Long id, String nacionalidade) {

		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE NACIONALIDADES N SET N.NACIONALIDADE = ? WHERE ID = ?")) {

			stm.setString(1, nacionalidade);
			stm.setLong(2, id);

			if (nacionalidadeNaoExiste(nacionalidade)) {

				stm.execute();
				return "Nacionalidade alterada com sucesso!";

			} else {

				return "Nacionalidade já existe";

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	private boolean nacionalidadeNaoExiste(String nacionalidade) {

		try {

			String sql = "SELECT ID FROM NACIONALIDADES WHERE NACIONALIDADE = ? AND ATIVO = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setString(1, nacionalidade);
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
				.prepareStatement("DELETE FROM NACIONALIDADES WHERE ID = ?")) {

			if (desimpedido(id)) {

				stm.setLong(1, id);
				stm.execute();
				return true;

			}

			return false;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	private boolean desimpedido(Long id) {

		try {

			String sql = "SELECT ID FROM CLIENTES WHERE NACIONALIDADEID = ? AND ATIVO = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setLong(1, id);
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

	public List<Nacionalidade> listar() {

		List<Nacionalidade> nacionalidades = new ArrayList<Nacionalidade>();
		try {

			String sql = "SELECT ID, NACIONALIDADE, ATIVO FROM NACIONALIDADES WHERE ATIVO = ? ORDER BY NACIONALIDADE";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setBoolean(1, true);
				pstm.execute();

				trasformarResultSetEmNacionalidade(nacionalidades, pstm);

			}

			return nacionalidades;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	private void trasformarResultSetEmNacionalidade(List<Nacionalidade> nacionalidades, PreparedStatement pstm)
			throws SQLException {

		try (ResultSet rst = pstm.getResultSet()) {

			while (rst.next()) {

				Nacionalidade nacionalidade = new Nacionalidade(rst.getLong(1), rst.getString(2), rst.getBoolean(3));

				nacionalidades.add(nacionalidade);

			}

		}

	}

	public String salvar(Nacionalidade nacionalidade) {

		try {
			String sql = "INSERT INTO NACIONALIDADES (NACIONALIDADE, ATIVO) VALUES (?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				if (nacionalidadeNaoExiste(nacionalidade.getNacionalidade())) {

					pstm.setString(1, nacionalidade.getNacionalidade());
					pstm.setBoolean(2, true);
					pstm.execute();
	
					try (ResultSet rst = pstm.getGeneratedKeys()) {
	
						rst.next();						
						nacionalidade.setId(rst.getLong(1));
	
					}

					return "Nacionalidade salva com sucesso!";

				} else {

					return "Registro já existe na base de dados!";

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public String getNacionalidadeComId(Long nacionalidadeId) {

		try {

			String sql = "SELECT NACIONALIDADE FROM NACIONALIDADES WHERE ID = ? AND ATIVO = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setLong(1, nacionalidadeId);
				pstm.setBoolean(2, true);
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

	public Long getIdComNacionalidade(String nacionalidade) {

		try {

			String sql = "SELECT ID FROM NACIONALIDADES WHERE NACIONALIDADE = ? AND ATIVO = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setString(1, nacionalidade);
				pstm.setBoolean(2, true);
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
