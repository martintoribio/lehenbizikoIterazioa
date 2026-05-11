package gui;

import businessLogic.BLFacade;
import domain.Sale;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

public class QueryBoughtGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPanelProducts = new JScrollPane();
	private JTable tableProducts = new JTable();
	private DefaultTableModel tableModelProducts;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QueryBoughtGUI.name"));

	private String[] columnNamesProducts = new String[] { 
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Title"), 
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Price"),
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Category"),
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.PublicationDate") 
	};

	public QueryBoughtGUI(JFrame pantaila, String email) {
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(750, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryBoughtGUI.name"));

		jLabelTitle.setBounds(50, 30, 300, 20);
		this.getContentPane().add(jLabelTitle);

		jButtonClose.setBounds(new Rectangle(310, 390, 130, 30));
		jButtonClose.addActionListener(e -> {
			this.setVisible(false);
			pantaila.setVisible(true);
		});
		this.getContentPane().add(jButtonClose, null);

		scrollPanelProducts.setBounds(new Rectangle(50, 70, 630, 290));
		tableModelProducts = new DefaultTableModel(null, columnNamesProducts);
		tableProducts.setModel(tableModelProducts);
		tableModelProducts.setColumnCount(5);
		scrollPanelProducts.setViewportView(tableProducts);
		
		tableProducts.getColumnModel().getColumn(0).setPreferredWidth(180);
		tableProducts.getColumnModel().getColumn(1).setPreferredWidth(70);
		tableProducts.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableProducts.getColumnModel().getColumn(3).setPreferredWidth(100);
		
		this.getContentPane().add(scrollPanelProducts, null);

		tableProducts.getColumnModel().removeColumn(tableProducts.getColumnModel().getColumn(4));

		loadBoughtSales(email);
		tableProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				if (mouseEvent.getClickCount() == 2) {
					int row = tableProducts.rowAtPoint(mouseEvent.getPoint());
					Sale s = (Sale) tableModelProducts.getValueAt(row, 4);
					new ShowBoughtSaleGUI(s,email,pantaila);
				}
			}
		});
	}

	private void loadBoughtSales(String email) {
		try {
			BLFacade facade = MainGUI.getBusinessLogic();
			List<Sale> sales = facade.getBoughtSales(email);

			for (Sale sale : sales) {
				Vector<Object> row = new Vector<Object>();
				row.add(sale.getTitle());
				row.add(sale.getPrice());
				row.add(sale.getKategoria());
				row.add(new SimpleDateFormat("dd-MM-yyyy").format(sale.getPublicationDate()));
				row.add(sale);
				tableModelProducts.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}