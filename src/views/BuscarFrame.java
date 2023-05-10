package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ClienteController;
import controller.FormaPagamentoController;
import controller.NacionalidadeController;
import controller.ReservaController;
import controller.TipoHabitacaoController;
import modelo.ClienteComReserva;

import modelo.Reserva;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SuppressWarnings("serial")
public class BuscarFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	private ClienteController clienteController = new ClienteController();
	private ReservaController reservaController = new ReservaController();
	private FormaPagamentoController formaPagamentoController = new FormaPagamentoController();
	private TipoHabitacaoController tipoHabitacaoController = new TipoHabitacaoController();
	private NacionalidadeController nacionalidadeController = new NacionalidadeController();
	private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarFrame buscarFrame = new BuscarFrame();
					buscarFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BuscarFrame() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscarFrame.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 12));
		tbReservas.setName("Reservas");
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Tipo de Habitação");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_tableReservas = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(BuscarFrame.class.getResource("/imagenes/reservado.png")),
				scroll_tableReservas, null);
		scroll_tableReservas.setVisible(true);

		tbHospedes = new JTable();
		tbHospedes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro. Cliente", "Nome", "Sobrenome", "D. Nascimento", "Nacionalidade", "Telefone", "Codigo Reserva", "Reserva"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbHospedes.getColumnModel().getColumn(0).setResizable(false);
		tbHospedes.getColumnModel().getColumn(0).setPreferredWidth(60);
		tbHospedes.getColumnModel().getColumn(1).setPreferredWidth(97);
		tbHospedes.getColumnModel().getColumn(2).setPreferredWidth(100);
		tbHospedes.getColumnModel().getColumn(3).setResizable(false);
		tbHospedes.getColumnModel().getColumn(5).setResizable(false);
		tbHospedes.getColumnModel().getColumn(5).setPreferredWidth(85);
		tbHospedes.getColumnModel().getColumn(6).setResizable(false);
		tbHospedes.getColumnModel().getColumn(6).setPreferredWidth(90);
		tbHospedes.getColumnModel().getColumn(7).setResizable(false);
		tbHospedes.getColumnModel().getColumn(7).setPreferredWidth(40);
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 12));
		tbReservas.setName("Clientes");
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("Huéspedes", new ImageIcon(BuscarFrame.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(BuscarFrame.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Quando o usuário remove o mouse do botão, ele retornará ao estado
				// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JButton btnBuscar = new JButton();
		btnBuscar.setLayout(null);
		btnBuscar.setBackground(new Color(12, 138, 199));
		btnBuscar.setBounds(748, 125, 122, 35);
		btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnBuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnBuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JButton btnAlterar = new JButton();
		btnAlterar.setLayout(null);
		btnAlterar.setBackground(new Color(12, 138, 199));
		btnAlterar.setBounds(635, 508, 122, 35);
		btnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnAlterar);

		JLabel lblAlterar = new JLabel("ALTERAR");
		lblAlterar.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlterar.setForeground(Color.WHITE);
		lblAlterar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblAlterar.setBounds(0, 0, 122, 35);
		btnAlterar.add(lblAlterar);

		JButton btnDeletar = new JButton();
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);

		JLabel lblDeletar = new JLabel("DELETAR");
		lblDeletar.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeletar.setForeground(Color.WHITE);
		lblDeletar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblDeletar.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblDeletar);
		setResizable(false);

		btnDeletar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (panel.getSelectedIndex() == 1) {

					String sobrenome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 2);
					deletarHospede();
					modeloHospedes.removeRow(tbHospedes.getSelectedRow());
					
					modeloHospedes.getDataVector().clear();
					List<ClienteComReserva> clientes = clienteController.buscarHospede(sobrenome);
					preencherTabelaHospedes(clientes);

				}

				if (panel.getSelectedIndex() == 0) {

					deletarReserva();
					modelo.removeRow(tbReservas.getSelectedRow());
					
				}

			}

		});

		btnAlterar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (panel.getSelectedIndex() == 1) {
					if (tbHospedes.getSelectedRow() != -1 && tbHospedes.getSelectedColumn() == 0) {

						alterarHospede();
						String sobrenome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 3);
						List<ClienteComReserva> clientes = clienteController.buscarHospede(sobrenome);
						modeloHospedes.getDataVector().clear();
						preencherTabelaHospedes(clientes);

					} else {

						JOptionPane.showMessageDialog(panel, "Por favor, selecionar o ID");

					}

				}

				if (panel.getSelectedIndex() == 0) {

					if (tbHospedes.getSelectedRow() != -1 && tbHospedes.getSelectedColumn() == 0) {

						alterarReserva();
						Reserva reserva = reservaController.buscarReserva((String) modelo
								.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn()));
						modelo.getDataVector().clear();
						preencherTabelaReservas(reserva);

					} else {

						JOptionPane.showMessageDialog(panel, "Por favor, selecionar o ID");

					}

				}

			}

		});

		btnBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (panel.getSelectedIndex() == 1) {

					if (txtBuscar.getText() != "") {

						List<ClienteComReserva> clientes = clienteController.buscarHospede(txtBuscar.getText());
						if (!clientes.isEmpty()) {

							modeloHospedes.getDataVector().clear();
							preencherTabelaHospedes(clientes);

						} else {

							JOptionPane.showMessageDialog(contentPane, "Nenhum hospede encontrado com esse sobrenome");

						}

					} else {

						JOptionPane.showMessageDialog(contentPane, "Debe proporcionar um sobrenome para a busca");

					}

				}

				if (panel.getSelectedIndex() == 0) {

					if (txtBuscar.getText() != "") {

						Reserva reserva = reservaController.buscarReserva(txtBuscar.getText());
						if (reserva != null) {

							modelo.getDataVector().clear();
							preencherTabelaReservas(reserva);

						} else {

							JOptionPane.showMessageDialog(contentPane, "Nenhuma reserva encontrada com esse código");

						}

					} else {

						JOptionPane.showMessageDialog(contentPane,
								"Debe proporcionar um código de reserva para a busca");

					}

				}

			}

		});

	}

	// Código que permite movimentar a janela pela tela seguindo a posição de "x" e
	// "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

	private void preencherTabelaHospedes(List<ClienteComReserva> clientes) {

		try {
			for (ClienteComReserva cliente : clientes) {

				String dn = formato.format(cliente.getDataNascimento());

				modeloHospedes.addRow(new Object[] { cliente.getId(), cliente.getNome(), cliente.getSobrenome(), dn,
						nacionalidadeController.getNacionalidadeComId(cliente.getNacionalidadeId()),
						cliente.getTelefone(), cliente.getCodigoReserva(), cliente.getAtivo() });

			}

		} catch (Exception e) {

			throw e;

		}

	}

	private void alterarHospede() {

		Long id = (Long) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 0);
		String nome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 1);
		String sobrenome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 2);
		LocalDate dataNascimento = LocalDate.parse((String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 3),
				formato);
		NacionalidadeController nacionalidadeController = new NacionalidadeController();
		Long nacionalidadeId = nacionalidadeController
				.getIdComNacionalidade((String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 4));
		String telefone = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 5);
		String codigoReserva = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 6);
		Boolean ativo = (Boolean) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 7);
		ClienteComReserva clienteComReserva = new ClienteComReserva(id, nome, sobrenome, dataNascimento,
				nacionalidadeId, telefone, codigoReserva, ativo);
		String mensaje = this.clienteController.alterar(clienteComReserva);
		JOptionPane.showMessageDialog(this, mensaje);

	}

	private void deletarHospede() {

		if (tbHospedes.getSelectedRow() != -1 && tbHospedes.getSelectedColumn() == 0) {

			Long id = (Long) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn());

			if (this.clienteController.deletarHospede(id)) {

				JOptionPane.showMessageDialog(this, "Cliente excluido com sucesso!");

			} else {

				JOptionPane.showMessageDialog(this, "Ainda tem registros em Reservas usando este Cliente!");

			}

		} else {

			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");

		}

	}

	private void preencherTabelaReservas(Reserva reserva) {

		String de = formato.format(reserva.getDataEntrada());
		String ds = formato.format(reserva.getDataSaida());
		modelo.addRow(new Object[] { reserva.getCodigoReserva(),
				tipoHabitacaoController.getDescricaoComId(reserva.getTipoHabitacaoId()), de, ds,
				reserva.getValorReserva(),
				formaPagamentoController.getFormaPagamentoComId(reserva.getFormaPagamentoId()) });

	}

	private void alterarReserva() {

		Reserva reservaAnt = (Reserva) reservaController
				.buscarReserva((String) modelo.getValueAt(tbReservas.getSelectedRow(), 0));
		Long id = reservaAnt.getId();
		Long tipoHabitacaoId = tipoHabitacaoController
				.getIdComDescricao((String) modelo.getValueAt(tbReservas.getSelectedRow(), 1));
		LocalDate dataEntrada = (LocalDate) modelo.getValueAt(tbReservas.getSelectedRow(), 2);
		LocalDate dataSaida = (LocalDate) modelo.getValueAt(tbReservas.getSelectedRow(), 3);
		Double valorReserva = (Double) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
		Long formaPagamentoId = formaPagamentoController
				.getIdComFormaPagamento((String) modelo.getValueAt(tbReservas.getSelectedRow(), 5));
		String codigoReserva = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 0);
		Long clienteId = reservaAnt.getClienteId();
		Reserva reserva = new Reserva(id, tipoHabitacaoId, dataEntrada, dataSaida, valorReserva, formaPagamentoId,
				codigoReserva, clienteId, true);
		reservaController.alterarReserva(reserva);
		JOptionPane.showMessageDialog(this, "Reserva alterada com sucesso");

	}

	private void deletarReserva() {

		if (tbReservas.getSelectedRow() != -1 && tbReservas.getSelectedColumn() == 0) {

			String id = (String) modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn());
			this.reservaController.deletarReserva(id);
			JOptionPane.showMessageDialog(this, "Reserva excluida com sucesso!");
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");

		}

	}

}