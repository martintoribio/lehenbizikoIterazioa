package gui;

import businessLogic.BLFacade;
import domain.Mugimendua;
import domain.Salaketa;
import domain.Sale;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

public class QuerySalaketakGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPanelProducts = new JScrollPane();
	private JTable tableProducts = new JTable();
	private DefaultTableModel tableModelProducts;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QuerySalaketakGUI.name"));

	private String[] columnNamesProducts = new String[] { "Salaketa", "Egoera"};

	public QuerySalaketakGUI(JFrame pantaila, String email,JFrame aurrekoPantaila) {
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(600, 400));
		this.setTitle("Salaketak");

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
		tableModelProducts.setColumnCount(2);
		scrollPanelProducts.setViewportView(tableProducts);
		this.getContentPane().add(scrollPanelProducts, null);

		loadReports(email);
	}

	private void loadReports(String email) {
		try {
			BLFacade facade = MainGUI.getBusinessLogic();
			List<Salaketa> salaketak = facade.getSalaketak(email);
			
			for (Salaketa salaketa : salaketak) {
				Vector<Object> row = new Vector<Object>();
				Sale sale = salaketa.getSale();
				row.add(sale.getTitle());
				row.add(salaketa.getEgoera());
				tableModelProducts.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}