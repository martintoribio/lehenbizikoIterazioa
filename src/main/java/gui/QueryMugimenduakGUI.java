package gui;

import businessLogic.BLFacade;
import domain.Mugimendua;
import domain.Sale;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

public class QueryMugimenduakGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPanelProducts = new JScrollPane();
	private JTable tableProducts = new JTable();
	private DefaultTableModel tableModelProducts;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QueryMugimenduakGUI.name"));

	private String[] columnNamesProducts = new String[] { "Mugimendu mota", "Diru aldaketa"};

	public QueryMugimenduakGUI(JFrame pantaila, String email,JFrame aurrekoPantaila) {
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(600, 400));
		this.setTitle("Mugimenduak");

		jLabelTitle.setBounds(50, 20, 300, 20);
		this.getContentPane().add(jLabelTitle);

		jButtonClose.setBounds(new Rectangle(220, 300, 130, 30));
		jButtonClose.addActionListener(e -> {
			this.setVisible(false);
			pantaila.setVisible(true);
		});
		this.getContentPane().add(jButtonClose, null);

		scrollPanelProducts.setBounds(new Rectangle(50, 60, 480, 200));
		tableModelProducts = new DefaultTableModel(null, columnNamesProducts);
		tableProducts.setModel(tableModelProducts);
		tableModelProducts.setColumnCount(3);
		scrollPanelProducts.setViewportView(tableProducts);
		this.getContentPane().add(scrollPanelProducts, null);

		tableProducts.getColumnModel().removeColumn(tableProducts.getColumnModel().getColumn(2));

		loadMovements(email);
	}

	private void loadMovements(String email) {
		try {
			BLFacade facade = MainGUI.getBusinessLogic();
			List<Mugimendua> mugimenduak = facade.getMugimenduak(email);

			for (Mugimendua mugimendua : mugimenduak) {
				Vector<Object> row = new Vector<Object>();
				row.add(mugimendua.getDeskribapena());
				float diruAld = mugimendua.getDiruAld();
				if (diruAld < 0.0)  row.add(diruAld); 
				else  row.add("+" + diruAld);
				row.add(mugimendua);
				tableModelProducts.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}