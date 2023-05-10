package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;

import controller.ClienteController;
import controller.NacionalidadeController;
import modelo.Cliente;
import modelo.Reserva;

public class BuscaClienteFrame extends JFrame {
	public BuscaClienteFrame() {
	}

	private static final long serialVersionUID = 1L;

	private JLabel lblSobrenome, lblTelefone, lblDataNascimento;
	private JTextField txtSobrenome, txtTelefone;
	private JButton btnBuscaTelefone, btnBuscaSobrenome, btnBuscaDataNascimento;
	private DefaultTableModel modelo;
	private JTable tabela;
	private DatePicker data = new DatePicker();
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private ClienteController clienteController = new ClienteController();
	private NacionalidadeController nacionalidadeController = new NacionalidadeController();


	public BuscaClienteFrame(Reserva reserva) {

		super("Clientes");
		setResizable(false);
		setTitle("Busca de Clientes");

		Container container = getContentPane();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		lblSobrenome = new JLabel("Sobrenome do Cliente");
		lblSobrenome.setBounds(10, 15, 190, 15);
		lblTelefone = new JLabel("Telefone do Cliente");
		lblTelefone.setBounds(10, 60, 190, 15);
		lblDataNascimento = new JLabel("Data de nascimento do Cliente");
		lblDataNascimento.setBounds(10, 110, 190, 15);
		lblSobrenome.setForeground(Color.BLACK);
		lblTelefone.setForeground(Color.BLACK);
		lblDataNascimento.setForeground(Color.BLACK);

		txtSobrenome = new JTextField();
		txtSobrenome.setBounds(10, 35, 190, 20);
		txtTelefone = new JTextField();
		txtTelefone.setBounds(10, 80, 190, 20);

		btnBuscaTelefone = new JButton("Buscar");
		btnBuscaTelefone.setBounds(225, 80, 90, 20);
		btnBuscaSobrenome = new JButton("Buscar");
		btnBuscaSobrenome.setBounds(225, 35, 90, 20);

		tabela = new JTable(new DefaultTableModel(new Object[][] {}, new String[] { "Identificador do Cliente", "Nome",
				"Sobrenome", "Data de Nascimento", "Código de Nacionalidade", "Telefone" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tabela.getColumnModel().getColumn(0).setPreferredWidth(124);
		modelo = (DefaultTableModel) tabela.getModel();
		tabela.setBounds(10, 185, 760, 300);
		setSize(889, 600);
		setVisible(true);
		setLocationRelativeTo(null);

		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(10, 190, 850, 250);
		data.setBounds(10, 132, 190, 25);

		data.setFont(new Font("Roboto", Font.PLAIN, 16));
		data.setDate(LocalDate.now());

		btnBuscaDataNascimento = new JButton("Buscar");
		btnBuscaDataNascimento.setBounds(225, 135, 90, 20);
		container.setLayout(null);
		container.add(lblSobrenome);
		container.add(lblTelefone);
		container.add(lblDataNascimento);
		container.add(txtSobrenome);
		container.add(txtTelefone);
		container.add(btnBuscaTelefone);
		container.add(btnBuscaSobrenome);
		container.add(scroll);
		container.add(data);
		container.add(btnBuscaDataNascimento);

		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.setBounds(496, 80, 100, 30);
		container.add(btnSelecionar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(496, 132, 100, 30);
		getContentPane().add(btnVoltar);

		btnBuscaTelefone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				clientes = clienteController.buscarTelefone(txtTelefone.getText());
				limparTabela();
				if (!clientes.isEmpty()) {
					preencherTabela(clientes);
				} else {
					JOptionPane.showMessageDialog(container, "Não existem clientes com esse telefone");
				}

			}

		});

		btnBuscaSobrenome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				clientes = clienteController.buscarSobrenome(txtSobrenome.getText());
				limparTabela();
				if (!clientes.isEmpty()) {
					preencherTabela(clientes);
				} else {
					JOptionPane.showMessageDialog(container, "Não existem clientes com esser sobrenome");
				}

			}

		});

		btnBuscaDataNascimento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				clientes = clienteController.buscarDataNascimento(data.getDate());
				limparTabela();
				if (!clientes.isEmpty()) {
					preencherTabela(clientes);
				} else {
					JOptionPane.showMessageDialog(container, "Não existem clientes com esa data de nascimento");
				}

			}

		});

		btnSelecionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (tabela.getSelectedRow() != -1 && tabela.getSelectedColumn() == 0) {
	
					Cliente cliente = new Cliente((Long) modelo.getValueAt(tabela.getSelectedRow(), 0),
							(String) modelo.getValueAt(tabela.getSelectedRow(), 1),
							(String) modelo.getValueAt(tabela.getSelectedRow(), 2),
							(LocalDate) modelo.getValueAt(tabela.getSelectedRow(), 3),
							(Long) modelo.getValueAt(tabela.getSelectedRow(), 4),
							(String) modelo.getValueAt(tabela.getSelectedRow(), 5), true);
					reserva.setClienteId((Long) modelo.getValueAt(tabela.getSelectedRow(),0));
					ClientesFrame clientesFrame = new ClientesFrame(reserva);
					CarregaDatos(clientesFrame, cliente, reserva);
					clientesFrame.setVisible(true);
					dispose();
	
				} else {

					JOptionPane.showMessageDialog(container, "Por favor, selecionar o código de Cliente");

				}

			}

		});

		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ClientesFrame registro = new ClientesFrame(reserva);
				registro.setVisible(true);
				dispose();

			}

		});

	}

	private void limparTabela() {

		modelo.getDataVector().clear();

	}

	private void preencherTabela(List<Cliente> clientes) {

		try {
			for (Cliente cliente : clientes) {

				modelo.addRow(new Object[] { cliente.getId(), cliente.getNome(), cliente.getSobrenome(),
						cliente.getDataNascimento(), cliente.getNacionalidadeId(), cliente.getTelefone() });

			}

		} catch (Exception e) {

			throw e;

		}

	}

	private void CarregaDatos(ClientesFrame clientesFrame, Cliente cliente, Reserva reserva) {

		clientesFrame.setTxtNome(cliente.getNome());
		clientesFrame.setTxtSobrenome(cliente.getSobrenome());
		clientesFrame.setDataNascimento(cliente.getDataNascimento());
		String nacionalidadeS = nacionalidadeController.getNacionalidadeComId(cliente.getNacionalidadeId());
		clientesFrame.setComboBoxNacionalidade(nacionalidadeS);
		clientesFrame.setTxtTelefone(cliente.getTelefone());
		clientesFrame.setLblCodigoReserva(reserva.getCodigoReserva());

	}
}
