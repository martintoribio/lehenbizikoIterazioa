package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Sale;
import exceptions.NahikoDirurikEzException;
import domain.Eskaera;
import domain.Eskaintza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class EskaintzaKudeatuGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	private DefaultTableModel tableModelProducts;

	private JFrame thisFrame; 
	private JFrame jasotakoPantaila;
	
	BLFacade facade = MainGUI.getBusinessLogic();
	
	public EskaintzaKudeatuGUI(Eskaintza eskaintza, Eskaera eskaera, String email,JFrame aurrekoPantaila) {;
		thisFrame=this;
		jasotakoPantaila = aurrekoPantaila;
		this.setSize(new Dimension(500, 300));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.EskaeraErantzun"));
		jButtonClose.setBounds(331, 243, 130, 30);

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
		prezioaField.setEnabled(false);
		prezioaField.setText(String.valueOf(eskaintza.getPrezioa()));
		getContentPane().add(prezioaField);
		prezioaField.setColumns(10);
		
		JTextArea erantzunMezuaArea = new JTextArea();
        erantzunMezuaArea.setLineWrap(true);
        erantzunMezuaArea.setWrapStyleWord(true);
        erantzunMezuaArea.setText(eskaintza.getErantzunMezua());
        erantzunMezuaArea.setEnabled(false);
        
        JScrollPane scrollPane = new JScrollPane(erantzunMezuaArea);
        scrollPane.setBounds(50, 75, 350, 70);
        getContentPane().add(scrollPane);
		
		BLFacade facade = MainGUI.getBusinessLogic();
        
		JButton eskaeraOnartuButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Accept"));
		eskaeraOnartuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					boolean b = facade.onartuEskaintza(eskaera, email, eskaintza);
					if (b) {
						mezua.setText(ResourceBundle.getBundle("Etiquetas").getString("EskaeraKudeatuGUI.accepted"));
					} else {
						mezua.setText("");
					}
				} catch (NahikoDirurikEzException ex) {
					mezua.setText(ex.getMessage());
				}

			}
		});
		eskaeraOnartuButton.setBounds(50, 243, 130, 30);
		getContentPane().add(eskaeraOnartuButton);
		
		JButton eskaeraEzeztatuButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Reject"));
		eskaeraEzeztatuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				facade.ezeztatuEskaintza(eskaintza);
				mezua.setText(ResourceBundle.getBundle("Etiquetas").getString("EskaeraKudeatuGUI.rejected"));
			}
		});
		eskaeraEzeztatuButton.setBounds(192, 243, 130, 30);
		getContentPane().add(eskaeraEzeztatuButton);
		
		
		
		
		
		
	}
}


