package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Tabelas extends JFrame {

	private JPanel contentPane;

	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabelas frame = new Tabelas();
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
	public Tabelas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tabelas.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		JLabel lblTabelas = new JLabel("TABELAS AUXILIARES");
		lblTabelas.setForeground(new Color(12, 138, 199));
		lblTabelas.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTabelas.setBounds(331, 62, 280, 42);
		contentPane.add(lblTabelas);
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

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
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

		JButton btnNivelAcesso = new JButton("Niveis de Acesso");
		btnNivelAcesso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NiveisAcessoFrame nivelAcesso = new NiveisAcessoFrame();
				nivelAcesso.setVisible(true);
				// dispose();
			}

			// @Override
			// public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse
			// sobre o botão, ele muda de cor
			// btnNivelAcesso.setBackground(Color.red);
			// lblNivelAcesso.setForeground(Color.white);
			// }

			// @Override
			// public void mouseExited(MouseEvent e) { // Quando o usuário remove o mouse do
			// botão, ele retornará ao estado
			// original
			// btnNivelAcesso.setBackground(Color.white);
			// lblNivelAcesso.setForeground(Color.black);
			// }
		});
		btnNivelAcesso.setForeground(new Color(255, 255, 255));
		btnNivelAcesso.setBackground(new Color(0, 128, 255));
		btnNivelAcesso.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnNivelAcesso.setBounds(254, 125, 240, 36);
		contentPane.add(btnNivelAcesso);

		JLabel lblNivelAcesso = new JLabel("Niveis de Acesso");
		lblNivelAcesso.setForeground(new Color(255, 255, 255));
		lblNivelAcesso.setBackground(new Color(0, 128, 255));
		lblNivelAcesso.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblNivelAcesso.setBounds(254, 125, 240, 36);
		contentPane.add(lblNivelAcesso);

		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UsuariosFrame usuarios = new UsuariosFrame();
				usuarios.setVisible(true);
				// dispose();
			}

			/*
			 * @Override public void mouseEntered(MouseEvent e) { // Quando o usuário passa
			 * o mouse sobre o botão, ele muda de cor btnUsuarios.setBackground(Color.red);
			 * lblUsuarios.setForeground(Color.white); }
			 * 
			 * @Override public void mouseExited(MouseEvent e) { // Quando o usuário remove
			 * o mouse do botão, ele retornará ao estado // original
			 * btnUsuarios.setBackground(Color.white);
			 * lblUsuarios.setForeground(Color.black); }
			 */ });
		btnUsuarios.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnUsuarios.setForeground(new Color(255, 255, 255));
		btnUsuarios.setBackground(new Color(0, 128, 255));
		btnUsuarios.setBounds(254, 185, 240, 36);
		contentPane.add(btnUsuarios);

		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBackground(new Color(0, 128, 255));
		lblUsuarios.setForeground(new Color(255, 255, 255));
		lblUsuarios.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblUsuarios.setBounds(254, 185, 240, 36);
		contentPane.add(lblUsuarios);

		JButton btnDiarias = new JButton("Diarias");
		btnDiarias.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DiariasFrame diarias = new DiariasFrame();
				diarias.setVisible(true);
			}
		});
		btnDiarias.setForeground(new Color(255, 255, 255));
		btnDiarias.setFont(new Font("Roboto", Font.PLAIN, 16));
		btnDiarias.setBackground(new Color(0, 128, 255));
		btnDiarias.setBounds(254, 240, 240, 36);
		contentPane.add(btnDiarias);

		JButton btnFormasPagamento = new JButton("Formas de Pagamento");
		btnFormasPagamento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FormasPagamentoFrame formasPagamento = new FormasPagamentoFrame();
				formasPagamento.setVisible(true);
			}
		});
		btnFormasPagamento.setBackground(new Color(0, 128, 255));
		btnFormasPagamento.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnFormasPagamento.setForeground(new Color(255, 255, 255));
		btnFormasPagamento.setBounds(254, 295, 240, 36);
		contentPane.add(btnFormasPagamento);

		JLabel lblDiarias = new JLabel("Diarias");
		lblDiarias.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblDiarias.setForeground(new Color(255, 255, 255));
		lblDiarias.setBackground(new Color(0, 128, 255));
		lblDiarias.setBounds(254, 240, 240, 36);
		contentPane.add(lblDiarias);

		JLabel lblFormasPagamento = new JLabel("Formas de Pagamento");
		lblFormasPagamento.setBackground(new Color(0, 128, 255));
		lblFormasPagamento.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblFormasPagamento.setForeground(new Color(255, 255, 255));
		lblFormasPagamento.setBounds(254, 295, 240, 36);
		contentPane.add(lblFormasPagamento);

		JButton btnTiposHabitacao = new JButton("Tipos de Habitação");
		btnTiposHabitacao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TiposHabitacaoFrame tiposHabitacao = new TiposHabitacaoFrame();
				tiposHabitacao.setVisible(true);
			}
		});
		btnTiposHabitacao.setForeground(new Color(255, 255, 255));
		btnTiposHabitacao.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnTiposHabitacao.setBackground(new Color(0, 128, 255));
		btnTiposHabitacao.setBounds(254, 350, 240, 36);
		contentPane.add(btnTiposHabitacao);

		JLabel lblTiposHabitacao = new JLabel("Tipos de Habitação");
		lblTiposHabitacao.setBackground(new Color(0, 128, 255));
		lblTiposHabitacao.setForeground(new Color(255, 255, 255));
		lblTiposHabitacao.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblTiposHabitacao.setBounds(254, 350, 240, 36);
		contentPane.add(lblTiposHabitacao);

		JButton btnNacionalidades = new JButton("Nacionalidades");
		btnNacionalidades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NacionalidadesFrame nacionalidades = new NacionalidadesFrame();
				nacionalidades.setVisible(true);
			}
		});
		btnNacionalidades.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnNacionalidades.setForeground(new Color(255, 255, 255));
		btnNacionalidades.setBackground(new Color(0, 128, 255));
		btnNacionalidades.setBounds(254, 405, 240, 36);
		contentPane.add(btnNacionalidades);

		JLabel lblNacionalidades = new JLabel("Nacionalidades");
		lblNacionalidades.setBackground(new Color(0, 128, 255));
		lblNacionalidades.setForeground(new Color(255, 255, 255));
		lblNacionalidades.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblNacionalidades.setBounds(254, 405, 240, 36);
		contentPane.add(lblNacionalidades);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("C:\\Users\\paran\\eclipse-workspace\\hotel-alura\\src\\imagenes\\aH-150px.png"));
		lblLogo.setBounds(25, 80, 150, 150);
		contentPane.add(lblLogo);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 255));
		panel.setBounds(0, 500, 910, 37);
		contentPane.add(panel);
		
		JLabel lblRodape = new JLabel("Desenvolvido por Julio Castiglioni © 2023");
		lblRodape.setForeground(new Color(255, 255, 255));
		lblRodape.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.add(lblRodape);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(507, 0, 350, 495);
		contentPane.add(panel_1);
		
		JLabel lblImagemFundo = new JLabel("");
		lblImagemFundo.setIcon(new ImageIcon(Tabelas.class.getResource("/imagenes/img-hotel-login-.png")));
		panel_1.add(lblImagemFundo);
		setResizable(false);
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
