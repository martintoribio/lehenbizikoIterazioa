package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class RegisteredGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel emailText;
	private JLabel pasahitzaText;
	private JLabel pasahitza2Text;
	private JLabel erroreMezua;
	private JTextField email;
	private JTextField pasahitza1;
	private JTextField pasahitza2;
	private JButton erregistratuBotoia;
	private JFrame main_page;
	private JFrame uneko_pantaila;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisteredGUI frame = new RegisteredGUI();
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
	public RegisteredGUI() {
		uneko_pantaila = this;
		main_page = new MainGUIErregistratua("");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		email = new JTextField();
		email.setBounds(168, 12, 130, 20);
		contentPane.add(email);
		email.setColumns(10);

		emailText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.email"));
		emailText.setBounds(28, 14, 130, 14);
		emailText.setFont(emailText.getFont().deriveFont(emailText.getFont().getStyle() | Font.BOLD));
		contentPane.add(emailText);

		pasahitzaText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.password"));
		pasahitzaText.setBounds(28, 49, 130, 14);
		pasahitzaText.setFont(pasahitzaText.getFont().deriveFont(pasahitzaText.getFont().getStyle() | Font.BOLD));
		contentPane.add(pasahitzaText);

		pasahitza2Text = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.passwordrepeat"));
		pasahitza2Text.setBounds(28, 87, 130, 14);
		pasahitza2Text.setFont(pasahitza2Text.getFont().deriveFont(pasahitza2Text.getFont().getStyle() | Font.BOLD));
		contentPane.add(pasahitza2Text);

		pasahitza1 = new JTextField();
		pasahitza1.setBounds(168, 47, 131, 20);
		pasahitza1.setColumns(10);
		contentPane.add(pasahitza1);

		pasahitza2 = new JTextField();
		pasahitza2.setBounds(168, 85, 131, 20);
		pasahitza2.setColumns(10);
		contentPane.add(pasahitza2);

		erregistratuBotoia = new JButton(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.Register"));
		erregistratuBotoia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!pasahitza1.getText().equals(pasahitza2.getText())) {
					erroreMezua.setText("Idatzi dituzun pasahitzak ez dira berdinak.");
					erroreMezua.setForeground(Color.red);
				} else {
					erroreMezua.setText("");
					uneko_pantaila.setVisible(false);
					main_page.setVisible(true);

				}
			}
		});
		erregistratuBotoia.setBounds(91, 189, 126, 27);
		contentPane.add(erregistratuBotoia);

		erroreMezua = new JLabel("");
		erroreMezua.setBounds(91, 145, 266, 14);
		contentPane.add(erroreMezua);

		JButton atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("RegisteredGUI.Back"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame main = new MainGUI();
				main.setVisible(true);
				uneko_pantaila.setVisible(false);
			}
		});
		atzeraButton.setBounds(242, 189, 115, 27);
		contentPane.add(atzeraButton);

	}
}
