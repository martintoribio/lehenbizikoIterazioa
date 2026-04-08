package gui;
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

public class MainGUIErregistratuaArd extends JFrame {
	
	 private String adminMail;
		private static final long serialVersionUID = 1L;

		private JPanel jContentPane = null;

	    private static BLFacade appFacadeInterface;
		
		public static BLFacade getBusinessLogic(){
			return appFacadeInterface;
		}
		public static void setBussinessLogic (BLFacade facade){
			appFacadeInterface=facade;
		}
		private JFrame nirePantaila;
	public MainGUIErregistratuaArd(String mail) {
		super();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		nirePantaila=this;
		this.adminMail=mail;
		this.setSize(495, 290);
		
		getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		
		JButton salaketaBut = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratuaArd.Complaint"));
		salaketaBut.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFrame a = new QueryAztertzekoSalaketakGUI(nirePantaila, mail,nirePantaila);
		        a.setVisible(true);
		    }
		});
		getContentPane().add(salaketaBut);
		
	
		JButton errekBut = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratuaArd.Claim"));
		errekBut.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFrame a = new QueryAztertzekoErreklamazioakGUI(nirePantaila, mail,nirePantaila);
		        a.setVisible(true);
		    }
		});
		getContentPane().add(errekBut);
		
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
	}

}
