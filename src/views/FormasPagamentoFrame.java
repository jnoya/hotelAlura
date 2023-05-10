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

import controller.FormaPagamentoController;
import modelo.FormaPagamento;

public class FormasPagamentoFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel labelFormaPagamento;
	private JTextField textoFormaPagamento;
	private JButton botaoSalvar, botaoEditar, botaoLimpar, botarApagar;
	private JTable tabela;
	private DefaultTableModel modelo;
	private FormaPagamentoController formaPagamentoController;

	public FormasPagamentoFrame() {
		super("Formas de Pagamento");
		
		Container container = getContentPane();
		setLayout(null);

		this.formaPagamentoController = new FormaPagamentoController();

		labelFormaPagamento = new JLabel("Forma de Pagamento");

		labelFormaPagamento.setBounds(10, 10, 240, 15);

		labelFormaPagamento.setForeground(Color.BLACK);

		container.add(labelFormaPagamento);

		textoFormaPagamento = new JTextField();

		textoFormaPagamento.setBounds(10, 25, 265, 20);

		container.add(textoFormaPagamento);

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

		modelo.addColumn("Identificador da Forma de Pagamento");
		modelo.addColumn("Forma de Pagamento");

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
			String formaPagamento = (String) modelo.getValueAt(tabela.getSelectedRow(), 1);
			String mensaje = this.formaPagamentoController.alterar(id, formaPagamento);
			JOptionPane.showMessageDialog(this, mensaje);

		} else {

			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");

		}

	}

	private void deletar() {

		if (tabela.getSelectedRow() != -1 && tabela.getSelectedColumn() == 0) {

			Long id = (Long) modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
			if (this.formaPagamentoController.deletar(id)) {

				JOptionPane.showMessageDialog(this, "Forma de pagamento excluida com sucesso!");

			} else {

				JOptionPane.showMessageDialog(this, "Ainda tem registros em Reservas usando esta forma de pagamento!");

			}

		} else {

			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");

		}

	}

	private void preencherTabela() {

		List<FormaPagamento> formasPagamento = listarFormaPagamento();
		try {
			for (FormaPagamento formaPagamento : formasPagamento) {

				modelo.addRow(new Object[] { formaPagamento.getId(), formaPagamento.getFormaPagamento() });

			}

		} catch (Exception e) {

			throw e;

		}

	}

	private List<FormaPagamento> listarFormaPagamento() {

		return this.formaPagamentoController.listar();

	}

	private void salvar() {

		if (!textoFormaPagamento.getText().equals("")) {

			FormaPagamento formaPagamento = new FormaPagamento(textoFormaPagamento.getText());
			String mensaje = this.formaPagamentoController.salvar(formaPagamento);
			JOptionPane.showMessageDialog(this, mensaje);
			this.limpar();

		} else {

			JOptionPane.showMessageDialog(this, "Forma de pagamento  deve ser informada.");

		}

	}

	private void limpar() {

		this.textoFormaPagamento.setText("");

	}

}
