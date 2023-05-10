package controller;

import java.sql.Connection;
import java.util.List;

import DAO.UsuarioDAO;
import factory.ConnectionFactory;
import modelo.Usuario;

public class UsuarioController {

	private UsuarioDAO usuarioDAO;

	public UsuarioController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.usuarioDAO = new UsuarioDAO(connection);
	}

	public boolean checkLogin(String login, String senha) {

		return this.usuarioDAO.checkLogin(login, senha);

	}

	public String alterar(Long id, String login, String senha, Long nivelAcessoId) {

		return this.usuarioDAO.alterar(id, login, senha, nivelAcessoId);

	}

	public void deletar(Long id) {

		this.usuarioDAO.deletar(id);

	}

	public String salvar(Usuario usuario) {

		return this.usuarioDAO.salvar(usuario);

	}

	public List<Usuario> listar() {

		return this.usuarioDAO.listar();

	}

	public String getNivelAcesso(Long nivelAcessoId) {

		return this.usuarioDAO.getNivelAcesso(nivelAcessoId);

	}

}
