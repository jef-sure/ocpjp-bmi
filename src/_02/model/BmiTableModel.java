package _02.model;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import _00.model.BMI;

@SuppressWarnings("serial")
public class BmiTableModel extends AbstractTableModel {
	private static String[] bmiColumnNames = { "Alter", "unter", "zwischen", "über" };
	private String[][] data = new String[AlterRange.values().length][bmiColumnNames.length];
	{
		AlterRange[] ar = AlterRange.values();
		for (int i = 0; i < ar.length; i++)
			data[i][0] = ar[i].toString();
	}
	private AlterRange[] ar = AlterRange.values();
	private BMI bmiSource;

	public BmiTableModel(BMI bmiSource) {
		this.bmiSource = bmiSource;
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return bmiColumnNames.length;
	}

	@Override
	public String getColumnName(int i) {
		return bmiColumnNames[i];
	}

	@Override
	public int getRowCount() {
		return AlterRange.values().length;
	}

	@Override
	public Object getValueAt(int i, int j) {
		switch (j) {
		case 0:
			return data[i][0];
		case 1:
			return "" + ar[i].getUntergewicht(bmiSource.istMann());
		case 2:
			return ar[i].getUntergewicht(bmiSource.istMann()) + " - " + ar[i].getUebergewicht(bmiSource.istMann());
		case 3:
			return "" + ar[i].getUebergewicht(bmiSource.istMann());
		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
	}

}
