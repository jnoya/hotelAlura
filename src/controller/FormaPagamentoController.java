package controller;

import java.sql.Connection;
import java.util.List;

import DAO.FormaPagamentoDAO;
import factory.ConnectionFactory;
import modelo.FormaPagamento;

public class FormaPagamentoController {

	private FormaPagamentoDAO formaPagamentoDAO;

	public FormaPagamentoController() {

		Connection connection = new ConnectionFactory().recuperarConexao();
		this.formaPagamentoDAO = new FormaPagamentoDAO(connection);
	
	}

	public String alterar(Long id, String formaPagamento) {

		return this.formaPagamentoDAO.alterar(id, formaPagamento);
	
	}

	public boolean deletar(Long id) {

		return this.formaPagamentoDAO.deletar(id);

	}

	public List<FormaPagamento> listar() {

		return this.formaPagamentoDAO.listar();
	}

	public String salvar(FormaPagamento formaPagamento) {

		return this.formaPagamentoDAO.salvar(formaPagamento);

	}

	public String getFormaPagamentoComId(Long formaPagamentoId) {

		return formaPagamentoDAO.getFormaPagamentoComId(formaPagamentoId);

	}

	public Long getIdComFormaPagamento(String formaPagamento) {

		return formaPagamentoDAO.getIdComFormaPagamento(formaPagamento);
	}

}
