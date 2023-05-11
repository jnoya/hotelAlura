package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;

import controller.ClienteController;
import controller.ReservaController;
import modelo.Reserva;
import modelo.ReservaComCliente;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("serial")
public class ListadosFrame extends JFrame {

	private JPanel contentPane, header;
	private JTable tbRegistrosApagados;
	private JTable tbArrecadacaoPorPeriodo;
	private DefaultTableModel modeloArrecadacao;
	private DefaultTableModel modeloReservasApagadas;
	private JLabel labelAtras, lblComent, lblTitulo, lblDesde, lblAte, lblPeriodo;
	private JLabel labelExit, lblTotal, lblLogo;
	int xMouse, yMouse;
	private DatePicker dataEntrada = new DatePicker();
	private DatePicker dataSaida = new DatePicker();
	private JTextField textTotal;
	private ReservaController reservaController = new ReservaController();
	private ClienteController clienteController = new ClienteController();
	private JTabbedPane panel;
	private JScrollPane scrollArrecadacao, scrollReservasApagadas;
	private JButton btnAtras, btnExit, btnListar, btnEditar, btnDeletar;

	public ListadosFrame() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadosFrame.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		header = new JPanel();
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

		btnAtras = new JButton();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuAdministracao administracao = new MenuAdministracao();
				administracao.setVisible(true);
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

