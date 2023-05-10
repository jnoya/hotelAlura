package controller;

import java.sql.Connection;
import java.util.List;

import DAO.TipoHabitacaoDAO;
import factory.ConnectionFactory;
import modelo.TipoHabitacao;

public class TipoHabitacaoController {

	private TipoHabitacaoDAO tipoHabitacaoDAO;

	public TipoHabitacaoController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.tipoHabitacaoDAO = new TipoHabitacaoDAO(connection);
	}

	public List<TipoHabitacao> listar() {

		return this.tipoHabitacaoDAO.listar();

	}

	public String alterar(Long id, String tipoHabitacao) {

		return this.tipoHabitacaoDAO.alterar(id, tipoHabitacao);

	}

	public boolean deletar(Long id) {

		return this.tipoHabitacaoDAO.deletar(id);

	}

	public String salvar(TipoHabitacao tipoHabitacao) {

		return this.tipoHabitacaoDAO.salvar(tipoHabitacao);
		
	}

	public String getDescricaoComId(Long tipoHabitacaoId) {

		return tipoHabitacaoDAO.getDescricaoComId(tipoHabitacaoId);

	}

	public Long getIdComDescricao(String descricao) {

		return tipoHabitacaoDAO.getIdComDescricao(descricao);
		
	
	}


}
