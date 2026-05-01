package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;

public class KategoriakGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String email;

	/**
	 * Create the frame.
	 */
	public KategoriakGUI(String email) {
		this.email=email;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ArrayList<String> kategoriak= Utils.getKategoriak();
		
		ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
		
		JCheckBox chckbxNewCheckBox = new JCheckBox(kategoriak.remove(0));
		chckbxNewCheckBox.setBounds(23, 74, 93, 21);
		contentPane.add(chckbxNewCheckBox);
		checkBoxes.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1_1 = new JCheckBox(kategoriak.remove(0));
		chckbxNewCheckBox_1_1.setBounds(154, 74, 93, 21);
		contentPane.add(chckbxNewCheckBox_1_1);
		checkBoxes.add(chckbxNewCheckBox_1_1);

		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox(kategoriak.remove(0));
		chckbxNewCheckBox_1.setBounds(289, 74, 93, 21);
		contentPane.add(chckbxNewCheckBox_1);
		checkBoxes.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox(kategoriak.remove(0));
		chckbxNewCheckBox_2.setBounds(82, 138, 93, 21);
		contentPane.add(chckbxNewCheckBox_2);
		checkBoxes.add(chckbxNewCheckBox_2);
		 
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox(kategoriak.remove(0));
		chckbxNewCheckBox_3.setBounds(233, 138, 93, 21);
		contentPane.add(chckbxNewCheckBox_3);
		checkBoxes.add(chckbxNewCheckBox_3);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("KategoriakGUI.title"));
		lblNewLabel.setBounds(137, 21, 170, 21);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("KategoriakGUI.botoia")); 
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> aukeratuak = new ArrayList<>();
				for (JCheckBox cb : checkBoxes) {
		            if (cb.isSelected()) {
		                String kategoria = cb.getText();
		                aukeratuak.add(kategoria);
		            }	
		        }
				BLFacade b = MainGUI.getBusinessLogic();
	            
				b.kategoriakAldatu(email,aukeratuak);
	
				
	            JFrame next = new MainGUIErregistratua(email);
	            next.setVisible(true);
	            dispose();
			}
		});
		btnNewButton.setBounds(163, 200, 84, 20);
		contentPane.add(btnNewButton);
			
	}
}
