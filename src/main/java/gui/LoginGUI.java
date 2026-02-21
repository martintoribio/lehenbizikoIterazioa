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
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailTextField;
	private JTextField passwordTextField;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel email = new JLabel("Email");
		email.setBounds(56, 30, 46, 14);
		contentPane.add(email);
		
		JLabel password = new JLabel("Password");
		password.setBounds(56, 75, 46, 14);
		contentPane.add(password);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(122, 27, 86, 20);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);
		
		passwordTextField = new JTextField();
		passwordTextField.setBounds(122, 72, 86, 20);
		contentPane.add(passwordTextField);
		passwordTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Login egin");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade facade = MainGUI.getBusinessLogic();
				boolean b =facade.isLogin(emailTextField.getText(), passwordTextField.getText());
				if (b) {
					new MainGUIErregistratua(null).setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(163, 192, 120, 23);
		contentPane.add(btnNewButton);

	}
}
