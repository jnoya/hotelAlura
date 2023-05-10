package controller;

import java.sql.Connection;
import java.util.List;

import DAO.NacionalidadeDAO;
import factory.ConnectionFactory;
import modelo.Nacionalidade;

public class NacionalidadeController {

	private NacionalidadeDAO nacionalidadeDAO;

	public NacionalidadeController() {

		Connection connection = new ConnectionFactory().recuperarConexao();
		this.nacionalidadeDAO = new NacionalidadeDAO(connection);
	
	}

	public String alterar(Long id, String nacionalidade) {

		return this.nacionalidadeDAO.alterar(id, nacionalidade);

	}

	public boolean deletar(Long id) {

		return this.nacionalidadeDAO.deletar(id);

	}

	public List<Nacionalidade> listar() {

		return this.nacionalidadeDAO.listar();

	}

	public String salvar(Nacionalidade nacionalidade) {

		return this.nacionalidadeDAO.salvar(nacionalidade);

	}

	public String getNacionalidadeComId(Long nacionalidadeId) {

		return nacionalidadeDAO.getNacionalidadeComId(nacionalidadeId);
	}

	public Long getIdComNacionalidade(String nacionalidade) {

		return nacionalidadeDAO.getIdComNacionalidade(nacionalidade);
		
	}

}
