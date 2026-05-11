package gui;

import businessLogic.BLFacade;
import domain.Sale;
import domain.User;
import exceptions.NahikoDirurikEzException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class QueryMultipleSalesGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final JLabel jLabelProducts = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QueryMultipleSalesGUI.FindProducts")); 
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JButton jButtonBuy = new JButton(ResourceBundle.getBundle("Etiquetas").getString("QueryMultipleSalesGUI.BuyButton")); 
	private JLabel jLabelSaldoa = new JLabel();

	private JScrollPane scrollPanelProducts = new JScrollPane();
	private JTable tableProducts = new JTable();
	private DefaultTableModel tableModelProducts;

	private JFrame thisFrame; 
	private JFrame jasotakoPantaila;
	
	private String[] columnNamesProducts = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Title"), 
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Price"),
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.PublicationDate"),
			ResourceBundle.getBundle("Etiquetas").getString("QueryMultipleSalesGUI.Select") 
	};
	
	private String loggedEmail;
	private List<Sale> displayedSales = new ArrayList<>(); 
	
	public QueryMultipleSalesGUI(JFrame pantaila, String email, List<Sale> saskia) {
		jasotakoPantaila = pantaila;
		this.loggedEmail = email;
		thisFrame = this;
		
		BLFacade facade = MainGUI.getBusinessLogic();

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryMultipleSalesGUI.EndBuy"));

		jLabelSaldoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelSaldoa.setForeground(new Color(0, 128, 0)); 
		jLabelSaldoa.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelSaldoa.setBounds(420, 20, 230, 25);
		
		float oraingoSaldoa = facade.getSaldoa(loggedEmail);
		jLabelSaldoa.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryMultipleSalesGUI.LabelSaldoa") + ": " + String.format("%.2f", oraingoSaldoa) + "€");
		this.getContentPane().add(jLabelSaldoa);

		tableModelProducts = new DefaultTableModel(null, columnNamesProducts) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return columnIndex == 3 ? Boolean.class : super.getColumnClass(columnIndex);
			}
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 3; 
			}
		};

		tableProducts.setModel(tableModelProducts);
		tableProducts.setEnabled(true); 

		scrollPanelProducts.setBounds(new Rectangle(52, 137, 518, 150));
		scrollPanelProducts.setViewportView(tableProducts);
		this.getContentPane().add(scrollPanelProducts);

		jLabelProducts.setBounds(52, 108, 427, 16);
		this.getContentPane().add(jLabelProducts);

		jButtonClose.setBounds(new Rectangle(100, 372, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);
				jasotakoPantaila.setVisible(true);
			}
		});
		this.getContentPane().add(jButtonClose);

		jButtonBuy.setBounds(new Rectangle(350, 372, 180, 30));
		jButtonBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Sale> hautatutakoak = new ArrayList<>();
				List<Integer> errenkadak = new ArrayList<>();

				for (int i = 0; i < tableModelProducts.getRowCount(); i++) {
					Boolean isSelected = (Boolean) tableModelProducts.getValueAt(i, 3);
					if (isSelected != null && isSelected) {
						hautatutakoak.add(displayedSales.get(i));
						errenkadak.add(i);
					}
				}

				if (hautatutakoak.isEmpty()) {
					JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("QueryMultipleSalesGUI.ErrorNoSelect"));
					return;
				}

				try {
					for (Sale s : hautatutakoak) {
						facade.buy(s, loggedEmail);
						saskia.add(s);
					}

					JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("QueryMultipleSalesGUI.Success"));
					
					float saldoBerria = facade.getSaldoa(loggedEmail);
					jLabelSaldoa.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryMultipleSalesGUI.LabelSaldoa") + ": " + String.format("%.2f", saldoBerria) + "€");
					
					for (int j = errenkadak.size() - 1; j >= 0; j--) {
						int index = errenkadak.get(j);
						tableModelProducts.removeRow(index);
						displayedSales.remove(index);
					}

				} catch (NahikoDirurikEzException ex) {
					JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("NahikoDirurikEzException"), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		this.getContentPane().add(jButtonBuy);

		try {
			if (!saskia.isEmpty()) {
				User seller = saskia.get(0).getSeller();
				List<domain.Sale> sales = facade.getSellerSales(loggedEmail, seller);

				tableModelProducts.setRowCount(0);
				displayedSales.clear();

				for (domain.Sale s : sales) {
					if (!saskia.contains(s)) {
						Vector<Object> row = new Vector<>();
						row.add(s.getTitle());
						row.add(s.getPrice());
						row.add(new SimpleDateFormat("dd-MM-yyyy").format(s.getPublicationDate()));
						row.add(false); 
						tableModelProducts.addRow(row);
						displayedSales.add(s); 
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}