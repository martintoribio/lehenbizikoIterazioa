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


public class EskaeraErantzunGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	private DefaultTableModel tableModelProducts;

	private JFrame thisFrame; 
	private JFrame jasotakoPantaila;
	
	private String loggedEmail;
	BLFacade facade = MainGUI.getBusinessLogic();
	
	public EskaeraErantzunGUI(Eskaera eskaera, String email,JFrame aurrekoPantaila) {
		this.loggedEmail = email;
		thisFrame=this;
		jasotakoPantaila = aurrekoPantaila;
		this.setSize(new Dimension(500, 300));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.EskaeraErantzun"));
		jButtonClose.setBounds(295, 243, 130, 30);

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
		
		
		JLabel mezua = new JLabel("");
		mezua.setBounds(111, 202, 281, 17);
		getContentPane().add(mezua);
		
		JLabel erantzunMezuaLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EskaeraErantzunGUI.answer")); 
		erantzunMezuaLbl.setBounds(50, 50, 189, 17);
		getContentPane().add(erantzunMezuaLbl);
		
		JLabel produktuKategoriaLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Price"));
		produktuKategoriaLbl.setBounds(50, 157, 189, 17);
		getContentPane().add(produktuKategoriaLbl);

		JTextField prezioaField = new JTextField();
		prezioaField.setBounds(50, 179, 114, 21);
		getContentPane().add(prezioaField);
		prezioaField.setColumns(10);
		
		JTextArea erantzunMezuaArea = new JTextArea();
        erantzunMezuaArea.setLineWrap(true);
        erantzunMezuaArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(erantzunMezuaArea);
        scrollPane.setBounds(50, 75, 350, 70);
        getContentPane().add(scrollPane);
		
		JButton eskaeraErantzunButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.EskaeraErantzun"));
		eskaeraErantzunButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!erantzunMezuaArea.getText().trim().isEmpty() && !prezioaField.getText().trim().isEmpty()) {
					try {
						BLFacade facade = MainGUI.getBusinessLogic();
						String erantzunMezua = erantzunMezuaArea.getText();
						float prezioa = Float.parseFloat(prezioaField.getText());
						facade.erantzunEskaera(erantzunMezua, prezioa, eskaera, email);
						mezua.setText(ResourceBundle.getBundle("Etiquetas").getString("EskaeraErantzunGUI.mezua"));
					} catch (NumberFormatException ex) {
						mezua.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.ErrorNumber"));
					}
				} else {
					mezua.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.blankData"));
				}
			}
		});
		eskaeraErantzunButton.setBounds(50, 243, 160, 30);
		getContentPane().add(eskaeraErantzunButton);
		
		
		
		
		
		
		
		
		
	}
}


