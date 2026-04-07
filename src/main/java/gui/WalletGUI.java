package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import businessLogic.BLFacade;
import exceptions.NahikoDirurikEzException;

public class WalletGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private String email;

	private JLabel jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.Title"));
	private JLabel jLabelSaldo = new JLabel();
	private JLabel jLabelKantitatea = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.Amount"));
	private JLabel jLabelPin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.Pin"));

	private JTextField textKantitatea = new JTextField();
	private JTextField textPin = new JTextField();

	private JButton btnGehitu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.Add"));
	private JButton btnAtera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.Remove"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	private JLabel jLabelError = new JLabel();

	public WalletGUI(String email) {

		this.email = email;

		this.setSize(400, 300);
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.Title"));
		getContentPane().setLayout(new GridLayout(7, 1, 5, 5));

		BLFacade bl = MainGUI.getBusinessLogic();

		
		jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		getContentPane().add(jLabelTitle);

	
		float saldo = bl.getSaldoa(email);
		jLabelSaldo.setText(ResourceBundle.getBundle("Etiquetas").getString("Wallet.Balance") + ": " + saldo);
		jLabelSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(jLabelSaldo);

	
		JPanel panelKantitatea = new JPanel(new FlowLayout());
		panelKantitatea.add(jLabelKantitatea);
		textKantitatea.setColumns(10);
		panelKantitatea.add(textKantitatea);
		getContentPane().add(panelKantitatea);

	
		JPanel panelPin = new JPanel(new FlowLayout());
		panelPin.add(jLabelPin);
		textPin.setColumns(10);
		panelPin.add(textPin);
		getContentPane().add(panelPin);

		
		JPanel panelBotones = new JPanel(new FlowLayout());
		panelBotones.add(btnGehitu);
		panelBotones.add(btnAtera);
		getContentPane().add(panelBotones);

	
		jLabelError.setForeground(Color.RED);
		jLabelError.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(jLabelError);

	
		getContentPane().add(jButtonClose);

		btnGehitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					float kantitatea = Float.parseFloat(textKantitatea.getText());
					int pin = Integer.parseInt(textPin.getText());

					if (bl.egiaztatuPin(email, pin)) {

						bl.diruaGehitu(email, kantitatea);

						float saldoBerria = bl.getSaldoa(email);
						jLabelSaldo.setText(ResourceBundle.getBundle("Etiquetas").getString("Wallet.Balance") + ": " + saldoBerria);
						jLabelError.setText("");

					} else {
						jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.PinError"));
					}

				} catch (NumberFormatException ex) {
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.NumberError"));
				}
			}
		});

		btnAtera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					float kantitatea = Float.parseFloat(textKantitatea.getText());
					int pin = Integer.parseInt(textPin.getText());

					if (bl.egiaztatuPin(email, pin)) {

						bl.diruaAtera(email, kantitatea);

						float saldoBerria = bl.getSaldoa(email);
						jLabelSaldo.setText(ResourceBundle.getBundle("Etiquetas").getString("Wallet.Balance") + ": " + saldoBerria);
						jLabelError.setText("");
					} else {
						jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.PinError"));
					}

				} catch (NumberFormatException ex) {
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.NumberError"));
				} catch (NahikoDirurikEzException ex) {
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("WalletGUI.NoMoney"));
				}
			}
		});

		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}