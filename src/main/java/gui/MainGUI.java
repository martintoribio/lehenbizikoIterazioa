package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainGUI extends JFrame {
	
    private String sellerMail;
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonLogin = null;
	private JButton jButtonRegister = null;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade facade){
		appFacadeInterface=facade;
	}
	protected JLabel jLabelTitle;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JFrame nirePantaila;
	/**
	 * This is the default constructor
	 */
	public MainGUI() {
		super();
		nirePantaila=this;
		
		this.setSize(500, 300);
		
		jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SelectOption"));
		jLabelTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		jLabelTitle.setForeground(Color.BLACK);
		jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelTitle.setBorder(new EmptyBorder(10, 0, 10, 0));
		
		rdbtnNewRadioButton = new JRadioButton("English");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en"));
				paintAgain();				}
		});
		buttonGroup.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("eus"));
				paintAgain();				}
		});
		buttonGroup.add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				paintAgain();
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_2);
	
		panel = new JPanel();
		panel.add(rdbtnNewRadioButton_1);
		panel.add(rdbtnNewRadioButton_2);
		panel.add(rdbtnNewRadioButton);
		
		jButtonLogin = new JButton();
		jButtonLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Login"));
		jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new LoginGUI();
				a.setVisible(true);
				nirePantaila.setVisible(false);
			}
		});
		
		jButtonRegister = new JButton();
		jButtonRegister.setText("<html><center>" + ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Register") + "</center></html>");
		jButtonRegister.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new RegisterGUI();

				a.setVisible(true);
				nirePantaila.setVisible(false);
			}
		});
		
		JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
		centerPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		centerPanel.add(jButtonLogin);
		centerPanel.add(jButtonRegister);
		
		jContentPane = new JPanel();
		jContentPane.setLayout(new BorderLayout(10, 10));
		jContentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		
		jContentPane.add(jLabelTitle, BorderLayout.NORTH);
		jContentPane.add(centerPanel, BorderLayout.CENTER);
		jContentPane.add(panel, BorderLayout.SOUTH);
		
		setContentPane(jContentPane);
		
		if (sellerMail != null) {
			setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Title") + ": " + sellerMail);
		} else {
			setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Title"));
		}
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
	}
	
	private void paintAgain() {
		jLabelTitle.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SelectOption"));
		jButtonRegister.setText("<html><center>" + ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Register") + "</center></html>");
		jButtonLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Login"));
		if (sellerMail != null) {
			this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Title") + ": " + sellerMail);
		} else {
			this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Title"));
		}
	}
	
} // @jve:decl-index=0:visual-constraint="0,0"

