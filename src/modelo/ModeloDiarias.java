package modelo;

import java.time.LocalDate;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModeloDiarias extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String colunas[] = { "Data", "ID do Tipo de Habitacao", "Valor" };
	private List<Diaria> diarias;
	private final int COLUNA_DATA = 0;
	private final int COLUNA_TIPOHABITACAOID = 1;
	private final int COLUNA_VALOR = 2;

	public ModeloDiarias(List<Diaria> list) {

		this.diarias = list;

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		return columnIndex == 2;

	}

	@Override
	public int getRowCount() {

		if (diarias == null) {

			return 0;

		} else {

			return diarias.size();

		}

	}

	// retorna o total de colunas da tabela
	@Override
	public int getColumnCount() {

		return colunas.length;

	}

	// retorna o nome da coluna de acordo com seu indice
	@Override
	public String getColumnName(int indice) {

		return colunas[indice];

	}

	// determina o tipo de dado da coluna conforme seu indice
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		switch (columnIndex) {
		case COLUNA_DATA:
			return LocalDate.class;
		case COLUNA_TIPOHABITACAOID:
			return Long.class;
		case COLUNA_VALOR:
			return Double.class;
		default:
			return String.class;
		}

	}

	// preenche cada célula da tabela
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Diaria diaria = diarias.get(rowIndex);

		switch (columnIndex) {
		case COLUNA_DATA:
			return diaria.getData();
		case COLUNA_TIPOHABITACAOID:
			return diaria.getTipoHabitacaoId();
		case COLUNA_VALOR:
			return diaria.getValor();
		}
		return null;

	}

	// altera o valor do objeto de acordo com a célula editada
	// e notifica a alteração da tabela, para que ela seja atualizada na tela

	public void setValueAt(Double aValue, int rowIndex) {

		Diaria diaria = diarias.get(rowIndex);
		diaria.setValor(aValue);
		fireTableCellUpdated(rowIndex, 2);

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		Diaria diaria = diarias.get(rowIndex);

		switch (columnIndex) {
		case 0:
			diaria.setData((LocalDate) aValue);
		case 1:
			diaria.setTipoHabitacaoId((Long) aValue);
		case 2:
			diaria.setValor((Double) aValue);

		default:
			System.err.println("Índice da coluna inválido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Diaria getDiaria(int indiceLinha) {

		return diarias.get(indiceLinha);

	}

	public void addDiaria(Diaria diaria) {

		diarias.add(diaria);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);

	}

	public void removeDiaria(int indiceLinha) {

		diarias.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);

	}

	public void atualizaTabela(List<Diaria> diaria) {

		System.out.println("atualizando tabela");
		int tamanhoAntigo = getRowCount();
		diarias.addAll(diaria);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);

	}

	public void limparTabela() {

		diarias.clear();
		fireTableDataChanged();

	}

	public boolean isEmpty() {

		return diarias.isEmpty();

	}

	public Long getId(int selectedRow) {

		return diarias.get(selectedRow).getId();

	}

}