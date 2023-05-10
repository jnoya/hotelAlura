package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.github.lgooddatepicker.components.DatePicker;
import controller.ClienteController;
import controller.NacionalidadeController;
import controller.ReservaController;

import javax.swing.JTextField;
import java.awt.Color;

import modelo.Cliente;
import modelo.Nacionalidade;
import modelo.Reserva;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class ClientesFrame extends JFrame {

	private JPanel contentPane, header, panel;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JTextField txtTelefone;
	private DatePicker dataNascimento = new DatePicker();
	private JComboBox<Nacionalidade> comboBoxNacionalidade;
	private JLabel lblExit, lblNome, lblSobrenome, lblDataNascimento, lblSalvar, lblImagemFundo, lblCodigoReserva;
	private JLabel lblAtras, lblNacionalidade, lblTelefone, lblTitulo, lblNumeroReserva, lblLogo, lblComboBoxNacionalidade;
	private JButton btnExit, btnAtras, btnSalvar;
	int xMouse, yMouse;
	private NacionalidadeController nacionalidadeController;
	private ClienteController clienteController = new ClienteController();
	private ReservaController reservaController = new ReservaController();
	private JSeparator separator_1_2, separator_1_2_1, separator_1_2_2, separator_1_2_3, separator_1_2_4,
			separator_1_2_5;
	private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private JButton btnBuscar;
	

	/**
	 * Create the frame.
	 */
	public ClientesFrame(Reserva reserva) {

		setIconImage(Toolkit.getDefaultToolkit().getImage(ClientesFrame.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);

		this.nacionalidadeController = new NacionalidadeController();
		header = new JPanel();
		header.setBounds(-54, 0, 910, 36);
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

		btnExit = new JButton();
		btnExit.setBounds(857, 0, 53, 36);
		contentPane.add(btnExit);
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
		btnExit.setBackground(Color.white);

		lblExit = new JLabel("X");
		lblExit.setBounds(0, 0, 53, 36);
		btnExit.add(lblExit);
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setForeground(SystemColor.black);
		lblExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		header.setLayout(null);
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		btnAtras = new JButton();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReservasFrame reservas = new ReservasFrame();
				reservas.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				lblAtras.setForeground(Color.black);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				lblAtras.setForeground(Color.white);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(new Color(12, 138, 199));
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		lblAtras = new JLabel("<");
		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtras.setForeground(Color.WHITE);
		lblAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		lblAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(lblAtras);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNome.setBounds(540, 150, 285, 30);
		txtNome.setBackground(Color.WHITE);
		txtNome.setColumns(10);
		txtNome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNome);

		txtSobrenome = new JTextField();
		txtSobrenome.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtSobrenome.setBounds(540, 220, 285, 30);
		txtSobrenome.setColumns(10);
		txtSobrenome.setBackground(Color.WHITE);
		txtSobrenome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtSobrenome);

		dataNascimento.setBounds(540, 290, 285, 30);
		dataNascimento.setDate(LocalDate.now());
		contentPane.add(dataNascimento);
		lblComboBoxNacionalidade = new JLabel();
		lblComboBoxNacionalidade.setBounds(540,360,289,30);
		lblComboBoxNacionalidade.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblComboBoxNacionalidade.setVisible(false);
		contentPane.add(lblComboBoxNacionalidade);
		comboBoxNacionalidade = new JComboBox<Nacionalidade>();
		comboBoxNacionalidade.setBounds(540, 360, 289, 30);
		comboBoxNacionalidade.setBackground(SystemColor.text);
		comboBoxNacionalidade.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		comboBoxNacionalidade.setFont(new Font("Roboto", Font.PLAIN, 16));
		comboBoxNacionalidade.addItem(new Nacionalidade(0l, "Selecione", true));
		List<Nacionalidade> nacionalidades = this.nacionalidadeController.listar();
		for (Nacionalidade nacionalidade : nacionalidades) {

			comboBoxNacionalidade.addItem(nacionalidade);

		}
		comboBoxNacionalidade.setVisible(true);
		contentPane.add(comboBoxNacionalidade);

		lblNome = new JLabel("NOME");
		lblNome.setBounds(540, 130, 253, 14);
		lblNome.setForeground(SystemColor.textInactiveText);
		lblNome.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNome);

		lblSobrenome = new JLabel("SOBRENOME");
		lblSobrenome.setBounds(540, 200, 255, 14);
		lblSobrenome.setForeground(SystemColor.textInactiveText);
		lblSobrenome.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblSobrenome);

		lblDataNascimento = new JLabel("DATA DE NASCIMENTO");
		lblDataNascimento.setBounds(540, 270, 255, 14);
		lblDataNascimento.setForeground(SystemColor.textInactiveText);
		lblDataNascimento.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblDataNascimento);

		lblNacionalidade = new JLabel("NACIONALIDADE");
		lblNacionalidade.setBounds(540, 340, 255, 14);
		lblNacionalidade.setForeground(SystemColor.textInactiveText);
		lblNacionalidade.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNacionalidade);

		lblTelefone = new JLabel("TELEFONE");
		lblTelefone.setBounds(540, 410, 253, 14);
		lblTelefone.setForeground(SystemColor.textInactiveText);
		lblTelefone.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtTelefone.setBounds(540, 430, 285, 30);
		txtTelefone.setColumns(10);
		txtTelefone.setBackground(Color.WHITE);
		txtTelefone.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefone);

		lblTitulo = new JLabel("REGISTRO HÓSPEDE");
		lblTitulo.setBounds(570, 55, 250, 42);
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 23));
		contentPane.add(lblTitulo);

		lblNumeroReserva = new JLabel("NÚMERO DE RESERVA");
		lblNumeroReserva.setBounds(540, 480, 253, 14);
		lblNumeroReserva.setForeground(SystemColor.textInactiveText);
		lblNumeroReserva.setFont(new Font("Roboto Black", Font.PLAIN, 18));
		contentPane.add(lblNumeroReserva);

		lblCodigoReserva = new JLabel(reserva.getCodigoReserva());
		lblCodigoReserva.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblCodigoReserva.setBounds(540, 500, 285, 30);
		lblCodigoReserva.setBackground(Color.WHITE);
		lblCodigoReserva.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(lblCodigoReserva);

		separator_1_2 = new JSeparator();
		separator_1_2.setBounds(540, 185, 289, 2);
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2);

		separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(540, 255, 289, 2);
		separator_1_2_1.setForeground(new Color(12, 138, 199));
		separator_1_2_1.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_1);

		separator_1_2_2 = new JSeparator();
		separator_1_2_2.setBounds(540, 325, 289, 2);
		separator_1_2_2.setForeground(new Color(12, 138, 199));
		separator_1_2_2.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_2);

		separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(540, 395, 289, 2);
		separator_1_2_3.setForeground(new Color(12, 138, 199));
		separator_1_2_3.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_3);

		separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(540, 465, 289, 2);
		separator_1_2_4.setForeground(new Color(12, 138, 199));
		separator_1_2_4.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_4);

		separator_1_2_5 = new JSeparator();
		separator_1_2_5.setBounds(540, 535, 289, 2);
		separator_1_2_5.setForeground(new Color(12, 138, 199));
		separator_1_2_5.setBackground(new Color(12, 138, 199));
		contentPane.add(separator_1_2_5);

		btnSalvar = new JButton();
		btnSalvar.setBounds(723, 560, 122, 35);
		btnSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (txtNome.getText() != "" && txtSobrenome.getText() != "" && txtTelefone.getText() != "") {

					Nacionalidade nacionalidade = (Nacionalidade) comboBoxNacionalidade.getSelectedItem();
					if (nacionalidade.getNacionalidade() != "Selecione") {

						Cliente cliente = new Cliente(null, txtNome.getText(), txtSobrenome.getText(),
								dataNascimento.getDate(), nacionalidade.getId(), txtTelefone.getText(), true);
						
						if (comboBoxNacionalidade.isVisible()) {
							clienteController.salvarCliente(cliente);
							reserva.setClienteId(cliente.getId());
						}
						
						reservaController.salvarReserva(reserva);

						String de = reserva.getDataEntrada().format(formato);
						String ds = reserva.getDataSaida().format(formato);

						JOptionPane.showMessageDialog(contentPane,
								"La reserva " + reserva.getCodigoReserva() + " para el cliente " + cliente.getNome()
										+ " " + cliente.getSobrenome() + " de " + de + " a " + ds + " por el valor de "
										+ reserva.getValorReserva() + " foi criada com sucesso.");
						ReservasFrame reservasFrame = new ReservasFrame();
						reservasFrame.setVisible(true);
						dispose();

					} else {

						JOptionPane.showMessageDialog(contentPane, "Debe selecionar uma nacionalidade primeiro!");

					}

				} else {

					JOptionPane.showMessageDialog(contentPane, "Nome, sobrenome e telefone debem ser preenchidos.");

				}

			}
		});
		btnSalvar.setLayout(null);
		btnSalvar.setBackground(new Color(12, 138, 199));
		contentPane.add(btnSalvar);
		btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		lblSalvar = new JLabel("SALVAR");
		lblSalvar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalvar.setForeground(Color.WHITE);
		lblSalvar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblSalvar.setBounds(0, 0, 122, 35);
		btnSalvar.add(lblSalvar);

		panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(12, 138, 199));
		contentPane.add(panel);
		panel.setLayout(null);

		lblImagemFundo = new JLabel("");
		lblImagemFundo.setBounds(0, 121, 479, 502);
		panel.add(lblImagemFundo);
		lblImagemFundo.setIcon(new ImageIcon(ClientesFrame.class.getResource("/imagenes/registro.png")));

		lblLogo = new JLabel("");
		lblLogo.setBounds(194, 39, 104, 107);
		panel.add(lblLogo);
		lblLogo.setIcon(new ImageIcon(ClientesFrame.class.getResource("/imagenes/Ha-100px.png")));
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Dialog", Font.PLAIN, 16));
		btnBuscar.setBounds(780, 95, 100, 30);
		contentPane.add(btnBuscar);
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		
				BuscaClienteFrame buscaClienteFrame = new BuscaClienteFrame(reserva);
				buscaClienteFrame.setVisible(true);
				dispose();
			}

		});
		
	}

	public void setComboBoxNacionalidade(String nacionalidade) {
		this.comboBoxNacionalidade.setVisible(false);
		this.lblComboBoxNacionalidade.setText(nacionalidade);
		this.lblComboBoxNacionalidade.setVisible(true);
	}

	public void setTxtNome(String nome) {
		this.txtNome.setText(nome);
	}

	public void setTxtSobrenome(String Sobrenome) {
		this.txtSobrenome.setText(Sobrenome);
	}

	public void setTxtTelefone(String telefone) {
		this.txtTelefone.setText(telefone);
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento.setDate(dataNascimento);
	}

	public void setLblCodigoReserva(String codigoReserva) {
		this.lblCodigoReserva.setText(codigoReserva);
	}

	// Código que permite movimentar a janela pela tela seguindo a posição de "x" y
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

}
