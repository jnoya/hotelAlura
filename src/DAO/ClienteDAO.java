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

import modelo.Cliente;
import modelo.ClienteComReserva;

public class ClienteDAO {

	private Connection connection;

	public ClienteDAO(Connection connection) {

		this.connection = connection;

	}

	public void salvarCliente(Cliente cliente) {

		try {
			String sql = "INSERT INTO CLIENTES (NOME, SOBRENOME, DATANASCIMENTO, NACIONALIDADEID, TELEFONE, ATIVO) VALUES (?, ?, ?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setString(1, cliente.getNome());
				pstm.setString(2, cliente.getSobrenome());
				pstm.setDate(3, Date.valueOf(cliente.getDataNascimento()));
				pstm.setLong(4, cliente.getNacionalidadeId());
				pstm.setString(5, cliente.getTelefone());
				pstm.setBoolean(6, true);
				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {

					rst.next();
					cliente.setId(rst.getLong(1));

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public List<ClienteComReserva> buscarHospede(String sobrenome) {

		try {
			String sql = "SELECT C.ID, C.NOME, C.SOBRENOME, C.DATANASCIMENTO, C.NACIONALIDADEID, C.TELEFONE, R.CODIGORESERVA, R.ATIVO FROM CLIENTES C LEFT JOIN RESERVAS R ON C.ID = R.CLIENTEID  WHERE C.SOBRENOME = ? AND C.ATIVO = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setString(1, sobrenome);
				pstm.setBoolean(2, true);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					List<ClienteComReserva> clientes = new ArrayList<ClienteComReserva>();

					while (rst.next()) {

						ClienteComReserva cliente = new ClienteComReserva(rst.getLong(1), rst.getString(2),
								rst.getString(3),
								rst.getDate(4).toLocalDate(), rst.getLong(5),
								rst.getString(6), rst.getString(7), rst.getBoolean(8));
						clientes.add(cliente);

					}

					return clientes;

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public boolean deletarHospede(Long id) {

		try (PreparedStatement stm = connection.prepareStatement("UPDATE CLIENTES SET ATIVO = ? WHERE ID = ?")) {

			if (desimpedido(id)) {

				stm.setBoolean(1, false);
				stm.setLong(2, id);
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

			String sql = "SELECT ID FROM RESERVAS WHERE CLIENTEID = ? AND ATIVO = ?";

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

	public String alterar(ClienteComReserva cliente) {

		try (PreparedStatement stm = connection.prepareStatement(
				"UPDATE CLIENTES C SET C.NOME = ?, C.SOBRENOME = ?, C.DATANASCIMENTO = ?, C.NACIONALIDADEID = ?, C.TELEFONE = ? WHERE ID = ?")) {

			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getSobrenome());
			stm.setDate(3, Date.valueOf(cliente.getDataNascimento()));
			stm.setLong(4, cliente.getNacionalidadeId());
			stm.setString(5, cliente.getTelefone());
			stm.setLong(6, cliente.getId());
			stm.execute();
			return "Cliente alterado com sucesso!";

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public List<Cliente> buscarTelefone(String telefone) {

		try {

			String sql = "SELECT ID, NOME, SOBRENOME, DATANASCIMENTO, NACIONALIDADEID, TELEFONE, ATIVO FROM CLIENTES WHERE TELEFONE = ? AND ATIVO = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setString(1, telefone);
				pstm.setBoolean(2, true);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					List<Cliente> clientes = new ArrayList<Cliente>();
					
					while (rst.next()) {

						Cliente cliente = new Cliente(rst.getLong(1), rst.getString(2), rst.getString(3), rst.getDate(4).toLocalDate(), rst.getLong(5), rst.getString(6), rst.getBoolean(7));
						
						clientes.add(cliente);

					}

					return clientes;

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public List<Cliente> buscarSobrenome(String sobrenome) {

		try {

			String sql = "SELECT ID, NOME, SOBRENOME, DATANASCIMENTO, NACIONALIDADEID, TELEFONE, ATIVO FROM CLIENTES WHERE SOBRENOME = ? AND ATIVO = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setString(1, sobrenome);
				pstm.setBoolean(2, true);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					List<Cliente> clientes = new ArrayList<Cliente>();
					
					while (rst.next()) {

						Cliente cliente = new Cliente(rst.getLong(1), rst.getString(2), rst.getString(3), rst.getDate(4).toLocalDate(), rst.getLong(5), rst.getString(6), rst.getBoolean(7));
						
						clientes.add(cliente);

					}

					return clientes;

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public List<Cliente> buscarDataNascimento(LocalDate dataNascimento) {

		try {

			String sql = "SELECT ID, NOME, SOBRENOME, DATANASCIMENTO, NACIONALIDADEID, TELEFONE, ATIVO FROM CLIENTES WHERE DATANASCIMENTO = ? AND ATIVO = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setDate(1, Date.valueOf(dataNascimento));
				pstm.setBoolean(2, true);
				pstm.execute();

				try (ResultSet rst = pstm.getResultSet()) {

					List<Cliente> clientes = new ArrayList<Cliente>();
					
					while (rst.next()) {

						Cliente cliente = new Cliente(rst.getLong(1), rst.getString(2), rst.getString(3), rst.getDate(4).toLocalDate(), rst.getLong(5), rst.getString(6), rst.getBoolean(7));
						
						clientes.add(cliente);

					}

					return clientes;

				}

			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public void ativaCliente(Long id) {

		try (PreparedStatement stm = connection.prepareStatement(
				"UPDATE CLIENTES C SET C.ATIVO = ? WHERE ID = ?")) {

			stm.setBoolean(1, true);
			stm.setLong(2, id);
			stm.execute();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

}
