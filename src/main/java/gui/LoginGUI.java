package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailTextField;
	private JTextField passwordTextField;
	private JFrame nirePantaila;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		nirePantaila = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel emailText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.email"));
		emailText.setBounds(30, 30, 141, 14);
		contentPane.add(emailText);

		JLabel password = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.password"));
		password.setBounds(30, 75, 141, 14);
		contentPane.add(password);

		emailTextField = new JTextField();
		emailTextField.setBounds(181, 28, 123, 20);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);

		passwordTextField = new JTextField();
		passwordTextField.setBounds(181, 73, 123, 20);
		contentPane.add(passwordTextField);
		passwordTextField.setColumns(10);

		JButton loginBotoia = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.Login"));
		loginBotoia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade facade = MainGUI.getBusinessLogic();
				boolean b = facade.isLogin(emailTextField.getText(), passwordTextField.getText());
				if (b) {
					new MainGUIErregistratua(null).setVisible(true);
				}
			}
		});
		loginBotoia.setBounds(91, 189, 126, 27);
		contentPane.add(loginBotoia);

		JButton atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.Back"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame main = new MainGUI();
				main.setVisible(true);
				nirePantaila.setVisible(false);
			}
		});
		atzeraButton.setBounds(242, 189, 115, 27);
		contentPane.add(atzeraButton);

	}
}
