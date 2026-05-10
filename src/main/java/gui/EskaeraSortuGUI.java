package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Sale;
import domain.Eskaera;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class EskaeraSortuGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	private DefaultTableModel tableModelProducts;

	private JFrame thisFrame; 
	private JFrame jasotakoPantaila;
	
	private String loggedEmail;
	private JTextField textField;
	BLFacade facade = MainGUI.getBusinessLogic();
	
	public EskaeraSortuGUI(JFrame pantaila, String email,JFrame aurrekoPantaila) {
		jasotakoPantaila=pantaila;
		this.loggedEmail = email;
		thisFrame=this;
		this.setSize(new Dimension(500, 300));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.EskaeraSortu"));
		jButtonClose.setBounds(300, 232, 130, 30);

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				thisFrame.setVisible(false);
				jasotakoPantaila.setVisible(true);

			}
		});		
		getContentPane().setLayout(null);
		
		this.getContentPane().add(jButtonClose);
		
		JComboBox<String> jComboBoxKategoria = new JComboBox<String>();
		DefaultComboBoxModel<String> kategoriaOptions = new DefaultComboBoxModel<String>();
		List<String> kategoriak;
		kategoriak=Utils.getKategoriak();
		for(String k:kategoriak) kategoriaOptions.addElement(k);
		jComboBoxKategoria.setModel(kategoriaOptions);
		jComboBoxKategoria.setBounds(50, 144, 250, 25);
		getContentPane().add(jComboBoxKategoria);
		
		JLabel mezua = new JLabel("");
		mezua.setBounds(111, 191, 295, 17);
		getContentPane().add(mezua);
		
		JTextField produktuIzena = new JTextField();
		produktuIzena.setBounds(50, 75, 250, 25);
		getContentPane().add(produktuIzena);
		produktuIzena.setColumns(10);
		
		JLabel produktuKategoriaLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EskaeraSortuGUI.produktuKategoria"));
		produktuKategoriaLbl.setBounds(50, 119, 189, 17);
		getContentPane().add(produktuKategoriaLbl);
		
		JLabel produktuIzenaLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EskaeraSortuGUI.productName")); 
		produktuIzenaLbl.setBounds(50, 50, 189, 17);
		getContentPane().add(produktuIzenaLbl);
		
		JButton eskaintzaSortuButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.EskaeraSortu"));
		eskaintzaSortuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!produktuIzena.getText().trim().isEmpty()) {
					String kategoria = (String)jComboBoxKategoria.getSelectedItem();
					String productName = produktuIzena.getText();
					Eskaera eskaera = facade.sortuEskaera(productName, kategoria, email);
					if (eskaera!=null) {
						mezua.setText(ResourceBundle.getBundle("Etiquetas").getString("EskaeraSortuGUI.mezua"));
					}
				} else {
					mezua.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.blankData"));
				}
			}
		});
		eskaintzaSortuButton.setBounds(50, 232, 160, 30);
		getContentPane().add(eskaintzaSortuButton);
		
		
		
		
		
		
		
	}
}


