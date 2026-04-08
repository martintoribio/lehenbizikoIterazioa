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

public class QueryAztertzekoSalaketakGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPanelProducts = new JScrollPane();
	private JTable tableProducts = new JTable();
	private DefaultTableModel tableModelProducts;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QuerySalaketakGUI.name"));

	private String[] columnNamesProducts = new String[] { "Salaketa", "Erabiltzailea"};

	public QueryAztertzekoSalaketakGUI(JFrame pantaila, String email,JFrame aurrekoPantaila) {
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
		tableModelProducts.setColumnCount(3);
		tableProducts.getColumnModel().removeColumn(tableProducts.getColumnModel().getColumn(2));
		scrollPanelProducts.setViewportView(tableProducts);
		this.getContentPane().add(scrollPanelProducts, null);
		loadReports(email);
		
		tableProducts.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent mouseEvent) {
	            
	            if(mouseEvent.getClickCount() == 2)
	            {
			        JTable table =(JTable) mouseEvent.getSource();
	            	Point point = mouseEvent.getPoint();
			        int row = table.rowAtPoint(point);
	            	Salaketa s=(Salaketa) tableModelProducts.getValueAt(row, 2);
		            new SalaketaIkusiGUI(s, pantaila);
	            }
	        }
	 });
	}

	private void loadReports(String email) {
		try {
			BLFacade facade = MainGUI.getBusinessLogic();
			List<Salaketa> salaketak = facade.getAztertzekoSalaketak();
			
			for (Salaketa salaketa : salaketak) {
				Vector<Object> row = new Vector<Object>();
				Sale sale = salaketa.getSale();
				row.add(sale.getTitle());
				row.add(salaketa.getUser().getEmail());
				row.add(salaketa);
				tableModelProducts.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tableProducts.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableProducts.getColumnModel().getColumn(1).setPreferredWidth(150);
	}
	
}