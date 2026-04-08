package gui;

import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.awt.image.BufferedImage;

import businessLogic.BLFacade;
import domain.Salaketa;
import domain.Sale;
import exceptions.NahikoDirurikEzException;


public class SalaketaIkusiGUI extends JFrame{
	
    File targetFile;
    BufferedImage targetImg;
    public JPanel panel_1;
    private static final int baseSize = 160;
	private static final String basePath="src/main/resources/images/";
	
	private static final long serialVersionUID = 1L;

	private JTextField fieldTitle=new JTextField();
	private JTextField fieldDescription=new JTextField();
	private JTextArea deskribapenaTextArea;

	private JLabel jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ShowSaleGUI.Title"));
	private JLabel jLabelDescription = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Description")); 
	private JLabel jLabelPrice = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Price"));
	private JTextField fieldPrice = new JTextField();
	private File selectedFile;
    private String irudia;

	private JScrollPane scrollPaneEvents = new JScrollPane();
	DefaultComboBoxModel<String> statusOptions = new DefaultComboBoxModel<String>();
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JButton jButtonOnartu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SalaketaIkusiGUI.Accept"));
	private JButton jButtonEzeztatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SalaketaIkusiGUI.Reject"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	private JFrame thisFrame;

	
	public SalaketaIkusiGUI(Salaketa salaketa, JFrame aurrekoPantaila) { 
		thisFrame=this; 
		this.setVisible(true);
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 520));
		Sale sale = salaketa.getSale();
		Integer idSalaketa = salaketa.getIdSalaketa();
		BLFacade facade = MainGUI.getBusinessLogic();
		//this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateProductGUI.CreateProduct"));
		fieldTitle.setText(sale.getTitle());
		fieldDescription.setText(sale.getDescription());

		fieldPrice.setText(Float.toString(sale.getPrice()));		
		
		jLabelTitle.setBounds(new Rectangle(20, 20, 92, 20));
		
		jLabelPrice.setBounds(new Rectangle(20, 60, 92, 20));
		fieldPrice.setEditable(false);
		fieldPrice.setBounds(new Rectangle(120, 60, 100, 20));

		jLabelMsg.setBounds(new Rectangle(16, 340, 500, 20));

		jLabelError.setBounds(new Rectangle(16, 340, 500, 20));
		jLabelError.setForeground(Color.red);
		
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		jButtonClose.setBounds(new Rectangle(300, 300, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisFrame.setVisible(false);			}
		});
		
		
		jButtonOnartu.setBounds(new Rectangle(20, 300, 130, 30));
		jButtonOnartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b = facade.salaketaOnartu(idSalaketa);
				if (b) {
					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("SalaketaIkusiGUI.accepted"));
					jLabelError.setText("");
				} else {
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("SalaketaIkusiGUI.alreadyDeleted"));
					jLabelMsg.setText("");
				}
			}
		});
		this.getContentPane().add(jButtonOnartu);
		
		jButtonEzeztatu.setBounds(new Rectangle(160, 300, 130, 30));
		jButtonEzeztatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b = facade.salaketaEzeztatu(idSalaketa);
				if (b) {
					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("SalaketaIkusiGUI.rejected"));
					jLabelError.setText("");
				} else {
					jLabelError.setText("error");
					jLabelMsg.setText("");
					
				}
			}
		});
		this.getContentPane().add(jButtonEzeztatu);
		
		
		
		

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonClose, null);
		
		this.getContentPane().add(jLabelTitle, null);
		
		
		this.getContentPane().add(jLabelPrice, null);
		this.getContentPane().add(fieldPrice, null);

		jLabelDescription.setBounds(20, 100, 109, 16);
		getContentPane().add(jLabelDescription);
		fieldTitle.setEditable(false);
		
		
		fieldTitle.setBounds(120, 20, 260, 26);
		getContentPane().add(fieldTitle);
		fieldTitle.setColumns(10);
		fieldDescription.setEditable(false);
		
		
		fieldDescription.setBounds(120, 100, 260, 60);
		getContentPane().add(fieldDescription);
		fieldDescription.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setBounds(400, 20, 150, 150);
		getContentPane().add(panel_1);
		
		deskribapenaTextArea = new JTextArea();
		deskribapenaTextArea.setEditable(false);
		deskribapenaTextArea.setLineWrap(true);
		deskribapenaTextArea.setText(salaketa.getDeskribapena());
		deskribapenaTextArea.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(deskribapenaTextArea);
		scrollPane.setBounds(20, 180, 550, 100);
		getContentPane().add(scrollPane);
		
		
		
		String file=sale.getFile();
		if (file!=null) {
			Image img=facade.downloadImage(file);
			targetImg = rescale((BufferedImage)img);
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(new JLabel(new ImageIcon(targetImg))); 
		}
		System.out.println("status: "+sale.getStatus());
		
		
		
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

