package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.Sale;

import java.util.ResourceBundle;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

public class BuyGUI extends JFrame {
	private JTextField CName;
	private JTextField CNumber;
	private JTextField CPin;
	private static final int baseSize = 160;
	public BuyGUI(Sale s,JFrame aurrekoa) {
		this.setSize(495,290);
		getContentPane().setLayout(null);
		
		JLabel cardName = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BuyGUI.cardName"));
		cardName.setBounds(25, 40, 100, 24);
		getContentPane().add(cardName);
		
		CName = new JTextField();
		CName.setBounds(189, 42, 127, 20);
		getContentPane().add(CName);
		CName.setColumns(10);
		
		JLabel cardNumber = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BuyGUI.cardNumber"));
		cardNumber.setBounds(25, 91, 100, 24);
		getContentPane().add(cardNumber);
		
		CNumber = new JTextField();
		CNumber.setBounds(189, 93, 127, 20);
		getContentPane().add(CNumber);
		CNumber.setColumns(10);
		
		JLabel CardPin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BuyGUI.cardPin"));
		CardPin.setBounds(25, 144, 100, 24);
		getContentPane().add(CardPin);
		
		CPin = new JTextField();
		CPin.setBounds(189, 146, 127, 20);
		getContentPane().add(CPin);
		CPin.setColumns(10);
		
		JButton BuyButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BuyGUI.buy"));
		BuyButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 BLFacade b = MainGUI.getBusinessLogic();
				 b.buy(s);
				 aurrekoa.setVisible(true);
				 dispose();//this.setVisible(false)
				 
			}
		});
		BuyButton.setBounds(155, 197, 127, 38);
		getContentPane().add(BuyButton);
		
		
		
	}
	public BufferedImage rescale(BufferedImage originalImage)
    {
        BufferedImage resizedImage = new BufferedImage(baseSize, baseSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, baseSize, baseSize, null);
        g.dispose();
        return resizedImage;
    }
}
