package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.NivelAcesso;

public class NivelAcessoDAO {

	private Connection connection;

	public NivelAcessoDAO(Connection connection) {
		this.connection = connection;
	}

	public List<NivelAcesso> listar() {

		try {

			List<NivelAcesso> niveisAcesso = new ArrayList<>();
			String sql = "SELECT ID, NIVELACESSO, ATIVO FROM NIVEISACESSO WHERE ATIVO = ? ORDER BY NIVELACESSO";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setBoolean(1, true);
				pstm.execute();
				trasformarResultSetEmNiveisAcesso(niveisAcesso, pstm);

			}

			return niveisAcesso;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	private void trasformarResultSetEmNiveisAcesso(List<NivelAcesso> niveisAcesso, PreparedStatement pstm)
			throws SQLException {

		try (ResultSet rst = pstm.getResultSet()) {

			while (rst.next()) {

				NivelAcesso nivelAcesso = new NivelAcesso(rst.getLong(1), rst.getString(2), rst.getBoolean(3));
				niveisAcesso.add(nivelAcesso);

			}

		}

	}

	public String alterar(Long id, String nivelAcesso) {

		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE NIVEISACESSO N SET N.NIVELACESSO = ? WHERE ID = ?")) {

			stm.setString(1, nivelAcesso);
			stm.setLong(2, id);

			if (nivelAcessoNaoExiste(nivelAcesso)) {

				stm.execute();
				return "Nivel de acesso modificado com sucesso!";

			}else {
				
				return "Nivel de acesso já existe";
			
			}
				
		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	private boolean nivelAcessoNaoExiste(String nivelAcesso) {

		try {

			String sql = "SELECT ID FROM NIVEISACESSO WHERE NIVELACESSO = ? AND ATIVO = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setString(1, nivelAcesso);
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
				.prepareStatement("DELETE FROM NIVEISACESSO WHERE ID = ?")) {

			if (desimpedido(id)) {

				stm.setLong(1, id);
				stm.execute();

				return true;

			} else {

				return false;

			}
		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public String salvar(NivelAcesso nivelAcesso) {

		try {
			String sql = "INSERT INTO NIVEISACESSO (NIVELACESSO, ATIVO) VALUES (?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				if (nivelAcessoNaoExiste(nivelAcesso.getNivelAcesso())) {

					pstm.setString(1, nivelAcesso.getNivelAcesso());
					pstm.setBoolean(2, true);
					pstm.execute();
	
					try (ResultSet rst = pstm.getGeneratedKeys()) {
	
						rst.next();
						nivelAcesso.setId(rst.getLong(1));
	
					}

					return "Nivel de acesso salvo com sucesso!";

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

			String sql = "SELECT ID FROM USUARIOS WHERE NIVELACESSOID = ? AND ATIVO = ?";

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

}
