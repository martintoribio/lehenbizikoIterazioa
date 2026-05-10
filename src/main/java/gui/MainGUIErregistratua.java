package gui;

/**
 * @author Software Engineering teachers
 */

//Erabiltzaileak saioa hasi ondoren agertuko den pantaila hau izango da
import javax.swing.*;

import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;


public class MainGUIErregistratua extends JFrame {
	
    private String sellerMail;
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonLogout = null;
	private JButton btnMerkatua;
	private JButton btnNireEremua;
	private JButton btnDiruZorroa;
	private JButton btnNotifikazioak;
	

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade facade){
		appFacadeInterface=facade;
	}
	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JFrame nirePantaila;
	
	/**
	 * This is the default constructor
	 */
	public MainGUIErregistratua(String mail) {
		super();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		nirePantaila=this;
		this.sellerMail=mail;
		
		this.setSize(495, 290);
		jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.SelectOption"));
		jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 20));
		jLabelSelectOption.setForeground(Color.BLACK);
		jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		
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
		
		btnMerkatua = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.MenuMerkatua"));
		btnMerkatua.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFrame a = new MenuMerkatuaGUI(nirePantaila, sellerMail);
		        a.setVisible(true);
		        nirePantaila.setVisible(false);
		    }
		});

		btnNireEremua = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.MenuNireEremua"));
		btnNireEremua.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFrame a = new MenuNireEremuaGUI(nirePantaila, sellerMail);
		        a.setVisible(true);
		        nirePantaila.setVisible(false);
		    }
		});

		btnDiruZorroa = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.MenuDiruZorroa"));
		btnDiruZorroa.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFrame a = new MenuDiruZorroaGUI(nirePantaila, sellerMail);
		        a.setVisible(true);
		        nirePantaila.setVisible(false);
		    }
		});

		btnNotifikazioak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.MenuNotifikazioak"));
		btnNotifikazioak.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFrame a = new MenuNotifikazioakGUI(nirePantaila, sellerMail);
		        a.setVisible(true);
		        nirePantaila.setVisible(false);
		    }
		});

		jButtonLogout = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.Logout"));
		jButtonLogout.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFrame a = new MainGUI();
		        a.setVisible(true);
		        nirePantaila.dispose();
		    }
		});

		
		jContentPane = new JPanel();
		jContentPane.setLayout(new BorderLayout(10, 15)); 
		jContentPane.setBorder(new EmptyBorder(10, 15, 10, 15)); 

		JPanel titlePane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titlePane.add(jLabelSelectOption);
		jContentPane.add(titlePane, BorderLayout.NORTH);

		JPanel centralPane = new JPanel(new GridLayout(2, 2, 10, 10)); 
		centralPane.add(btnMerkatua);
		centralPane.add(btnNireEremua);
		centralPane.add(btnDiruZorroa);
		centralPane.add(btnNotifikazioak);
		jContentPane.add(centralPane, BorderLayout.CENTER);

		JPanel bottomPane = new JPanel();
		bottomPane.setLayout(new BoxLayout(bottomPane, BoxLayout.Y_AXIS)); 

		JPanel panelLogout = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelLogout.add(jButtonLogout);

		panel.setLayout(new FlowLayout(FlowLayout.CENTER)); 

		bottomPane.add(panelLogout);
		bottomPane.add(panel);
		jContentPane.add(bottomPane, BorderLayout.SOUTH);
		
		setContentPane(jContentPane);
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.MainTitle") +": "+sellerMail);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
	}
		
	private void paintAgain() {
	    jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.SelectOption"));
	    btnMerkatua.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.MenuMerkatua"));
	    btnNireEremua.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.MenuNireEremua"));
	    btnDiruZorroa.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.MenuDiruZorroa"));
	    btnNotifikazioak.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.MenuNotifikazioak"));
	    jButtonLogout.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.Logout"));
	    this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.MainTitle")+ ": "+sellerMail);
	}
	
} // @jve:decl-index=0:visual-constraint="0,0"

