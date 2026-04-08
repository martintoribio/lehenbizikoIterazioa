package gui;

import businessLogic.BLFacade;
import domain.Erreklamazioa;
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

public class QueryErreklamazioakGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPanelProducts = new JScrollPane();
	private JTable tableProducts = new JTable();
	private DefaultTableModel tableModelProducts;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QueryErreklamazioakGUI.name"));

	private String[] columnNamesProducts = new String[] { "Erreklamazioa", "Egoera"};

	public QueryErreklamazioakGUI(JFrame pantaila, String email,JFrame aurrekoPantaila) {
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(600, 400));
		this.setTitle("Erreklamazioak");

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

		loadErreklamazioak(email);
	}

	private void loadErreklamazioak(String email) {
		try {
			BLFacade facade = MainGUI.getBusinessLogic();
			List<Erreklamazioa> erreklamazioak = facade.getErreklamazioak(email);
			
			for (Erreklamazioa errek : erreklamazioak) {
				Vector<Object> row = new Vector<Object>();
				Sale sale = errek.getSale();
				row.add(sale.getTitle());
				row.add(errek.getEgoera());
				tableModelProducts.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}