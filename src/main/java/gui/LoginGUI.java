package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import businessLogic.BLFacade;
import domain.Arduraduna;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailTextField;
	private JPasswordField passwordTextField;
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
		JLabel erroreMezua = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel emailText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.email"));
		emailText.setHorizontalAlignment(SwingConstants.CENTER);
		emailText.setBounds(100, 30, 200, 20);
		contentPane.add(emailText);

		emailTextField = new JTextField();
		emailTextField.setBounds(100, 55, 200, 30);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);

		JLabel password = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.password"));
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setBounds(100, 100, 200, 20);
		contentPane.add(password);

		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(100, 125, 200, 30);
		contentPane.add(passwordTextField);

		JButton loginBotoia = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.Login"));
		loginBotoia.setBounds(125, 180, 150, 40);
		loginBotoia.setEnabled(false);
		contentPane.add(loginBotoia);
		
		JButton atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.setBounds(150, 240, 100, 30);
		contentPane.add(atzeraButton);
		
		erroreMezua.setHorizontalAlignment(SwingConstants.CENTER);
		erroreMezua.setBounds(50, 290, 300, 20);
		contentPane.add(erroreMezua);
		
		DocumentListener fieldListener = new DocumentListener() {
			public void insertUpdate(DocumentEvent e) { checkFields(); }
			public void removeUpdate(DocumentEvent e) { checkFields(); }
			public void changedUpdate(DocumentEvent e) { checkFields(); }

			private void checkFields() {
				boolean hasEmail = !emailTextField.getText().trim().isEmpty();
				boolean hasPassword = passwordTextField.getPassword().length > 0;
				loginBotoia.setEnabled(hasEmail && hasPassword);
			}
		};
		emailTextField.getDocument().addDocumentListener(fieldListener);
		passwordTextField.getDocument().addDocumentListener(fieldListener);
		
		loginBotoia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade facade = MainGUI.getBusinessLogic();
				String passStr = new String(passwordTextField.getPassword());
				Arduraduna a = facade.isLoginArd(emailTextField.getText(), passStr);
				if (a != null) {
					new MainGUIErregistratuaArd(emailTextField.getText()).setVisible(true);
					nirePantaila.setVisible(false);
				} else {
					User u = facade.isLogin(emailTextField.getText(), passStr);
					if (u != null) {
						new MainGUIErregistratua(emailTextField.getText()).setVisible(true);
						nirePantaila.setVisible(false);
					} else {
						erroreMezua.setForeground(Color.red);
						erroreMezua.setText(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI.ErroreMezua"));
					}
				}
			}
		});

		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame main = new MainGUI();
				main.setVisible(true);
				nirePantaila.setVisible(false);
			}
		});
	}
}

