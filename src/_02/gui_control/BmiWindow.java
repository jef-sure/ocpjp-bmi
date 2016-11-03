package _02.gui_control;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

import _00.model.BMI;
import _02.model.AlterRange;
import _02.model.BmiTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;

public class BmiWindow {

	private JFrame frame;
	private JFormattedTextField tfKoerperGroesse;
	private JTable table;
	private JFormattedTextField tfGewicht;
	private final JButton btnEnd = new JButton("End");
	private JComboBox<AlterRange> cbAlter = new JComboBox<AlterRange>();
	private ButtonGroup grpGeschlecht = new ButtonGroup();
	private BMI bmi = new BMI();
	private JLabel labelBmiValue = new JLabel("");
	private BmiTableModel bmiTableModel = new BmiTableModel(bmi);
	private BmiTableCellRenderer bmiTableCellRenderer = new BmiTableCellRenderer();
	private int hrow = -1;
	private AlterRange arSelected = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BmiWindow window = new BmiWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BmiWindow() {
		initialize();
	}

	private void updateBMI() {
		float bmiWert = bmi.getBmiWert();
		if (bmiWert != 0) {
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(2);
			labelBmiValue.setText(nf.format(bmiWert));
			int hc = bmiWert < arSelected.getUntergewicht(bmi.istMann()) ? 1
					: bmiWert > arSelected.getUebergewicht(bmi.istMann()) ? 3 : 2;
			bmiTableCellRenderer.setColumn(hc);
		}
		bmiTableModel.fireTableDataChanged();
		table.repaint();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 740, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setTitle("Berechnung des Body Mass Index");

		JPanel panelLeft = new JPanel();
		frame.getContentPane().add(panelLeft, BorderLayout.WEST);
		GridBagLayout gbl_panelLeft = new GridBagLayout();
		gbl_panelLeft.columnWidths = new int[] { 65, 86, 0 };
		gbl_panelLeft.rowHeights = new int[] { 20, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelLeft.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelLeft.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
		panelLeft.setLayout(gbl_panelLeft);

		NumberFormat formatGroesse = NumberFormat.getInstance();
		NumberFormatter formatterGroesse = new NumberFormatter(formatGroesse);
		formatterGroesse.setValueClass(Integer.class);
		// formatterGroesse.setMinimum(101);
		// formatterGroesse.setMaximum(300);
		formatterGroesse.setAllowsInvalid(false);
		formatterGroesse.setCommitsOnValidEdit(false);

		NumberFormat formatGewicht = NumberFormat.getInstance();
		NumberFormatter formatterGewicht = new NumberFormatter(formatGewicht);
		formatterGewicht.setValueClass(Float.class);
		// formatterGewicht.setMinimum(1);
		// formatterGewicht.setMaximum(500);
		formatterGewicht.setAllowsInvalid(false);
		formatterGewicht.setCommitsOnValidEdit(false);

		JLabel lblKoerperGroesse = new JLabel("KörperGrösse");
		lblKoerperGroesse.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblKoerperGroesse = new GridBagConstraints();
		gbc_lblKoerperGroesse.anchor = GridBagConstraints.EAST;
		gbc_lblKoerperGroesse.insets = new Insets(0, 20, 5, 5);
		gbc_lblKoerperGroesse.gridx = 0;
		gbc_lblKoerperGroesse.gridy = 4;
		panelLeft.add(lblKoerperGroesse, gbc_lblKoerperGroesse);

		tfKoerperGroesse = new JFormattedTextField(formatterGroesse);
		tfKoerperGroesse.setColumns(4);
		tfKoerperGroesse.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (tfKoerperGroesse.getValue() != null)
					bmi.setKoerperGroesse((int) tfKoerperGroesse.getValue());
				updateBMI();
			}

		});
		lblKoerperGroesse.setLabelFor(tfKoerperGroesse);
		GridBagConstraints gbc_tfKoerperGroesse = new GridBagConstraints();
		gbc_tfKoerperGroesse.insets = new Insets(0, 0, 5, 0);
		gbc_tfKoerperGroesse.anchor = GridBagConstraints.NORTHWEST;
		gbc_tfKoerperGroesse.gridx = 1;
		gbc_tfKoerperGroesse.gridy = 4;
		panelLeft.add(tfKoerperGroesse, gbc_tfKoerperGroesse);

		JLabel lblGewicht = new JLabel("Gewicht");
		lblGewicht.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblGewicht = new GridBagConstraints();
		gbc_lblGewicht.anchor = GridBagConstraints.EAST;
		gbc_lblGewicht.insets = new Insets(0, 0, 5, 5);
		gbc_lblGewicht.gridx = 0;
		gbc_lblGewicht.gridy = 5;
		panelLeft.add(lblGewicht, gbc_lblGewicht);

		tfGewicht = new JFormattedTextField(formatterGewicht);
		tfGewicht.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (tfGewicht.getValue() != null)
					bmi.setActualGewicht((float) tfGewicht.getValue());
				updateBMI();
			}
		});
		lblGewicht.setLabelFor(tfGewicht);
		GridBagConstraints gbc_tfGewicht = new GridBagConstraints();
		gbc_tfGewicht.anchor = GridBagConstraints.WEST;
		gbc_tfGewicht.insets = new Insets(0, 0, 5, 0);
		gbc_tfGewicht.gridx = 1;
		gbc_tfGewicht.gridy = 5;
		panelLeft.add(tfGewicht, gbc_tfGewicht);
		tfGewicht.setColumns(4);

		JLabel lblAlter = new JLabel("Alter");
		lblAlter.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblAlter = new GridBagConstraints();
		gbc_lblAlter.anchor = GridBagConstraints.EAST;
		gbc_lblAlter.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlter.gridx = 0;
		gbc_lblAlter.gridy = 6;
		panelLeft.add(lblAlter, gbc_lblAlter);

		cbAlter.setModel(new DefaultComboBoxModel<AlterRange>(AlterRange.values()));
		hrow = 1;
		bmiTableCellRenderer.setIndex(hrow);
		cbAlter.setSelectedIndex(hrow);
		arSelected = (AlterRange) cbAlter.getSelectedItem();
		cbAlter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bmiTableCellRenderer.setIndex(cbAlter.getSelectedIndex());
				arSelected = (AlterRange) cbAlter.getSelectedItem();
				hrow = cbAlter.getSelectedIndex();
				updateBMI();
			}
		});
		GridBagConstraints gbc_cbAlter = new GridBagConstraints();
		gbc_cbAlter.anchor = GridBagConstraints.WEST;
		gbc_cbAlter.insets = new Insets(0, 0, 5, 0);
		gbc_cbAlter.gridx = 1;
		gbc_cbAlter.gridy = 6;
		panelLeft.add(cbAlter, gbc_cbAlter);

		JLabel lblGeschlecht = new JLabel("Geschlecht");
		lblGeschlecht.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lbllblGeschlecht = new GridBagConstraints();
		gbc_lbllblGeschlecht.anchor = GridBagConstraints.EAST;
		gbc_lbllblGeschlecht.insets = new Insets(0, 0, 5, 5);
		gbc_lbllblGeschlecht.gridx = 0;
		gbc_lbllblGeschlecht.gridy = 7;
		panelLeft.add(lblGeschlecht, gbc_lbllblGeschlecht);

		JPanel panelGeschlecht = new JPanel();
		GridBagConstraints gbc_panelGeschlecht = new GridBagConstraints();
		gbc_panelGeschlecht.insets = new Insets(0, 0, 5, 0);
		gbc_panelGeschlecht.fill = GridBagConstraints.BOTH;
		gbc_panelGeschlecht.gridx = 1;
		gbc_panelGeschlecht.gridy = 7;
		panelLeft.add(panelGeschlecht, gbc_panelGeschlecht);
		panelGeschlecht.setLayout(new BoxLayout(panelGeschlecht, BoxLayout.Y_AXIS));
		ActionListener rbMF = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("M")) {
					bmi.setMann(true);
				} else {
					bmi.setMann(false);
				}
				updateBMI();
			}
		};
		JRadioButton rdbtnMann = new JRadioButton("Mann");
		rdbtnMann.setActionCommand("M");
		rdbtnMann.addActionListener(rbMF);
		panelGeschlecht.add(rdbtnMann);

		JRadioButton rdbtnFrau = new JRadioButton("Frau");
		rdbtnFrau.setActionCommand("F");
		rdbtnFrau.addActionListener(rbMF);
		panelGeschlecht.add(rdbtnFrau);
		grpGeschlecht.add(rdbtnMann);
		grpGeschlecht.add(rdbtnFrau);
		rdbtnMann.setSelected(true);
		bmi.setMann(true);

		JLabel lblErgebnisBMI = new JLabel("Ergebnis BMI");
		GridBagConstraints gbc_lblErgebnisBMI = new GridBagConstraints();
		gbc_lblErgebnisBMI.anchor = GridBagConstraints.EAST;
		gbc_lblErgebnisBMI.insets = new Insets(0, 0, 5, 5);
		gbc_lblErgebnisBMI.gridx = 0;
		gbc_lblErgebnisBMI.gridy = 8;
		panelLeft.add(lblErgebnisBMI, gbc_lblErgebnisBMI);

		GridBagConstraints gbc_labelBmiValue = new GridBagConstraints();
		gbc_labelBmiValue.insets = new Insets(0, 0, 5, 0);
		gbc_labelBmiValue.gridx = 1;
		gbc_labelBmiValue.gridy = 8;
		panelLeft.add(labelBmiValue, gbc_labelBmiValue);
		JPanel panelRight = new JPanel();
		panelRight.setBorder(null);
		frame.getContentPane().add(panelRight, BorderLayout.EAST);

		table = new JTable(bmiTableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setDefaultRenderer(String.class, bmiTableCellRenderer);
		panelRight.add(
				new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		panelRight.addComponentListener(new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent e) {
				Dimension tpl = panelLeft.getSize();
				Dimension tpd = table.getParent().getParent().getSize();
				int ph = (tpl.height - 30) / 6;
				table.getParent().getParent().setPreferredSize(new Dimension(tpd.width, ph * 6 + 23));
				int rowHeight = table.getRowHeight();
				rowHeight = Math.max(rowHeight, ph);
				for (int row = 0; row < table.getRowCount(); row++) {
					table.setRowHeight(row, rowHeight);
				}
			}

			@Override
			public void componentResized(ComponentEvent e) {
				componentShown(e);
			}

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		panel.add(btnEnd);
	}

}
