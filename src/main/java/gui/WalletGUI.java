package gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import businessLogic.BLFacade;
import exceptions.NahikoDirurikEzException;
import exceptions.TxartelOkerraException;

public class WalletGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private String email;

	private JLabel jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.Title"));
	private JLabel jLabelSaldo = new JLabel();
	private JLabel jLabelKantitatea = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.Amount"));
	private JLabel jLabelPin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.Pin"));

	private JTextField textKantitatea = new JTextField();
	private JPasswordField textPin = new JPasswordField();

	private JButton btnGehitu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.Add"));
	private JButton btnAtera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.Remove"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	private JLabel jLabelError = new JLabel();

	public WalletGUI(String email, JFrame aurrekoPantaila) {

		this.email = email;
		BLFacade bl = MainGUI.getBusinessLogic();

		this.setSize(500, 420);
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.Title"));
		getContentPane().setLayout(null);


		jLabelTitle.setBounds(150, 20, 200, 30);
		jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(jLabelTitle);

		
		jLabelSaldo.setBounds(100, 80, 300, 25);
		jLabelSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelSaldo.setFont(new Font("Tahoma", Font.BOLD, 14));
		float saldo = bl.getSaldoa(email);
		updateTextSaldo(saldo);
		getContentPane().add(jLabelSaldo);

		
		jLabelKantitatea.setBounds(110, 140, 100, 25);
		jLabelKantitatea.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(jLabelKantitatea);

		textKantitatea.setBounds(220, 140, 100, 25);
		getContentPane().add(textKantitatea);
		
		JLabel lblEuro = new JLabel("€");
		lblEuro.setBounds(330, 140, 20, 25);
		lblEuro.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblEuro);
	
		
		jLabelPin.setBounds(110, 190, 100, 25);
		jLabelPin.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(jLabelPin);

		textPin.setBounds(220, 190, 100, 25);
		getContentPane().add(textPin);

		
		btnGehitu.setBounds(110, 260, 120, 35);
		btnGehitu.setEnabled(false);
		getContentPane().add(btnGehitu);

		btnAtera.setBounds(260, 260, 120, 35);
		btnAtera.setEnabled(false);
		getContentPane().add(btnAtera);

	
		jButtonClose.setBounds(185, 320, 120, 30);
		getContentPane().add(jButtonClose);

		
		jLabelError.setBounds(50, 360, 400, 20);
		jLabelError.setForeground(Color.RED);
		jLabelError.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(jLabelError);
		
		
		DocumentListener fieldListener = new DocumentListener() {
			public void insertUpdate(DocumentEvent e) { checkFields(); }
			public void removeUpdate(DocumentEvent e) { checkFields(); }
			public void changedUpdate(DocumentEvent e) { checkFields(); }

			private void checkFields() {
				boolean hasAmount = !textKantitatea.getText().trim().isEmpty();
				boolean hasPin = textPin.getPassword().length > 0;
				boolean enableButtons = hasAmount && hasPin;
				
				btnGehitu.setEnabled(enableButtons);
				btnAtera.setEnabled(enableButtons);
			}
		};
		textKantitatea.getDocument().addDocumentListener(fieldListener);
		textPin.getDocument().addDocumentListener(fieldListener);
		
		
		btnGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					float kantitatea = Float.parseFloat(textKantitatea.getText());
					if (kantitatea <= 0.0) {
						jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.Negative"));
					} else {
						jLabelError.setText("");
						int pin = Integer.parseInt(new String(textPin.getPassword()));
						bl.diruaGehitu(email, kantitatea, pin);
						
						float saldoBerria = bl.getSaldoa(email);
						updateTextSaldo(saldoBerria);
						cleanFields();
					}
				} catch (TxartelOkerraException ex) {
					jLabelError.setText(ex.getMessage());
				} catch (NumberFormatException ex) {
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.NumberError"));
				}
			}
		});

		btnAtera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					float kantitatea = Float.parseFloat(textKantitatea.getText());
					if (kantitatea <= 0.0) {
						jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.Negative"));
					} else {
						jLabelError.setText("");
						int pin = Integer.parseInt(new String(textPin.getPassword()));
						bl.diruaAtera(email, kantitatea, pin);
						
						float saldoBerria = bl.getSaldoa(email);
						updateTextSaldo(saldoBerria);
						cleanFields();
					}
				} catch (TxartelOkerraException ex) {
					jLabelError.setText(ex.getMessage());
				} catch (NumberFormatException ex) {
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.NumberError"));
				} catch (NahikoDirurikEzException ex) {
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.NoMoney"));
				}
			}
		});

		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aurrekoPantaila.setVisible(true);
				dispose();
			}
		});
	}
	
	private void updateTextSaldo(float saldo) {
		jLabelSaldo.setText(ResourceBundle.getBundle("Etiquetas").getString("Wallet.Balance") + ": " + String.format("%.2f €", saldo));
	}
	
	private void cleanFields() {
		textKantitatea.setText("");
		textPin.setText("");
	}
}