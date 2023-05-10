package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;

import controller.DiariaController;
import controller.TipoHabitacaoController;
import modelo.Diaria;
import modelo.ModeloDiarias;
import modelo.TipoHabitacao;
import javax.swing.JComboBox;

public class DiariasFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton botaoSalvar, botaoEditar, botaoLimpar, botarApagar;
	private JTable tabela;
	private ModeloDiarias modelo;
	private DiariaController diariaController;
	private JLabel labelValor, labelData, labelTipoHabitacao;
	private JTextField textoValor;
	private JComboBox<TipoHabitacao> comboBoxTipoHabitacao;
	private JScrollPane scroll;
	private TipoHabitacaoController tipoHabitacaoController;
	private DatePicker data = new DatePicker();

	private Container container = getContentPane();

	public DiariasFrame() {

		super("Diarias");
		

		getContentPane().setLayout(null);

		this.diariaController = new DiariaController();
		this.tipoHabitacaoController = new TipoHabitacaoController();
		data.getComponentDateTextField().setFont(new Font("Dialog", Font.PLAIN, 16));

		data.setFont(new Font("Roboto", Font.PLAIN, 16));
		data.setSize(190, 25);
		data.setLocation(10, 25);
		data.setDate(LocalDate.now());
		container.add(data);

		labelData = new JLabel("Data");
		labelData.setBounds(10, 10, 120, 15);
		labelData.setForeground(Color.BLACK);
		container.add(labelData);

		labelTipoHabitacao = new JLabel("Tipo de Habitação");
		labelTipoHabitacao.setBounds(10, 55, 120, 15);
		container.add(labelTipoHabitacao);

		comboBoxTipoHabitacao = new JComboBox<TipoHabitacao>();
		comboBoxTipoHabitacao.setBounds(10, 70, 120, 20);

		comboBoxTipoHabitacao.addItem(new TipoHabitacao(0l, "Selecione", true));
		List<TipoHabitacao> tiposHabitacao = this.tipoHabitacaoController.listar();
		for (TipoHabitacao tipoHabitacao : tiposHabitacao) {

			comboBoxTipoHabitacao.addItem(tipoHabitacao);

		}
		container.add(comboBoxTipoHabitacao);

		labelValor = new JLabel("Valor");
		labelValor.setBounds(10, 100, 120, 15);
		container.add(labelValor);

		textoValor = new JTextField();
		textoValor.setBounds(10, 115, 120, 20);
		container.add(textoValor);

		botaoSalvar = new JButton("Salvar");
		botaoLimpar = new JButton("Limpar");

		botaoSalvar.setBounds(10, 145, 80, 20);
		botaoLimpar.setBounds(100, 145, 80, 20);

		container.add(botaoSalvar);
		container.add(botaoLimpar);

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
				modelo.limparTabela();
				modelo.atualizaTabela(diariaController.listar());
				;
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
				modelo.removeDiaria(tabela.getSelectedRow());;
				modelo.limparTabela();
				modelo.atualizaTabela(diariaController.listar());
				;
			}
		});

		botaoEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alterar();
				modelo.limparTabela();
				modelo.atualizaTabela(diariaController.listar());
				;
			}
		});
	}

	private void alterar() {

		if (tabela.getSelectedRow() != -1 && tabela.getSelectedColumn() == 0) {

			Double valor = (Double) tabela.getValueAt(tabela.getSelectedRow(), 2);
			Long id = (Long) modelo.getId(tabela.getSelectedRow());
			this.diariaController.alterar(id, valor);
			JOptionPane.showMessageDialog(this, "Valor da diaria alterado com sucesso");

		} else {

			JOptionPane.showMessageDialog(this, "Por favor, selecionar a Data");

		}

	}

	private void deletar() {

		if (tabela.getSelectedRow() != -1 && tabela.getSelectedColumn() == 0) {

			Long id = (Long) modelo.getId(tabela.getSelectedRow());
			this.diariaController.deletar(id);
			JOptionPane.showMessageDialog(this, "Diaria excluida com sucesso!");

		} else {

			JOptionPane.showMessageDialog(this, "Por favor, selecionar o Data");

		}

	}

	private void preencherTabela() {

		List<Diaria> diarias = this.diariaController.listar();
		modelo = new ModeloDiarias(diarias);
		tabela = new JTable(modelo);
		tabela.setBounds(10, 185, 760, 300);
		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		scroll = new JScrollPane(tabela);
		scroll.setBounds(10, 185, 760, 300);
		container.add(scroll);

	}

	private void salvar() {

		TipoHabitacao tipoHabitacao = (TipoHabitacao) comboBoxTipoHabitacao.getSelectedItem();

		if (data.getDate() != null && !textoValor.getText().equals("")
				&& !tipoHabitacao.getDescricao().equals("Selecione")) {

			Diaria diaria = new Diaria(data.getDate(), textoValor.getText(), 0l);
			diaria.setTipoHabitacaoId(tipoHabitacao.getId());
			String mensaje = this.diariaController.salvar(diaria);
			JOptionPane.showMessageDialog(this, mensaje);
			this.limpar();

		} else {

			JOptionPane.showMessageDialog(this, "Data, Tipo de Habitação e Valor  devem ser informados.");

		}

	}

	private void limpar() {

		this.textoValor.setText("");

	}

}
