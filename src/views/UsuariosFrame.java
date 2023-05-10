package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.NivelAcessoController;

import controller.UsuarioController;
import modelo.NivelAcesso;
import modelo.Usuario;

public class UsuariosFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel labelNome, labelSenha, labelAcesso;
	private JTextField textoNome, textoSenha;
	private JComboBox<NivelAcesso> comboAcesso;
	private JButton botaoSalvar, botaoEditar, botaoLimpar, botarApagar;
	private DefaultTableModel modelo;
	private NivelAcessoController nivelAcessoController;
	private UsuarioController usuarioController;
	private JTable tabela;

	public UsuariosFrame() {
		super("Usuarios");
		
		Container container = getContentPane();
		getContentPane().setLayout(null);

		this.usuarioController = new UsuarioController();
		this.nivelAcessoController = new NivelAcessoController();

		labelNome = new JLabel("Nome do Usuario");
		labelSenha = new JLabel("Senha");
		labelAcesso = new JLabel("Nivel de acesso do usuario");

		labelNome.setBounds(10, 10, 240, 15);
		labelSenha.setBounds(10, 50, 240, 15);
		labelAcesso.setBounds(10, 90, 240, 15);

		labelNome.setForeground(Color.BLACK);
		labelSenha.setForeground(Color.BLACK);
		labelAcesso.setForeground(Color.BLACK);

		container.add(labelNome);
		container.add(labelSenha);
		container.add(labelAcesso);

		textoNome = new JTextField();
		textoSenha = new JTextField();
		comboAcesso = new JComboBox<NivelAcesso>();

		comboAcesso.addItem(new NivelAcesso(0l, "Selecione", true));
		List<NivelAcesso> niveisAcesso = this.nivelAcessoController.listar();

		for (NivelAcesso nivelAcesso : niveisAcesso) {

			comboAcesso.addItem(nivelAcesso);

		}
		textoNome.setBounds(10, 25, 265, 20);
		textoSenha.setBounds(10, 65, 265, 20);
		comboAcesso.setBounds(10, 105, 265, 20);

		container.add(textoNome);
		container.add(textoSenha);
		container.add(comboAcesso);

		botaoSalvar = new JButton("Salvar");
		botaoLimpar = new JButton("Limpar");

		botaoSalvar.setBounds(10, 145, 80, 20);
		botaoLimpar.setBounds(100, 145, 80, 20);

		container.add(botaoSalvar);
		container.add(botaoLimpar);

		modelo = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				// se a coluna for a 2 não pode ser editada lembrando que 1 é a 0
				if (column == 0) {

					return false;

				}

				return super.isCellEditable(row, column);

			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {

				switch (columnIndex) {
				case 0:
					return Long.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return Long.class;
				default:
					return Boolean.class;
				}

			}

		};
		tabela = new JTable(modelo);

		modelo.addColumn("Identificador do Usuario");
		modelo.addColumn("Nome do Usuario");
		modelo.addColumn("Senha");
		modelo.addColumn("Nivel Acesso");

		tabela.setBounds(10, 185, 760, 300);
		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);

		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(10, 185, 760, 300);

		container.add(scroll);

		preencherTabela();

		botarApagar = new JButton("Excluir");
		botaoEditar = new JButton("Alterar");

		botarApagar.setBounds(10, 500, 80, 20);
		botaoEditar.setBounds(100, 500, 80, 20);

		container.add(botarApagar);
		container.add(botaoEditar);

		botaoSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
				limparTabela();
				preencherTabela();
			}
		});

		botaoLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});

		botarApagar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deletar();
				modelo.removeRow(tabela.getSelectedRow());;
				limparTabela();
				preencherTabela();
			}
		});

		botaoEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alterar();
				limparTabela();
				preencherTabela();
			}
	
		});
	
	}

	private void limparTabela() {

		modelo.getDataVector().clear();
	
	}

	private void alterar() {

		if (tabela.getSelectedRow() != -1 && tabela.getSelectedColumn() == 0) {

			Long id = (Long) modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
			String login = (String) tabela.getValueAt(tabela.getSelectedRow(), 1);
			String senha = (String) tabela.getValueAt(tabela.getSelectedRow(), 2);
			Long nivelAcessoId = (Long) (tabela.getValueAt(tabela.getSelectedRow(), 3));

			String mensaje = this.usuarioController.alterar(id, login, senha, nivelAcessoId);
			JOptionPane.showMessageDialog(this, mensaje);

		} else {

			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");

		}

	}

	private void deletar() {

		if (tabela.getSelectedRow() != -1 && tabela.getSelectedColumn() == 0) {

			Long id = (Long) modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
			this.usuarioController.deletar(id);
			JOptionPane.showMessageDialog(this, "Usuario excluido com sucesso!");

		} else {

			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");

		}

	}

	private void preencherTabela() {

		List<Usuario> usuarios = listarUsuario();
		try {
			for (Usuario usuario : usuarios) {

				modelo.addRow(new Object[] { usuario.getId(), usuario.getLogin(), usuario.getSenha(),
						usuario.getNivelAcessoId() });

			}

		} catch (Exception e) {

			throw e;

		}

	}

	private void salvar() {

		NivelAcesso nivelAcesso = (NivelAcesso) comboAcesso.getSelectedItem();

		if (!textoNome.getText().equals("") && !textoSenha.getText().equals("")
				&& !nivelAcesso.getNivelAcesso().equals("Selecione")) {

			Usuario usuario = new Usuario(textoNome.getText(), textoSenha.getText(), 0l);
			usuario.setNivelAcessoId(nivelAcesso.getId());

			String mensaje = this.usuarioController.salvar(usuario);
			JOptionPane.showMessageDialog(this, mensaje);
			this.limpar();

		} else {

			JOptionPane.showMessageDialog(this, "Nome, senha e nivel de acesso  devem ser informados.");

		}

	}

	private List<Usuario> listarUsuario() {

		return this.usuarioController.listar();

	}

	private void limpar() {

		this.textoNome.setText("");
		this.textoSenha.setText("");
		this.comboAcesso.setSelectedIndex(0);

	}

}
