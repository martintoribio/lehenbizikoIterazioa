package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Sale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class QuerySalesGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final JLabel jLabelProducts = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.Products")); 

	private JButton jButtonSearch = new JButton(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.Search")); 
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	private JScrollPane scrollPanelProducts = new JScrollPane();
	private JTable tableProducts= new JTable();

	private DefaultTableModel tableModelProducts;

	private JFrame thisFrame; 
	private JFrame jasotakoPantaila;
	private String[] columnNamesProducts = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Title"), 
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Price"), 
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Category"),
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.PublicationDate"),

	};
	private JTextField jTextFieldSearch;
	
	private String loggedEmail;

	
	public QuerySalesGUI(JFrame pantaila, String email) {
		jasotakoPantaila=pantaila;
		this.loggedEmail = email;
		tableProducts.setEnabled(false);
		thisFrame=this;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(750, 500));
		
		jTextFieldSearch = new JTextField();
		jTextFieldSearch.setBounds(50, 30, 450, 30);
		getContentPane().add(jTextFieldSearch);
		jTextFieldSearch.setColumns(10);
		
		jButtonSearch.setBounds(520, 30, 150, 30);
		getContentPane().add(jButtonSearch);
		
		jLabelProducts.setBounds(50, 80, 400, 20);
		this.getContentPane().add(jLabelProducts);
		
		scrollPanelProducts.setBounds(new Rectangle(50, 110, 630, 250));
		scrollPanelProducts.setViewportView(tableProducts);
		tableModelProducts = new DefaultTableModel(null, columnNamesProducts);
		tableProducts.setModel(tableModelProducts);
		
		tableModelProducts.setDataVector(null, columnNamesProducts);
		tableModelProducts.setColumnCount(5);
		
		tableProducts.getColumnModel().getColumn(0).setPreferredWidth(180);
		tableProducts.getColumnModel().getColumn(1).setPreferredWidth(70);
		tableProducts.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableProducts.getColumnModel().getColumn(3).setPreferredWidth(100);
		
		tableProducts.getColumnModel().removeColumn(tableProducts.getColumnModel().getColumn(4));
		this.getContentPane().add(scrollPanelProducts, null);
		
		jButtonClose.setBounds(310, 390, 130, 30);
		this.getContentPane().add(jButtonClose, null);
		
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);
				jasotakoPantaila.setVisible(true);
			}
		});		
		
		jButtonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tableModelProducts.setDataVector(null, columnNamesProducts);
					tableModelProducts.setColumnCount(5);

					BLFacade facade = MainGUI.getBusinessLogic();
					Date today = UtilDate.trim(new Date());
					List<domain.Sale> sales = facade.getPublishedSales(jTextFieldSearch.getText(), today);

					if (sales.isEmpty()) jLabelProducts.setText(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.NoProducts"));
					else jLabelProducts.setText(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.Products"));

					for (domain.Sale sale : sales) {
						if (!sale.isBought()) {
							Vector<Object> row = new Vector<Object>();
							row.add(sale.getTitle());
							row.add(sale.getPrice());
							row.add(sale.getKategoria());
							row.add(new SimpleDateFormat("dd-MM-yyyy").format(sale.getPublicationDate()));
							row.add(sale);
							tableModelProducts.addRow(row);
						}
					}

					tableProducts.getColumnModel().getColumn(0).setPreferredWidth(180);
					tableProducts.getColumnModel().getColumn(1).setPreferredWidth(70);
					tableProducts.getColumnModel().getColumn(2).setPreferredWidth(100);
					tableProducts.getColumnModel().getColumn(3).setPreferredWidth(100);
					tableProducts.getColumnModel().removeColumn(tableProducts.getColumnModel().getColumn(4)); 
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		tableProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				if(mouseEvent.getClickCount() == 2) {
					JTable table =(JTable) mouseEvent.getSource();
					Point point = mouseEvent.getPoint();
					int row = table.rowAtPoint(point);
					Sale s=(Sale) tableModelProducts.getValueAt(row, 4);
					new ShowSaleGUI(s, loggedEmail,pantaila);
				}
			}
		});
	}
}

