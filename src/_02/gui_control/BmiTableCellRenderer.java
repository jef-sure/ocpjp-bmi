package _02.gui_control;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class BmiTableCellRenderer extends DefaultTableCellRenderer {
	private int index;
	private int hcolumn = -1;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (row == index && column == hcolumn)
			setBackground(Color.YELLOW);
		else
			setBackground(null);
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		return this;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setColumn(int c) {
		hcolumn = c;
	}

	public BmiTableCellRenderer() {
		setHorizontalAlignment(JLabel.CENTER);
	}
}
