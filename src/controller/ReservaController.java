package controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import DAO.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;
import modelo.ReservaComCliente;
import views.ReservasFrame;

public class ReservaController {

	private ReservaDAO reservaDAO;

	public ReservaController() {

		Connection connection = new ConnectionFactory().recuperarConexao();
		this.reservaDAO = new ReservaDAO(connection);

	}

	public Double calculaTotalReserva(LocalDate dataEntrada, LocalDate dataSaida, Long tipoHabitacaoId) {

		return reservaDAO.calculaTotalReserva(dataEntrada, dataSaida, tipoHabitacaoId);

	}

	public static void mensaje(String mensaje) {

		ReservasFrame.mensaje(mensaje);
	}

	public Long ultimaReserva() {

		return reservaDAO.ultimaReserva();
	}

	public void salvarReserva(Reserva reserva) {

		reservaDAO.salvarReserva(reserva);

	}

	public Reserva buscarReserva(String codigoReserva) {

		return reservaDAO.buscarReserva(codigoReserva);

	}

	public void deletarReserva(String id) {

		reservaDAO.deletarReserva(id);
	}

	public void alterarReserva(Reserva reserva) {

		reservaDAO.alterarReserva(reserva);

	}

	public List<Reserva> listaReservas(LocalDate dataEntrada, LocalDate dataSaida) {

		return reservaDAO.listaReservas(dataEntrada, dataSaida);
		
	}

	public String mostrarArrecadacao(LocalDate dataEntrada, LocalDate dataSaida) {

		return reservaDAO.mostrarArrecadacao(dataEntrada, dataSaida);
		
	}

	public List<ReservaComCliente> listaReservasComCliente(LocalDate dataEntrada, LocalDate dataSaida) {

		return reservaDAO.listaReservasComCliente(dataEntrada, dataSaida)
				;
	}

	public String mostrarReservasApagadas(LocalDate dataEntrada, LocalDate dataSaida) {

		return reservaDAO.mostrarReservasApagadas(dataEntrada, dataSaida);
		
	}

	public void ativarReserva(String codigoReserva) {

		reservaDAO.ativarReserva(codigoReserva);
	
	}

	public Long getClienteId(String codigoReserva) {

		return reservaDAO.getClienteId(codigoReserva);
	
	}

	public void deletarDefinitivo(String codigoReserva) {
		
		reservaDAO.deletarDefinitivo(codigoReserva);
		
	}
}
