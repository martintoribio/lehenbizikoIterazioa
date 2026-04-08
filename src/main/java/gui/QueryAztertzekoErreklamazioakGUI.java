package gui;

import businessLogic.BLFacade;
import domain.Erreklamazioa;
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

public class QueryAztertzekoErreklamazioakGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPanelProducts = new JScrollPane();
	private JTable tableProducts = new JTable();
	private DefaultTableModel tableModelProducts;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QueryErreklamazioakGUI.name"));
	private JFrame unekoPantaila = this;
	private String[] columnNamesProducts = new String[] { "Erreklamazioa", "Produktua", "Erabiltzailea"};

	public QueryAztertzekoErreklamazioakGUI(JFrame pantaila, String email,JFrame aurrekoPantaila) {
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(750, 300));
		this.setTitle("Erreklamazioak");
		
		jLabelTitle.setBounds(50, 20, 400, 20);
		this.getContentPane().add(jLabelTitle);

		jButtonClose.setBounds(new Rectangle(220, 300, 130, 30));
		jButtonClose.addActionListener(e -> {
			this.setVisible(false);
			pantaila.setVisible(true);
		});
		this.getContentPane().add(jButtonClose, null);

		scrollPanelProducts.setBounds(new Rectangle(50, 60, 650, 200));
		tableModelProducts = new DefaultTableModel(null, columnNamesProducts);
		tableProducts.setModel(tableModelProducts);
		tableModelProducts.setColumnCount(4);
		tableProducts.getColumnModel().removeColumn(tableProducts.getColumnModel().getColumn(3));
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
	            	Erreklamazioa e=(Erreklamazioa) tableModelProducts.getValueAt(row, 3);
		            new ErreklamazioaIkusiGUI(e, pantaila, email);
		            unekoPantaila.setVisible(false);
	            }
	        }
	 });
	}

	private void loadReports(String email) {
		try {
			BLFacade facade = MainGUI.getBusinessLogic();
			List<Erreklamazioa> erreklamazioak = facade.getAztertzekoErreklamazioak();
			
			for (Erreklamazioa erreklamazioa : erreklamazioak) {
				Sale sale = erreklamazioa.getSale();
				if (sale.getTitle().isEmpty() && sale!=null) {
					Vector<Object> row = new Vector<Object>();
					row.add(erreklamazioa.getTitulua());
					row.add(sale.getTitle());
					row.add(erreklamazioa.getUser().getEmail());
					row.add(erreklamazioa);
					tableModelProducts.addRow(row);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tableProducts.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableProducts.getColumnModel().getColumn(1).setPreferredWidth(150);
	}
	
}