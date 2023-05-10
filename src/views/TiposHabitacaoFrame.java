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

import controller.TipoHabitacaoController;
import modelo.TipoHabitacao;

public class TiposHabitacaoFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel labelTipoHabitacao;
	private JTextField textoTipoHabitacao;
	private JButton botaoSalvar, botaoEditar, botaoLimpar, botarApagar;
	private JTable tabela;
	private DefaultTableModel modelo;
	private TipoHabitacaoController tipoHabitacaoController;

	public TiposHabitacaoFrame() {
		super("Tipos de Habitação");
		
		Container container = getContentPane();
		setLayout(null);

		this.tipoHabitacaoController = new TipoHabitacaoController();

		labelTipoHabitacao = new JLabel("Tipo de Habitação");

		labelTipoHabitacao.setBounds(10, 10, 240, 15);

		labelTipoHabitacao.setForeground(Color.BLACK);

		container.add(labelTipoHabitacao);

		textoTipoHabitacao = new JTextField();

		textoTipoHabitacao.setBounds(10, 25, 265, 20);

		container.add(textoTipoHabitacao);

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
		modelo.addColumn("Identificador da Tipo de Habitacao");
		modelo.addColumn("Tipo de Habitacao");

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
			String tipoHabitacao = (String) modelo.getValueAt(tabela.getSelectedRow(), 1);
			String mensaje = this.tipoHabitacaoController.alterar(id, tipoHabitacao);
			JOptionPane.showMessageDialog(this, mensaje);

		} else {

			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");

		}

	}

	private void deletar() {

		if (tabela.getSelectedRow() != -1 && tabela.getSelectedColumn() == 0) {

			Long id = (Long) modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
			if (this.tipoHabitacaoController.deletar(id)) {

				JOptionPane.showMessageDialog(this, "Tipo de Habitacao excluido com sucesso!");

			} else {

				JOptionPane.showMessageDialog(this, "Ainda tem registros em Diarias usando este Tipo de Habitacao!");

			}

		} else {

			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");

		}

	}

	private void preencherTabela() {

		List<TipoHabitacao> tiposHabitacao = listarTipoHabitacao();
		try {
			for (TipoHabitacao tipoHabitacao : tiposHabitacao) {

				modelo.addRow(new Object[] { tipoHabitacao.getId(), tipoHabitacao.getDescricao() });

			}

		} catch (Exception e) {

			throw e;

		}

	}

	private List<TipoHabitacao> listarTipoHabitacao() {

		return this.tipoHabitacaoController.listar();

	}

	private void salvar() {

		if (!textoTipoHabitacao.getText().equals("")) {

			TipoHabitacao tipoHabitacao = new TipoHabitacao(textoTipoHabitacao.getText());
			String mensaje = this.tipoHabitacaoController.salvar(tipoHabitacao);
			JOptionPane.showMessageDialog(this, mensaje);
			this.limpar();

		} else {

			JOptionPane.showMessageDialog(this, "Tipo de Habitacao  deve ser informado.");

		}

	}

	private void limpar() {

		this.textoTipoHabitacao.setText("");

	}

}