		btnExit = new JButton();
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnExit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Quando o usuário remove o mouse do botão, ele retornará ao estado
				// original
				btnExit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnExit.setLayout(null);
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(857, 0, 53, 36);
		header.add(btnExit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnExit.add(labelExit);

		lblTitulo = new JLabel("LISTADOS");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 50, 130, 30);
		contentPane.add(lblTitulo);

		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(ListadosFrame.class.getResource("/imagenes/Ha-100px.png")));
		lblLogo.setBounds(56, 51, 104, 107);
		contentPane.add(lblLogo);

		lblPeriodo = new JLabel("Período");
		lblPeriodo.setForeground(new Color(0, 128, 255));
		lblPeriodo.setFont(new Font("Roboto", Font.BOLD, 18));
		lblPeriodo.setBounds(190, 110, 80, 30);
		contentPane.add(lblPeriodo);

		lblDesde = new JLabel("DESDE");
		lblDesde.setFont(new Font("Roboto", Font.BOLD, 14));
		lblDesde.setBounds(270, 110, 60, 30);
		contentPane.add(lblDesde);
		dataEntrada.getComponentDateTextField().setFont(new Font("Dialog", Font.PLAIN, 16));

		dataEntrada.setFont(new Font("Roboto", Font.PLAIN, 16));
		dataEntrada.setSize(190, 30);
		dataEntrada.setLocation(330, 110);
		dataEntrada.setDate(LocalDate.now());
		contentPane.add(dataEntrada);

		lblAte = new JLabel("ATÉ");
		lblAte.setFont(new Font("Roboto", Font.BOLD, 14));
		lblAte.setBounds(530, 110, 35, 30);
		contentPane.add(lblAte);

		dataSaida.setFont(new Font("Roboto", Font.PLAIN, 16));
		dataSaida.setSize(190, 30);
		dataSaida.setLocation(565, 110);
		dataSaida.setDate(LocalDate.now());
		contentPane.add(dataSaida);

		btnListar = new JButton();
		btnListar.setText("LISTAR");
		btnListar.setForeground(new Color(255, 255, 255));
		btnListar.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnListar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (panel.getSelectedIndex() == 0) {

					btnEditar.setVisible(false);
					btnDeletar.setVisible(false);
					textTotal.setText("");
					lblComent.setText("");
					modeloArrecadacao.getDataVector().clear();
					carregaListaReservas();
					String resultado = reservaController.mostrarArrecadacao(dataEntrada.getDate(), dataSaida.getDate());
					textTotal.setText(resultado);
					lblComent.setText("Reais");

				}

				if (panel.getSelectedIndex() == 1) {

					btnEditar.setVisible(true);
					btnDeletar.setVisible(true);
					textTotal.setText("");
					lblComent.setText("");
					modeloReservasApagadas.getDataVector().clear();
					carregaReservasApagadas();
					String quantidade = reservaController.mostrarReservasApagadas(dataEntrada.getDate(),
							dataSaida.getDate());
					textTotal.setText(quantidade);
					lblComent.setText("Reservas");

				}

			}

		});

		btnListar.setLayout(null);
		btnListar.setBackground(new Color(12, 138, 199));
		btnListar.setBounds(760, 110, 100, 30);
		btnListar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnListar);

		lblTotal = new JLabel("Total");
		lblTotal.setForeground(new Color(0, 128, 255));
		lblTotal.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTotal.setBounds(290, 150, 50, 30);
		contentPane.add(lblTotal);

		textTotal = new JTextField("");
		textTotal.setFont(new Font("Dialog", Font.BOLD, 18));
		textTotal.setForeground(new Color(255, 0, 0));
		textTotal.setBounds(340, 150, 86, 30);
		contentPane.add(textTotal);
		textTotal.setColumns(10);

		lblComent = new JLabel("");
		lblComent.setForeground(new Color(0, 128, 255));
		lblComent.setFont(new Font("Dialog", Font.BOLD, 16));
		lblComent.setBounds(436, 150, 80, 30);
		contentPane.add(lblComent);

		panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 190, 865, 280);
		contentPane.add(panel);

		tbArrecadacaoPorPeriodo = new JTable();
		tbArrecadacaoPorPeriodo.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo da Reserva", "Check In", "Check Out", "Valor da Reserva", "Forma de Pagamento"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbArrecadacaoPorPeriodo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbArrecadacaoPorPeriodo.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloArrecadacao = (DefaultTableModel) tbArrecadacaoPorPeriodo.getModel();
		scrollArrecadacao = new JScrollPane(tbArrecadacaoPorPeriodo);
		panel.addTab("Arrecadação por periodo",
				new ImageIcon(ListadosFrame.class.getResource("/imagenes/reservado.png")), scrollArrecadacao, null);
		scrollArrecadacao.setVisible(true);

		tbRegistrosApagados = new JTable();
		tbRegistrosApagados.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo da reserva", "Nome", "Sobrenome", "Check In", "Check Out", "Valor da Reserva", "Ativo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbRegistrosApagados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbRegistrosApagados.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloReservasApagadas = (DefaultTableModel) tbRegistrosApagados.getModel();
		scrollReservasApagadas = new JScrollPane(tbRegistrosApagados);
		panel.addTab("Reservas apagadas", new ImageIcon(ListadosFrame.class.getResource("/imagenes/pessoas.png")),
				scrollReservasApagadas, null);
		scrollReservasApagadas.setVisible(true);

		btnEditar = new JButton();
		btnEditar.setForeground(new Color(255, 255, 255));
		btnEditar.setText("ATIVAR");
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEditar.setVisible(false);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (tbRegistrosApagados.getSelectedRow() != -1 && tbRegistrosApagados.getSelectedColumn() == 0) {

					textTotal.setText("");
					lblComent.setText("");
					Long id = reservaController.getClienteId(
							(String) modeloReservasApagadas.getValueAt(tbRegistrosApagados.getSelectedRow(), 0));
					clienteController.ativarCliente(id);
					reservaController.ativarReserva(
							(String) modeloReservasApagadas.getValueAt(tbRegistrosApagados.getSelectedRow(), 0));
					modeloReservasApagadas.getDataVector().clear();
					carregaReservasApagadas();
					String quantidade = reservaController.mostrarReservasApagadas(dataEntrada.getDate(),
							dataSaida.getDate());
					textTotal.setText(quantidade);
					lblComent.setText("Reservas");
					JOptionPane.showMessageDialog(contentPane, "Reserva ativada com sucesso!");

				} else {

					JOptionPane.showMessageDialog(contentPane, "Por favor, selecionar o ID");

				}

			}

		});
		contentPane.add(btnEditar);

		btnDeletar = new JButton();
		btnDeletar.setText("DELETAR");
		btnDeletar.setForeground(new Color(255, 255, 255));
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnDeletar.setVisible(false);
		btnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (tbRegistrosApagados.getSelectedRow() != -1 && tbRegistrosApagados.getSelectedColumn() == 0) {

					textTotal.setText("");
					lblComent.setText("");
					reservaController.deletarDefinitivo(
							(String) modeloReservasApagadas.getValueAt(tbRegistrosApagados.getSelectedRow(), 0));
					modeloReservasApagadas.getDataVector().clear();
					carregaReservasApagadas();
					String quantidade = reservaController.mostrarReservasApagadas(dataEntrada.getDate(),
							dataSaida.getDate());
					textTotal.setText(quantidade);
					lblComent.setText("Reservas");
					JOptionPane.showMessageDialog(contentPane, "Reserva deletada definitivamente com sucesso!");

				} else {

					JOptionPane.showMessageDialog(contentPane, "Por favor, selecionar o ID");

				}

			}

		});
		contentPane.add(btnDeletar);

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

	private void carregaListaReservas() {

		List<Reserva> reservas = reservaController.listaReservas(dataEntrada.getDate(), dataSaida.getDate());
		try {
			for (Reserva reserva : reservas) {

				modeloArrecadacao.addRow(new Object[] { reserva.getCodigoReserva(), reserva.getDataEntrada(),
						reserva.getDataSaida(), reserva.getValorReserva(), reserva.getFormaPagamentoId() });

			}

		} catch (Exception e) {

			throw e;

		}

	}

	private void carregaReservasApagadas() {

		List<ReservaComCliente> reservasComCliente = reservaController.listaReservasComCliente(dataEntrada.getDate(),
				dataSaida.getDate());
		try {
			for (ReservaComCliente reservaComCliente : reservasComCliente) {

				modeloReservasApagadas.addRow(new Object[] { reservaComCliente.getCodigoReserva(),
						reservaComCliente.getNome(), reservaComCliente.getSobrenome(),
						reservaComCliente.getDataEntrada(), reservaComCliente.getDataSaida(),
						reservaComCliente.getValorReserva(), reservaComCliente.getAtivo() });

			}

		} catch (Exception e) {

			throw e;

		}

	}

}
