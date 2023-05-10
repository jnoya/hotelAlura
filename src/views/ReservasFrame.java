package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import javax.swing.JTextField;

import controller.FormaPagamentoController;
import controller.TipoHabitacaoController;
import controller.ReservaController;
import modelo.FormaPagamento;
import modelo.Reserva;
import modelo.TipoHabitacao;

import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.awt.Toolkit;

import java.util.List;
import java.util.Random;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

public class ReservasFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panel, header, panel_1;
	private JTextField txtValor;
	private DatePicker dataEntrada = new DatePicker();
	private DatePicker dataSaida = new DatePicker();
	private JComboBox<FormaPagamento> comboBoxFormaPagamento;
	int xMouse, yMouse;
	private JButton btnAtras, btnExit, btnProximo;
	private JLabel labelAtras, logo, lblValorSimbolo, imagenFondo, lblTitulo, lblCheckIn, lblTipoHabitacao,
			lblFormaPago, lblValor;
	private JSeparator separator_1_2, separator_1_1, separator1_4, separator_1, separator_1_3;
	private FormaPagamentoController formaPagamentoController = new FormaPagamentoController();
	private TipoHabitacaoController tipoHabitacaoController = new TipoHabitacaoController();
	private ReservaController reservaController = new ReservaController();
	private JLabel lblCheckOut;
	private JComboBox<TipoHabitacao> comboBoxTipoHabitacao;
	private JLabel lblExit;
	private JLabel lblSeguinte;
	private String codigoReserva;
	private boolean indicador = false;
	private Double totalReserva;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservasFrame frame = new ReservasFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReservasFrame() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasFrame.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(-12, -29, 918, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		header = new JPanel();
		header.setBounds(15, 30, 895, 36);
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
		panel.add(header);

		btnAtras = new JButton();
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
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));

		lblExit = new JLabel("X");
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setFont(new Font("Roboto", Font.PLAIN, 23));
		lblExit.setBounds(844, 0, 53, 36);
		header.add(lblExit);

		btnExit = new JButton();
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal principal = new MenuPrincipal();
				principal.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setBackground(Color.red);
				lblExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnExit.setBackground(Color.white);
				lblExit.setForeground(Color.black);
			}

		});
		btnExit.setLayout(null);
		btnExit.setBackground(Color.WHITE);
		btnExit.setBounds(844, 0, 53, 36);
		header.add(btnExit);

		panel_1 = new JPanel();
		panel_1.setBounds(420, 65, 493, 500);
		panel_1.setBackground(new Color(12, 138, 199));
		panel_1.setLayout(null);

		logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(ReservasFrame.class.getResource("/imagenes/Ha-100px.png")));

		imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 490, 360);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(ReservasFrame.class.getResource("/imagenes/reservas-img-3.png")));

		panel.add(panel_1);

		lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setBounds(109, 65, 250, 36);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 20));
		panel.add(lblTitulo);

		lblTipoHabitacao = new JLabel("TIPO DE HABITAÇÃO");
		lblTipoHabitacao.setForeground(SystemColor.textInactiveText);
		lblTipoHabitacao.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblTipoHabitacao.setBounds(68, 101, 230, 20);
		panel.add(lblTipoHabitacao);

		comboBoxTipoHabitacao = new JComboBox<TipoHabitacao>();
		comboBoxTipoHabitacao.setFont(new Font("Roboto", Font.PLAIN, 16));
		comboBoxTipoHabitacao.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		comboBoxTipoHabitacao.setBackground(Color.WHITE);
		comboBoxTipoHabitacao.setBounds(62, 128, 289, 30);
		comboBoxTipoHabitacao.addItem(new TipoHabitacao(0l, "Selecione", true));
		List<TipoHabitacao> tiposHabitacao = this.tipoHabitacaoController.listar();
		for (TipoHabitacao tipoHabitacao : tiposHabitacao) {

			comboBoxTipoHabitacao.addItem(tipoHabitacao);

		}
		comboBoxTipoHabitacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (indicador) {
					indicador = false;
					txtValor.setText("0");
					setTotalReserva();
				}

			}

		});
		panel.add(comboBoxTipoHabitacao);

		separator1_4 = new JSeparator();
		separator1_4.setForeground(new Color(0, 128, 255));
		separator1_4.setBackground(new Color(0, 128, 255));
		separator1_4.setBounds(68, 325, 289, 2);
		panel.add(separator1_4);

		lblCheckIn = new JLabel("DATA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 180, 200, 20);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblCheckIn);

		dataEntrada.setFont(new Font("Roboto", Font.PLAIN, 16));
		dataEntrada.setSize(190, 30);
		dataEntrada.setLocation(68, 207);
		dataEntrada.setDate(LocalDate.now());

		dataEntrada.addDateChangeListener(new DateChangeListener() {

			@Override
			public void dateChanged(DateChangeEvent event) {

				if (indicador) {
					txtValor.setText("0");
					setTotalReserva();

				}

			}

		});
		panel.add(dataEntrada);

		separator_1_2 = new JSeparator();
		separator_1_2.setForeground(SystemColor.textHighlight);
		separator_1_2.setBounds(68, 167, 289, 2);
		separator_1_2.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_2);

		lblCheckOut = new JLabel("DATA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblCheckOut.setBounds(68, 259, 200, 20);
		panel.add(lblCheckOut);

		dataSaida.setFont(new Font("Roboto", Font.PLAIN, 16));
		dataSaida.setSize(190, 30);
		dataSaida.setLocation(68, 286);
		dataSaida.setDate(LocalDate.now());

		dataSaida.addDateChangeListener(new DateChangeListener() {

			@Override
			public void dateChanged(DateChangeEvent event) {

				txtValor.setText("0");
				setTotalReserva();

			}

		});
		panel.add(dataSaida);

		separator_1_1 = new JSeparator();
		separator_1_1.setForeground(SystemColor.textHighlight);
		separator_1_1.setBounds(68, 246, 289, 2);
		separator_1_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1_1);

		lblValorSimbolo = new JLabel("$");
		lblValorSimbolo.setBounds(80, 368, 17, 25);
		lblValorSimbolo.setForeground(SystemColor.textHighlight);
		lblValorSimbolo.setFont(new Font("Roboto", Font.BOLD, 17));
		panel.add(lblValorSimbolo);

		lblValor = new JLabel("VALOR DA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(68, 338, 200, 20);
		lblValor.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(lblValor);

		txtValor = new JTextField();
		txtValor.setBackground(SystemColor.text);
		txtValor.setHorizontalAlignment(SwingConstants.CENTER);
		txtValor.setForeground(Color.BLACK);
		txtValor.setBounds(110, 368, 220, 25);
		txtValor.setEditable(false);
		txtValor.setFont(new Font("Roboto Black", Font.BOLD, 17));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtValor.setText("0");
		panel.add(txtValor);
		txtValor.setColumns(10);

		separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.textHighlight);
		separator_1.setBounds(68, 404, 289, 2);
		separator_1.setBackground(SystemColor.textHighlight);
		panel.add(separator_1);

		lblFormaPago = new JLabel("FORMA DE PAGAMENTO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 417, 230, 20);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		panel.add(lblFormaPago);

		comboBoxFormaPagamento = new JComboBox<FormaPagamento>();
		comboBoxFormaPagamento.setBounds(68, 444, 289, 30);
		comboBoxFormaPagamento.setBackground(SystemColor.text);
		comboBoxFormaPagamento.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		comboBoxFormaPagamento.setFont(new Font("Roboto", Font.PLAIN, 16));
		comboBoxFormaPagamento.addItem(new FormaPagamento(0l, "Selecione", true));
		List<FormaPagamento> formasPagamento = this.formaPagamentoController.listar();
		for (FormaPagamento formaPagamento : formasPagamento) {

			comboBoxFormaPagamento.addItem(formaPagamento);

		}
		panel.add(comboBoxFormaPagamento);

		separator_1_3 = new JSeparator();
		separator_1_3.setForeground(SystemColor.textHighlight);
		separator_1_3.setBackground(SystemColor.textHighlight);
		separator_1_3.setBounds(68, 483, 289, 2);
		panel.add(separator_1_3);

		btnProximo = new JButton();
		btnProximo.setForeground(new Color(255, 255, 255));
		btnProximo.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnProximo.setText("PROXIMO");
		btnProximo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				FormaPagamento formaPagamento = (FormaPagamento) comboBoxFormaPagamento.getSelectedItem();
				TipoHabitacao tipoHabitacao = (TipoHabitacao) comboBoxTipoHabitacao.getSelectedItem();

				if (indicador && !formaPagamento.getFormaPagamento().equals("Selecione")) {

					Long ultimaReserva = reservaController.ultimaReserva();
					ultimaReserva += 1;
					codigoReserva = ultimaReserva.toString();
					String medio = getRandomString(5);
					codigoReserva = "RAlura" + medio + codigoReserva;
					Reserva reserva = new Reserva(null, tipoHabitacao.getId(), dataEntrada.getDate(),
							dataSaida.getDate(), totalReserva, formaPagamento.getId(), codigoReserva, null, true);
					ClientesFrame registro = new ClientesFrame(reserva);
					registro.setVisible(true);
					dispose();

				} else {
					JOptionPane.showMessageDialog(null, "Deve preencher todos os campos.");
				}

			}

			private String getRandomString(int i) {

				byte[] bytearray = new byte[256];
				String mystring;
				StringBuffer thebuffer;
				String theAlphaNumericS;

				new Random().nextBytes(bytearray);

				mystring = new String(bytearray, Charset.forName("UTF-8"));

				thebuffer = new StringBuffer();

				theAlphaNumericS = mystring.replaceAll("[^A-Z0-9]", "");

				for (int m = 0; m < theAlphaNumericS.length(); m++) {

					if (Character.isLetter(theAlphaNumericS.charAt(m)) && (i > 0)
							|| Character.isDigit(theAlphaNumericS.charAt(m)) && (i > 0)) {

						thebuffer.append(theAlphaNumericS.charAt(m));
						i--;
					}
				}

				return thebuffer.toString();
			}

		});
		btnProximo.setLayout(null);
		btnProximo.setBackground(SystemColor.textHighlight);
		btnProximo.setBounds(215, 500, 142, 35);
		btnProximo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panel.add(btnProximo);

		lblSeguinte = new JLabel("PROXIMO");
		lblSeguinte.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeguinte.setForeground(Color.WHITE);
		lblSeguinte.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblSeguinte.setBounds(215, 500, 142, 35);
		btnProximo.add(lblSeguinte);

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

	public static void mensaje(String mensaje) {

		JOptionPane.showMessageDialog(null, mensaje);

	}

	public void setTotalReserva() {

		TipoHabitacao tipoHabitacao = (TipoHabitacao) comboBoxTipoHabitacao.getSelectedItem();
		if (!tipoHabitacao.getDescricao().equals("Selecione")) {

			totalReserva = reservaController.calculaTotalReserva(dataEntrada.getDate(), dataSaida.getDate(),
					tipoHabitacao.getId());
			txtValor.setText(Double.toString(totalReserva));
			indicador = true;

		} else {

			JOptionPane.showMessageDialog(null, "Primeiro tem que escolher o tipo de habitação");

		}
	}

}
