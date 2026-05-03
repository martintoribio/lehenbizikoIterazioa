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


public class MainGUIErregistratua extends JFrame {
	
    private String sellerMail;
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;
	private JButton jButtonLogout = null;	
	private JButton btnDiruaKudeatu = null;

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
	
	private JButton jButtonViewFavorites = null;
	private JButton jButtonViewBought = null;
	private JButton jButtonViewMovements = null;
	private JButton jButtonViewReports = null;
	private JButton viewErreklamazioak = null;
	private JButton jButtonViewNotifikazioak = null;
	
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
		
		jButtonCreateQuery = new JButton();
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.CreateSale"));
		jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new CreateSaleGUI(sellerMail,nirePantaila);
				a.setVisible(true);
			}
		});
		
		jButtonQueryQueries = new JButton();
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QuerySales"));
		jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new QuerySalesGUI(nirePantaila, sellerMail,MainGUIErregistratua.this);

				a.setVisible(true);
			}
		});
		
		jButtonViewFavorites = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryFavorites"));
		jButtonViewFavorites.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFrame a = new QueryFavoritesGUI(nirePantaila, sellerMail,MainGUIErregistratua.this);
		        a.setVisible(true);
		    }
		});
		
		jButtonViewBought = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryBought"));
		jButtonViewBought.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFrame a = new QueryBoughtGUI(nirePantaila, sellerMail,MainGUIErregistratua.this);
		        a.setVisible(true);
		    }
		});
		
		jButtonViewMovements = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryMovements"));
		jButtonViewMovements.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFrame a = new QueryMugimenduakGUI(nirePantaila, sellerMail,MainGUIErregistratua.this);
		        a.setVisible(true);
		    }
		});
		btnDiruaKudeatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.Wallet"));
		btnDiruaKudeatu.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFrame a = new WalletGUI(sellerMail);
		        a.setVisible(true);
		    }
		});
		jButtonViewReports = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryReports"));
		jButtonViewReports.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFrame a = new QuerySalaketakGUI(nirePantaila, sellerMail,MainGUIErregistratua.this);
		        a.setVisible(true);
		    }
		});
		
		viewErreklamazioak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryErreklamazioak")); 
		viewErreklamazioak.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFrame a = new QueryErreklamazioakGUI(nirePantaila, sellerMail,MainGUIErregistratua.this);
		        a.setVisible(true);
		    }
		});
		
		jButtonViewNotifikazioak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryNotifikazioak")); 
		jButtonViewNotifikazioak.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFrame a = new QueryNotifikazioakGUI(sellerMail, nirePantaila);
		        a.setVisible(true);
		    }
		});
		
		jButtonLogout = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.Logout"));
		jButtonLogout.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFrame a = new MainGUI();
		        a.setVisible(true);
		        nirePantaila.setVisible(false);
		    }
		});
	
		
		jContentPane = new JPanel();
		jContentPane.setLayout(new GridLayout(12, 1, 0, 0));
		jContentPane.add(jLabelSelectOption);
		jContentPane.add(jButtonCreateQuery);
		jContentPane.add(jButtonQueryQueries);
		jContentPane.add(jButtonViewFavorites);
		jContentPane.add(jButtonViewBought);
		jContentPane.add(jButtonViewMovements);
		jContentPane.add(btnDiruaKudeatu);
		jContentPane.add(jButtonViewReports);
		jContentPane.add(viewErreklamazioak);
		jContentPane.add(jButtonViewNotifikazioak);
		jContentPane.add(jButtonLogout);
		jContentPane.add(panel);
		
		
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
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QuerySales"));
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.CreateSale"));
		jButtonViewFavorites.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryFavorites"));
		jButtonViewBought.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryBought"));
		jButtonViewMovements.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryMovements"));
		btnDiruaKudeatu.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.Wallet"));
		jButtonViewReports.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryReports"));
		viewErreklamazioak.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryErreklamazioak"));
		jButtonViewNotifikazioak.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryNotifikazioak"));
		jButtonLogout.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.Logout"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Title")+ ": "+sellerMail);
	}
	
} // @jve:decl-index=0:visual-constraint="0,0"

