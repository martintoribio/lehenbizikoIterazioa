package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Arduraduna;
import domain.Salaketa;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class OfertaSalatuGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea deskribapenaTextArea;
	private JFrame nirePantaila;

	/**
	 * Create the frame.
	 */
	public OfertaSalatuGUI(String email, JFrame pantaila, int saleNumber) {
		nirePantaila = this;
		JLabel erroreMezua = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel deskribapenaText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("OfertaSalatuGUI.description"));
		deskribapenaText.setBounds(30, 30, 141, 14);
		contentPane.add(deskribapenaText);


		deskribapenaTextArea = new JTextArea();
		deskribapenaTextArea.setLineWrap(true);
		deskribapenaTextArea.setWrapStyleWord(true);;
		JScrollPane scrollPane = new JScrollPane(deskribapenaTextArea);
		scrollPane.setBounds(30, 45, 370, 100);
		contentPane.add(scrollPane);

		
		
		JButton salatuBotoia = new JButton(ResourceBundle.getBundle("Etiquetas").getString("OfertaSalatuGUI.report"));
		salatuBotoia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade facade = MainGUI.getBusinessLogic();
				if (!deskribapenaTextArea.getText().trim().isEmpty()) {
					String deskribapena = deskribapenaTextArea.getText();
					Salaketa salaketa = facade.sortuSalaketa(email, deskribapena, saleNumber);
					if (salaketa==null) {
						erroreMezua.setText("Error");
					} else {
						erroreMezua.setText("");
						nirePantaila.dispose();
					}
				} else {
					erroreMezua.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.blankData"));
				}


				
			}
		});
		salatuBotoia.setBounds(91, 200, 126, 27);
		contentPane.add(salatuBotoia);

		JButton atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nirePantaila.setVisible(false);
			}
		});
		atzeraButton.setBounds(242, 200, 115, 27);
		contentPane.add(atzeraButton);
		
		
		erroreMezua.setBounds(118, 175, 246, 20);
		contentPane.add(erroreMezua);

	}
}

