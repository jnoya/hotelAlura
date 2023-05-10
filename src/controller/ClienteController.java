package controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import DAO.ClienteDAO;
import factory.ConnectionFactory;
import modelo.Cliente;
import modelo.ClienteComReserva;

public class ClienteController {

	private ClienteDAO clienteDAO;

	public ClienteController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.clienteDAO = new ClienteDAO(connection);
	}

	public void salvarCliente(Cliente cliente) {

		clienteDAO.salvarCliente(cliente);

	}

	public List<ClienteComReserva> buscarHospede(String sobrenome) {

		return clienteDAO.buscarHospede(sobrenome);

	}

	public boolean deletarHospede(Long id) {

		return clienteDAO.deletarHospede(id);
		
	}

	public String alterar(ClienteComReserva cliente) {

		return clienteDAO.alterar(cliente);
	
	}

	public List<Cliente> buscarTelefone(String telefone) {
		
		return clienteDAO.buscarTelefone(telefone);
		
	}

	public List<Cliente> buscarSobrenome(String sobrenome) {
		
		return clienteDAO.buscarSobrenome(sobrenome);
		
	}

	public List<Cliente> buscarDataNascimento(LocalDate dataNascimento) {
		
		return clienteDAO.buscarDataNascimento(dataNascimento);
		
	}

	public void ativarCliente(Long id) {

		clienteDAO.ativaCliente(id);
		
	}

}
