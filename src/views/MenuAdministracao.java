package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.SystemColor;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class MenuAdministracao extends JFrame {

	private JPanel contentPane;
	int xMouse, yMouse;
	private JLabel labelExit;
	private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public MenuAdministracao() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(MenuAdministracao.class.getResource("/imagenes/aH-40px.png")));
		
		setBounds(100, 100, 944, 609);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);

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

		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(12, 138, 199));
		panelMenu.setBounds(0, 0, 257, 609);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);

		JPanel btnAdmTabelas = new JPanel();
		btnAdmTabelas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAdmTabelas.setBackground(new Color(118, 187, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAdmTabelas.setBackground(new Color(12, 138, 199));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Tabelas tabelas = new Tabelas();
				tabelas.setVisible(true);
				dispose();
			}
		});

		btnAdmTabelas.setBounds(0, 312, 257, 70);
		btnAdmTabelas.setBackground(new Color(12, 138, 199));
		btnAdmTabelas.setLayout(null);
		btnAdmTabelas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		panelMenu.add(btnAdmTabelas);

		JLabel lblAdmTabelasAuxiliares = new JLabel("<html>Administrar tabelas </br> auxiliares</html>");
		lblAdmTabelasAuxiliares.setIcon(new ImageIcon(MenuAdministracao.class.getResource("/imagenes/pessoas.png")));
		lblAdmTabelasAuxiliares.setBounds(30, 0, 199, 70);
		lblAdmTabelasAuxiliares.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdmTabelasAuxiliares.setForeground(Color.WHITE);
		lblAdmTabelasAuxiliares.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnAdmTabelas.add(lblAdmTabelasAuxiliares);

		JLabel logo = new JLabel("");
		logo.setBounds(50, 58, 150, 150);
		panelMenu.add(logo);
		logo.setIcon(new ImageIcon(MenuAdministracao.class.getResource("/imagenes/aH-150px.png")));

		JPanel btnListadosAdm = new JPanel();
		btnListadosAdm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnListadosAdm.setBackground(new Color(118, 187, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnListadosAdm.setBackground(new Color(12, 138, 199));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				ListadosFrame listados = new ListadosFrame();
				listados.setVisible(true);
				dispose();
			}
		});
		btnListadosAdm.setBounds(0, 255, 257, 56);
		btnListadosAdm.setBackground(new Color(12, 138, 199));
		panelMenu.add(btnListadosAdm);
		btnListadosAdm.setLayout(null);
		btnListadosAdm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		JLabel labelListadosAdm = new JLabel("Listados administrativos");
		labelListadosAdm.setIcon(new ImageIcon(MenuAdministracao.class.getResource("/imagenes/reservado.png")));
		labelListadosAdm.setForeground(SystemColor.text);
		labelListadosAdm.setBounds(25, 11, 222, 34);
		labelListadosAdm.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelListadosAdm.setHorizontalAlignment(SwingConstants.LEFT);
		btnListadosAdm.add(labelListadosAdm);

		JSeparator separator = new JSeparator();
		separator.setBounds(26, 219, 201, 2);
		panelMenu.add(separator);
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 944, 36);
		contentPane.add(header);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	            MenuUsuario menu = new MenuUsuario();
	            menu.setVisible(true);
	            dispose();	 
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});

		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(891, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel panelFecha = new JPanel();
		panelFecha.setBackground(new Color(118, 187, 223));
		panelFecha.setBounds(256, 84, 688, 121);
		contentPane.add(panelFecha);
		panelFecha.setLayout(null);

		JLabel lblTituloPrincipal = new JLabel("Modulo administrativo Hotel Alura");
		lblTituloPrincipal.setBounds(180, 11, 450, 42);
		panelFecha.add(lblTituloPrincipal);
		lblTituloPrincipal.setForeground(Color.WHITE);
		lblTituloPrincipal.setFont(new Font("Roboto", Font.PLAIN, 24));

		JLabel labelFecha = new JLabel("New label");
		labelFecha.setBounds(210, 64, 294, 36);
		panelFecha.add(labelFecha);
		labelFecha.setForeground(Color.WHITE);
		labelFecha.setFont(new Font("Roboto", Font.PLAIN, 33));
		LocalDate fechaActual = LocalDate.now(); // data atual
		String fecha = fechaActual.format(formato); // formata a data numa string
		labelFecha.setText("Hoje é " + fecha); // estabelece a data na label

		JLabel lbltitulo = new JLabel("Bem-vindo");
		lbltitulo.setFont(new Font("Roboto", Font.BOLD, 24));
		lbltitulo.setBounds(302, 234, 147, 46);
		contentPane.add(lbltitulo);

		String textoDescripcion = "<html><body>Administre as tabelas auxiliares do sistema </br> e obtenha listados administrativos </body></html>";
		JLabel labelDescripcion_0 = new JLabel(textoDescripcion);
		labelDescripcion_0.setFont(new Font("Roboto", Font.PLAIN, 17));

		labelDescripcion_0.setBounds(312, 291, 568, 66);
		contentPane.add(labelDescripcion_0);

		String textoDescricao1 = "<html><body> Esta ferramenta permitirá que você mantenha um controle completo e detalhado de suas tabelas auxiliares, você terá acesso a ferramentas especiais para tarefas específicas como:</body></html>";
		JLabel labelDescricao_1 = new JLabel(textoDescricao1);
		labelDescricao_1.setFont(new Font("Roboto", Font.PLAIN, 17));
		labelDescricao_1.setBounds(311, 345, 569, 88);
		contentPane.add(labelDescricao_1);

		JLabel labelDescricao_2 = new JLabel("- Inserção, alteração e exclusão de registros nas tabelas auxiliares");
		labelDescricao_2.setFont(new Font("Roboto", Font.PLAIN, 17));
		labelDescricao_2.setBounds(312, 457, 500, 27);
		contentPane.add(labelDescricao_2);

		JLabel labelDescricao_3 = new JLabel("- Listados administrativos");
		labelDescricao_3.setFont(new Font("Roboto", Font.PLAIN, 17));
		labelDescricao_3.setBounds(312, 500, 355, 27);
		contentPane.add(labelDescricao_3);

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
}
