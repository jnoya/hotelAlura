package controller;

import java.sql.Connection;
import java.util.List;

import DAO.NivelAcessoDAO;
import factory.ConnectionFactory;
import modelo.NivelAcesso;

public class NivelAcessoController {

	private NivelAcessoDAO nivelAcessoDAO;

	public NivelAcessoController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.nivelAcessoDAO = new NivelAcessoDAO(connection);
	}

	public List<NivelAcesso> listar() {

		return this.nivelAcessoDAO.listar();

	}

	public String alterar(Long id, String nivelAcesso) {

		return this.nivelAcessoDAO.alterar(id, nivelAcesso);

	}

	public boolean deletar(Long id) {

		return this.nivelAcessoDAO.deletar(id);

	}

	public String salvar(NivelAcesso nivelAcesso) {

		return this.nivelAcessoDAO.salvar(nivelAcesso);
		
	}


}
