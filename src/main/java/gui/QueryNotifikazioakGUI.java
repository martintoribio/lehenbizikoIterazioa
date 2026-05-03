package gui;

import businessLogic.BLFacade;
import domain.Mugimendua;
import domain.Notifikazioa;
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

public class QueryNotifikazioakGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPanelProducts = new JScrollPane();
	private JTable tableProducts = new JTable();
	private DefaultTableModel tableModelProducts;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QueryNotifikazioakGUI.name"));
	private JFrame unekoPantaila = this;
	private String[] columnNamesProducts = new String[] { "Notifikazioa", "Kategoria", "Sale"};
	
	public QueryNotifikazioakGUI(String email,JFrame aurrekoPantaila) {
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(600, 400));
		this.setTitle("Notifikazioak");

		jLabelTitle.setBounds(50, 20, 300, 20);
		this.getContentPane().add(jLabelTitle);

		jButtonClose.setBounds(new Rectangle(220, 300, 130, 30));
		jButtonClose.addActionListener(e -> {
			this.setVisible(false);
			aurrekoPantaila.setVisible(true);
		});
		this.getContentPane().add(jButtonClose, null);

		scrollPanelProducts.setBounds(new Rectangle(50, 60, 480, 200));
		scrollPanelProducts.setViewportView(tableProducts);
		this.getContentPane().add(scrollPanelProducts, null);
		tableModelProducts = new DefaultTableModel(null, columnNamesProducts);
		tableProducts.setModel(tableModelProducts);
		tableModelProducts.setColumnCount(3);

		tableProducts.getColumnModel().removeColumn(tableProducts.getColumnModel().getColumn(2));
		
		loadNotifikazioak(email);
		
		tableProducts.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent mouseEvent) {
	            
	            if(mouseEvent.getClickCount() == 2)
	            {
			        JTable table =(JTable) mouseEvent.getSource();
	            	Point point = mouseEvent.getPoint();
			        int row = table.rowAtPoint(point);
	            	Sale s=(Sale) tableModelProducts.getValueAt(row, 2);
		            new ShowSaleGUI(s, email, unekoPantaila);
	            }
	        }
	 });
	}

	private void loadNotifikazioak(String email) {
		try {
			BLFacade facade = MainGUI.getBusinessLogic();
			List<Notifikazioa> notifikazioak = facade.getNotifikazioak(email);
			
			for (Notifikazioa notifikazioa : notifikazioak) {
				Sale sale = notifikazioa.getSale();
				Vector<Object> row = new Vector<Object>();
				row.add(notifikazioa.getMezua());
				row.add(notifikazioa.getKategoria());
				row.add(sale);
				tableModelProducts.addRow(row);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}