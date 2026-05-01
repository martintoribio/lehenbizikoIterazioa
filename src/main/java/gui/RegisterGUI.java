package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.Color;
import java.awt.event.ActionEvent;

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
		setBounds(100, 100, 400, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		emailText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.email"));
		emailText.setBounds(28, 10, 130, 20);
		emailText.setFont(emailText.getFont().deriveFont(emailText.getFont().getStyle() | Font.BOLD));
		contentPane.add(emailText);

		email = new JTextField();
		email.setBounds(168, 10, 130, 20);
		contentPane.add(email);
		email.setColumns(10);

		pasahitzaText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.password"));
		pasahitzaText.setBounds(28, 38, 130, 20);
		pasahitzaText.setFont(pasahitzaText.getFont().deriveFont(pasahitzaText.getFont().getStyle() | Font.BOLD));
		contentPane.add(pasahitzaText);

		pasahitza1 = new JTextField();
		pasahitza1.setBounds(168, 38, 130, 20);
		contentPane.add(pasahitza1);
		pasahitza1.setColumns(10);

		pasahitza2Text = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.passwordrepeat"));
		pasahitza2Text.setBounds(28, 66, 130, 20);
		pasahitza2Text.setFont(pasahitza2Text.getFont().deriveFont(pasahitza2Text.getFont().getStyle() | Font.BOLD));
		contentPane.add(pasahitza2Text);

		pasahitza2 = new JTextField();
		pasahitza2.setBounds(168, 66, 130, 20);
		contentPane.add(pasahitza2);
		pasahitza2.setColumns(10);
		
		tIzena = new JTextField();
		tIzena.setBounds(168, 94, 130, 20);
		contentPane.add(tIzena);
		tIzena.setColumns(10);
		
		tIzenaText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.txartelIzena"));
		tIzenaText.setBounds(28, 94, 130, 20);
		tIzenaText.setFont(tIzenaText.getFont().deriveFont(tIzenaText.getFont().getStyle() | Font.BOLD));
		contentPane.add(tIzenaText);
		
		tZenb = new JTextField();
		tZenb.setBounds(168, 122, 130, 20);
		contentPane.add(tZenb);
		tZenb.setColumns(10);
		
		tZenbText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.txartelZenbakia"));
		tZenbText.setBounds(28, 122, 130, 20);
		tZenbText.setFont(tZenbText.getFont().deriveFont(tZenbText.getFont().getStyle() | Font.BOLD));
		contentPane.add(tZenbText);
		
		PIN = new JTextField();
		PIN.setBounds(168, 150, 130, 20);
		contentPane.add(PIN);
		PIN.setColumns(10);
		
		PINText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.PIN"));
		PINText.setBounds(28, 150, 130, 20);
		PINText.setFont(PINText.getFont().deriveFont(PINText.getFont().getStyle() | Font.BOLD));
		contentPane.add(PINText);
		

		erroreMezua = new JLabel("");
		erroreMezua.setBounds(28, 178, 340, 18);
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
		erregistratuBotoia.setBounds(45, 202, 126, 27);
		contentPane.add(erregistratuBotoia);

		atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame main = new MainGUI();
				main.setVisible(true);
				uneko_pantaila.setVisible(false);
			}
		});
		atzeraButton.setBounds(185, 202, 115, 27);
		contentPane.add(atzeraButton);
		
	}
}