package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.NivelAcessoController;
import modelo.NivelAcesso;

public class NiveisAcessoFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel labelNivelAcesso;
	private JTextField textoNivelAcesso;
	private JButton botaoSalvar, botaoEditar, botaoLimpar, botarApagar;
	private JTable tabela;
	private DefaultTableModel modelo;
	private NivelAcessoController nivelAcessoController;

	public NiveisAcessoFrame() {
		super("Niveis de Acesso");
		
		Container container = getContentPane();
		setLayout(null);

		this.nivelAcessoController = new NivelAcessoController();

		labelNivelAcesso = new JLabel("Nivel Acesso");
		labelNivelAcesso.setBounds(10, 10, 240, 15);
		labelNivelAcesso.setForeground(Color.BLACK);

		container.add(labelNivelAcesso);

		textoNivelAcesso = new JTextField();
		textoNivelAcesso.setBounds(10, 25, 265, 20);

		container.add(textoNivelAcesso);

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

		};
		tabela = new JTable(modelo);
		modelo.addColumn("Identificador do Nivel de Acesso");
		modelo.addColumn("Nivel de Acesso");
		tabela.setEditingColumn(1);
		tabela.setColumnSelectionAllowed(false);
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

		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);

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
			String nivelAcesso = (String) tabela.getValueAt(tabela.getSelectedRow(), 1);
			String mensaje = this.nivelAcessoController.alterar(id, nivelAcesso);
			JOptionPane.showMessageDialog(this, mensaje);

		} else {

			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");

		}

	}

	private void deletar() {

		if (tabela.getSelectedRow() != -1 && tabela.getSelectedColumn() == 0) {

			Long id = (Long) modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
			if (this.nivelAcessoController.deletar(id)) {

				JOptionPane.showMessageDialog(this, "Nivel de acesso excluido com sucesso!");

			} else {

				JOptionPane.showMessageDialog(this, "Ainda tem registros em Usuarios usando este nivel de acesso!");

			}

		} else {

			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");

		}

	}

	private void preencherTabela() {

		List<NivelAcesso> niveisAcesso = listarNivelAcesso();
		try {
			for (NivelAcesso nivelAcesso : niveisAcesso) {

				modelo.addRow(new Object[] { nivelAcesso.getId(), nivelAcesso.getNivelAcesso() });

			}

		} catch (Exception e) {

			throw e;

		}

	}

	private List<NivelAcesso> listarNivelAcesso() {

		return this.nivelAcessoController.listar();

	}

	private void salvar() {

		if (!textoNivelAcesso.getText().equals("")) {

			NivelAcesso nivelAcesso = new NivelAcesso(textoNivelAcesso.getText());
			String mensaje = this.nivelAcessoController.salvar(nivelAcesso);
			JOptionPane.showMessageDialog(this, mensaje);
			this.limpar();

		} else {

			JOptionPane.showMessageDialog(this, "Nivel de acesso  deve ser informado.");

		}

	}

	private void limpar() {

		this.textoNivelAcesso.setText("");

	}

}
