package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.FormaPagamento;

public class FormaPagamentoDAO {

	private Connection connection;

	public FormaPagamentoDAO(Connection connection) {
		this.connection = connection;
	}

	public String alterar(Long id, String formaPagamento) {

		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE FORMASPAGAMENTO F SET F.FORMAPAGAMENTO = ? WHERE ID = ?")) {

			stm.setString(1, formaPagamento);
			stm.setLong(2, id);

			if (formaPagamentoNaoExiste(formaPagamento)) {

				stm.execute();
				return "Forma de pagamento alterada com sucesso!";
			}else {
				
				return "Forma de pagamento já existe";
			
			}
				
		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	private boolean formaPagamentoNaoExiste(String formaPagamento) {

		try {

			String sql = "SELECT ID FROM FORMASPAGAMENTO WHERE FORMAPAGAMENTO = ? AND ATIVO = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setString(1, formaPagamento);
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

		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM FORMASPAGAMENTO WHERE ID = ?")) {

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

	public List<FormaPagamento> listar() {

		List<FormaPagamento> formasPagamento = new ArrayList<FormaPagamento>();
		try {

			String sql = "SELECT ID, FORMAPAGAMENTO, ATIVO FROM FORMASPAGAMENTO WHERE ATIVO = ? ORDER BY FORMAPAGAMENTO";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				
				pstm.setBoolean(1, true);
				pstm.execute();

				trasformarResultSetEmFormasPagamento(formasPagamento, pstm);

			}

			return formasPagamento;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}


	private void trasformarResultSetEmFormasPagamento(List<FormaPagamento> formasPagamento, PreparedStatement pstm)
			throws SQLException {

		try (ResultSet rst = pstm.getResultSet()) {

			while (rst.next()) {

				FormaPagamento formaPagamento = new FormaPagamento(rst.getLong(1), rst.getString(2), rst.getBoolean(3));

				formasPagamento.add(formaPagamento);

			}

		}

	}

	public String salvar(FormaPagamento formaPagamento) {

		try {
			String sql = "INSERT INTO FORMASPAGAMENTO (FORMAPAGAMENTO, ATIVO) VALUES (?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				if (formaPagamentoNaoExiste(formaPagamento.getFormaPagamento())) {
	
					pstm.setString(1, formaPagamento.getFormaPagamento());
					pstm.setBoolean(2, true);
					pstm.execute();
	
					try (ResultSet rst = pstm.getGeneratedKeys()) {
	
						rst.next();
						formaPagamento.setId(rst.getLong(1));
	
					}

					return "Forma de pagamento salva com sucesso!";

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

			String sql = "SELECT ID FROM RESERVAS WHERE FORMAPAGAMENTOID = ? AND ATIVO = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setLong(1, id);
				pstm.setBoolean(2, true);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					if (rst.next()) {

						return false;
				
					}else {
						
						return true;
					
					}

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public String getFormaPagamentoComId(Long formaPagamentoId) {

		try {

			String sql = "SELECT FORMAPAGAMENTO FROM FORMASPAGAMENTO WHERE ID = ? AND ATIVO = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setLong(1, formaPagamentoId);
				pstm.setBoolean(2, true);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					if (rst.next()) {

						return rst.getString(1);
				
					}else {
						
						return null;
					
					}

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public Long getIdComFormaPagamento(String formaPagamento) {

		try {

			String sql = "SELECT ID FROM FORMASPAGAMENTO WHERE FORMAPAGAMENTO = ? AND ATIVO = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setString(1, formaPagamento);
				pstm.setBoolean(2, true);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					if (rst.next()) {

						return rst.getLong(1);
				
					}else {
						
						return null;
					
					}

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

}
