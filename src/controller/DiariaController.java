package controller;

import java.sql.Connection;
import java.util.List;

import DAO.DiariaDAO;
import factory.ConnectionFactory;
import modelo.Diaria;

public class DiariaController {

	private DiariaDAO diariaDAO;

	public DiariaController() {
	
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.diariaDAO = new DiariaDAO(connection);
	
	}

	public void alterar(Long Id, Double valor) {

		this.diariaDAO.alterar(Id, valor);

	}

	public void deletar(Long id) {

		this.diariaDAO.deletar(id);

	}

	public List<Diaria> listar() {

		return this.diariaDAO.listar();

	}

	public String salvar(Diaria diaria) {

		return this.diariaDAO.salvar(diaria);

	}

	public String getTipoHabitacao(Long tipoHabitacaoId) {

		return this.diariaDAO.getTipoHabitacao(tipoHabitacaoId);
	}

}
