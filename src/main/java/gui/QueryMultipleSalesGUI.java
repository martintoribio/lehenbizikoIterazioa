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
	
	// --- CAMBIO: Botón de pago y etiqueta de saldo ---
	private JButton jButtonBuy = new JButton(); 
	private JLabel jLabelSaldoa = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QueryMultipleSalesGUI.LabelSaldoa"));

	private JScrollPane scrollPanelProducts = new JScrollPane();
	private JTable tableProducts = new JTable();
	private DefaultTableModel tableModelProducts;

	private JFrame thisFrame; 
	private JFrame jasotakoPantaila;
	
	private String[] columnNamesProducts = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Title"), 
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Price"),
			ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.PublicationDate"),
			"Seleccionar" 
	};
	
	private String loggedEmail;
	private List<Sale> displayedSales = new ArrayList<>(); 
	
	public QueryMultipleSalesGUI(JFrame pantaila, String email, JFrame aurrekoPantaila, List<Sale> saskia) {
		jasotakoPantaila = pantaila;
		this.loggedEmail = email;
		thisFrame = this;
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryMultipleSalesGUI.EndBuy"));

		jLabelSaldoa.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelSaldoa.setForeground(new Color(0, 128, 0)); 
		jLabelSaldoa.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelSaldoa.setBounds(420, 20, 230, 25);
		actualizarSaldoUI(); 
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

		// Botón Cerrar
		jButtonClose.setBounds(new Rectangle(100, 372, 130, 30));
		jButtonClose.addActionListener(e -> {
			thisFrame.setVisible(false);
			jasotakoPantaila.setVisible(true);
		});
		this.getContentPane().add(jButtonClose);

		// --- CAMBIO: Lógica de Compra usando el método buy(s, email) ---
		jButtonBuy.setBounds(new Rectangle(350, 372, 180, 30));
		jButtonBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				List<Sale> seleccionados = new ArrayList<>();

				// 1. Identificar seleccionados
				for (int i = 0; i < tableModelProducts.getRowCount(); i++) {
					Boolean isSelected = (Boolean) tableModelProducts.getValueAt(i, 3);
					if (isSelected != null && isSelected) {
						seleccionados.add(displayedSales.get(i));
					}
				}

				if (seleccionados.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No has seleccionado ningún artículo.");
					return;
				}

				// 2. Procesar compras una a una en la Base de Datos
				try {
					for (Sale s : seleccionados) {
						// El método buy ya gestiona el saldo y marca el objeto como comprado en la DB
						facade.buy(s, loggedEmail); 
						
						// Opcional: si tu método buy NO gestiona el ingreso al vendedor, descomenta esto:
						// facade.diruaGehitu(s.getSeller().getEmail(), (float)s.getPrice(), 1234);
					}

					JOptionPane.showMessageDialog(null, "Compra realizada con éxito. Los artículos se han eliminado de la lista.");
					
					// 3. Refrescar la pantalla
					actualizarSaldoUI();
					cargarDatos(saskia); // Esto hará que los artículos comprados ya no salgan

				} catch (NahikoDirurikEzException ex) {
					JOptionPane.showMessageDialog(null, "No tienes saldo suficiente para completar la operación.", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage());
				}
			}
		});
		this.getContentPane().add(jButtonBuy);

		cargarDatos(saskia);
	}

	// --- CAMBIO: Método para obtener el saldo actualizado del servidor ---
	private void actualizarSaldoUI() {
		try {
			BLFacade facade = MainGUI.getBusinessLogic();
			float saldo = facade.getSaldoa(loggedEmail);
			jLabelSaldoa.setText("Tu Saldo: " + String.format("%.2f", saldo) + "€");
		} catch (Exception e) {
			jLabelSaldoa.setText("Saldo: ---");
		}
	}

	// --- CAMBIO: Carga de datos filtrando los artículos ya comprados (bought == true) ---
	private void cargarDatos(List<Sale> saskia) {
		try {
			BLFacade facade = MainGUI.getBusinessLogic();
			tableModelProducts.setRowCount(0); // Limpiar tabla
			displayedSales.clear();

			// Asumimos que queremos ver los artículos del vendedor del primer ítem que nos pasaron
			User seller = saskia.get(0).getSeller();
			List<domain.Sale> sales = facade.getSellerSales(loggedEmail, seller);

			for (domain.Sale s : sales) {
				// SÓLO añadimos a la tabla si NO está comprado y NO está ya en el carrito temporal
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}