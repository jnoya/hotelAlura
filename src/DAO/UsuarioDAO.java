package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Usuario;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO(Connection connection) {
		this.connection = connection;
	}

	public boolean checkLogin(String login, String senha) {

		boolean check = false;
		try (PreparedStatement pstm = connection
				.prepareStatement("SELECT * FROM usuarios WHERE BINARY login = ? AND BINARY senha = ? AND ATIVO = ?")) {

			pstm.setString(1, login);
			pstm.setString(2, senha);
			pstm.setBoolean(3, true);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				check = true;

			}

			return check;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public String alterar(Long id, String login, String senha, Long nivelAcessoId) {

		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE USUARIOS U SET U.LOGIN = ?, U.SENHA = ?, U.NIVELACESSOID = ? WHERE ID = ?")) {

			if (nivelAcessoExiste(nivelAcessoId)) {

				if (loginNaoExiste(id,login)) { 
				
					stm.setString(1, login);
					stm.setString(2, senha);
					stm.setLong(3, nivelAcessoId);
					stm.setLong(4, id);
					stm.execute();
					return "Usuario modificado com sucesso!";

				}else {
					
					return "Nome de usuario já existe";
				
				}
					
					
			} else {

				return "Nivel de acesso não existe";
			
			}

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public void deletar(Long id) {

		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM USUARIOS WHERE ID = ?")) {

			stm.setLong(1, id);
			stm.execute();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public String salvar(Usuario usuario) {

		try {
			String sql = "INSERT INTO USUARIOS (LOGIN, SENHA, NIVELACESSOID, ATIVO) VALUES (?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				if (loginNaoExiste(usuario.getLogin())) {

					pstm.setString(1, usuario.getLogin());
					pstm.setString(2, usuario.getSenha());
					pstm.setLong(3, usuario.getNivelAcessoId());
					pstm.setBoolean(4, true);
					pstm.execute();

					try (ResultSet rst = pstm.getGeneratedKeys()) {
						
						rst.next();
						usuario.setId(rst.getLong(1));
		
					}

					return "Usuario salvo com sucesso!";

				}else {
					
					return "Nome de usuario já existe";
				
				}

			}
			
		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public List<Usuario> listar() {

		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {

			String sql = "SELECT ID, LOGIN, SENHA, NIVELACESSOID, ATIVO FROM USUARIOS WHERE ATIVO = ? ORDER BY LOGIN";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setBoolean(1, true);
				pstm.execute();

				trasformarResultSetEmUsuario(usuarios, pstm);

			}

			return usuarios;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	private void trasformarResultSetEmUsuario(List<Usuario> usuarios, PreparedStatement pstm) throws SQLException {

		try (ResultSet rst = pstm.getResultSet()) {

			while (rst.next()) {

				Usuario usuario = new Usuario(rst.getLong(1), rst.getString(2), rst.getString(3), rst.getLong(4),
						rst.getBoolean(5));

				usuarios.add(usuario);

			}

		}

	}

	public String getNivelAcesso(Long nivelAcessoId) {

		try (PreparedStatement pstm = connection
				.prepareStatement("SELECT NIVELACESSO FROM NIVEISACESSO WHERE ID = ? AND ATIVO = ?")) {

			pstm.setLong(1, nivelAcessoId);
			pstm.setBoolean(2, true);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				return rs.getString(1);

			}

			return "Nivel de acesso não encontrado";

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public boolean nivelAcessoExiste(Long nivelAcessoId) {

		try (PreparedStatement pstm = connection
				.prepareStatement("SELECT NIVELACESSO FROM NIVEISACESSO WHERE ID = ? AND ATIVO = ?")) {

			pstm.setLong(1, nivelAcessoId);
			pstm.setBoolean(2, true);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				return true;

			}

			return false;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

	public boolean loginNaoExiste(Long id, String login) {

		try (PreparedStatement pstm = connection
				.prepareStatement("SELECT ID FROM USUARIOS WHERE LOGIN = ? AND ID <> ? AND ATIVO = ?")) {

			pstm.setString(1, login);
			pstm.setLong(2, id);
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

	public boolean loginNaoExiste(String login) {

		try (PreparedStatement pstm = connection
				.prepareStatement("SELECT ID FROM USUARIOS WHERE LOGIN = ? AND ATIVO = ?")) {

			pstm.setString(1, login);
			pstm.setBoolean(2, true);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				return false;

			}

			return true;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

}
