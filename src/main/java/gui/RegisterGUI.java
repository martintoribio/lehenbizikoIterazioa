package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel emailText;
	private JLabel pasahitzaText;
	private JLabel pasahitza2Text;
	private JLabel tIzenaText;
	private JLabel tZenbText;
	private JLabel PINText;
	private JLabel erroreMezua;
	private JTextField email;
	private JTextField pasahitza1;
	private JTextField pasahitza2;
	private JTextField tIzena;
	private JTextField tZenb;
	private JTextField PIN;
	private JButton erregistratuBotoia;
	private JButton atzeraButton;
	private JFrame uneko_pantaila;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegisterGUI() {
		uneko_pantaila = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		emailText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.email"));
		emailText.setBounds(30, 30, 150, 25);
		emailText.setFont(emailText.getFont().deriveFont(emailText.getFont().getStyle() | Font.BOLD));
		contentPane.add(emailText);

		email = new JTextField();
		email.setBounds(190, 30, 150, 25);
		contentPane.add(email);
		email.setColumns(10);

		pasahitzaText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.password"));
		pasahitzaText.setBounds(30, 70, 150, 25);
		pasahitzaText.setFont(pasahitzaText.getFont().deriveFont(pasahitzaText.getFont().getStyle() | Font.BOLD));
		contentPane.add(pasahitzaText);

		pasahitza1 = new JTextField();
		pasahitza1.setBounds(190, 70, 150, 25);
		contentPane.add(pasahitza1);
		pasahitza1.setColumns(10);

		pasahitza2Text = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.passwordrepeat"));
		pasahitza2Text.setBounds(30, 110, 150, 25);
		pasahitza2Text.setFont(pasahitza2Text.getFont().deriveFont(pasahitza2Text.getFont().getStyle() | Font.BOLD));
		contentPane.add(pasahitza2Text);

		pasahitza2 = new JTextField();
		pasahitza2.setBounds(190, 110, 150, 25);
		contentPane.add(pasahitza2);
		pasahitza2.setColumns(10);
		
		tIzena = new JTextField();
		tIzena.setBounds(190, 150, 150, 25);
		contentPane.add(tIzena);
		tIzena.setColumns(10);
		
		tIzenaText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.txartelIzena"));
		tIzenaText.setBounds(30, 150, 150, 25);
		tIzenaText.setFont(tIzenaText.getFont().deriveFont(tIzenaText.getFont().getStyle() | Font.BOLD));
		contentPane.add(tIzenaText);
		
		tZenb = new JTextField();
		tZenb.setBounds(190, 190, 150, 25);
		contentPane.add(tZenb);
		tZenb.setColumns(10);
		
		tZenbText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.txartelZenbakia"));
		tZenbText.setBounds(30, 190, 150, 25);
		tZenbText.setFont(tZenbText.getFont().deriveFont(tZenbText.getFont().getStyle() | Font.BOLD));
		contentPane.add(tZenbText);
		
		PIN = new JTextField();
		PIN.setBounds(190, 230, 150, 25);
		contentPane.add(PIN);
		PIN.setColumns(10);
		
		PINText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.PIN"));
		PINText.setBounds(30, 230, 150, 25);
		PINText.setFont(PINText.getFont().deriveFont(PINText.getFont().getStyle() | Font.BOLD));
		contentPane.add(PINText);
		

		erroreMezua = new JLabel("");
		erroreMezua.setBounds(30, 265, 310, 20);
		contentPane.add(erroreMezua);

		erregistratuBotoia = new JButton(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.Register"));
		erregistratuBotoia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!pasahitza1.getText().equals(pasahitza2.getText())) {
					erroreMezua.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.differentPasswords"));
					erroreMezua.setForeground(Color.red);
				} else if (email.getText().trim().isEmpty() || pasahitza1.getText().trim().isEmpty() || pasahitza2.getText().trim().isEmpty() || tIzena.getText().trim().isEmpty() || tZenb.getText().trim().isEmpty() || PIN.getText().trim().isEmpty()){
					erroreMezua.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.blankData"));
					erroreMezua.setForeground(Color.red);
				} else {
					try {
						erroreMezua.setText("");
						BLFacade b=MainGUI.getBusinessLogic();
						boolean ald=b.isRegister(email.getText(),pasahitza1.getText(), tIzena.getText(), tZenb.getText(), Integer.parseInt(PIN.getText()));
						if (ald) {
							JFrame kategor = new KategoriakGUI(email.getText());
							kategor.setVisible(true);
							uneko_pantaila.setVisible(false);
						}else {
							erroreMezua.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.registError"));
						}
					} catch (NumberFormatException ex) {
						erroreMezua.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.numberError"));
						erroreMezua.setForeground(Color.red);
					}
				}
			}
		});
		erregistratuBotoia.setBounds(125, 295, 150, 40);
		contentPane.add(erregistratuBotoia);
		erregistratuBotoia.setEnabled(false);
		
		DocumentListener fieldListener = new DocumentListener() {
		    public void insertUpdate(DocumentEvent e) { checkFields(); }
		    public void removeUpdate(DocumentEvent e) { checkFields(); }
		    public void changedUpdate(DocumentEvent e) { checkFields(); }

		    private void checkFields() {
		        boolean hasEmail = !email.getText().trim().isEmpty();
		        boolean hasPass1 = !pasahitza1.getText().trim().isEmpty();
		        boolean hasPass2 = !pasahitza2.getText().trim().isEmpty();
		        boolean hasTIzena = !tIzena.getText().trim().isEmpty();
		        boolean hasTZenb = !tZenb.getText().trim().isEmpty();
		        boolean hasPIN = !PIN.getText().trim().isEmpty();
		        
		        erregistratuBotoia.setEnabled(hasEmail && hasPass1 && hasPass2 && hasTIzena && hasTZenb && hasPIN);
		    }
		};
		
		email.getDocument().addDocumentListener(fieldListener);
		pasahitza1.getDocument().addDocumentListener(fieldListener);
		pasahitza2.getDocument().addDocumentListener(fieldListener);
		tIzena.getDocument().addDocumentListener(fieldListener);
		tZenb.getDocument().addDocumentListener(fieldListener);
		PIN.getDocument().addDocumentListener(fieldListener);

		atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame main = new MainGUI();
				main.setVisible(true);
				uneko_pantaila.setVisible(false);
			}
		});
		atzeraButton.setBounds(150, 355, 100, 30);
		contentPane.add(atzeraButton);
		
	}
}