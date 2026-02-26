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

public class QueryFavoritesGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPanelProducts = new JScrollPane();
	private JTable tableProducts = new JTable();
	private DefaultTableModel tableModelProducts;
	private JButton jButtonClose = new JButton("Itxi");
	private JLabel jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QueryFavourites.add"));

	private String[] columnNamesProducts = new String[] { "Izena", "Prezioa", "Data" };

	public QueryFavoritesGUI(JFrame pantaila, String email,JFrame aurrekoPantaila) {
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(600, 400));
		this.setTitle("Nire gogokoenak");

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
		tableModelProducts.setColumnCount(4);
		scrollPanelProducts.setViewportView(tableProducts);
		this.getContentPane().add(scrollPanelProducts, null);

		tableProducts.getColumnModel().removeColumn(tableProducts.getColumnModel().getColumn(3));

		loadFavorites(email);

		tableProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				if (mouseEvent.getClickCount() == 2) {
					int row = tableProducts.rowAtPoint(mouseEvent.getPoint());
					Sale s = (Sale) tableModelProducts.getValueAt(row, 3);
					new ShowSaleGUI(s,email,aurrekoPantaila);
				}
			}
		});
	}

	private void loadFavorites(String email) {
		try {
			BLFacade facade = MainGUI.getBusinessLogic();
			List<Sale> sales = facade.getFavorites(email);

			for (Sale sale : sales) {
				Vector<Object> row = new Vector<Object>();
				row.add(sale.getTitle());
				row.add(sale.getPrice());
				row.add(new SimpleDateFormat("dd-MM-yyyy").format(sale.getPublicationDate()));
				row.add(sale);
				tableModelProducts.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}