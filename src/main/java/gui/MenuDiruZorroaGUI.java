package gui;
import javax.swing.*;
import java.awt.event.*;
import java.util.ResourceBundle;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;

public class MenuDiruZorroaGUI extends JFrame {
    private JPanel jContentPane;
    
    public MenuDiruZorroaGUI(JFrame pantailaNagusia, String sellerMail) {
        setSize(500, 300);
        setTitle("Diru-zorroa - " + sellerMail);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton btnViewMovements = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryMovements"));
        btnViewMovements.addActionListener(e -> {
            new QueryMugimenduakGUI(this, sellerMail, this).setVisible(true);
            this.setVisible(false);
        });

        JButton btnDiruaKudeatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.Wallet"));
        btnDiruaKudeatu.addActionListener(e -> {
            new WalletGUI(sellerMail, this).setVisible(true);
            this.setVisible(false);
        });

        JButton btnAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
        btnAtzera.addActionListener(e -> {
            pantailaNagusia.setVisible(true);
            dispose();
        });

        jContentPane = new JPanel();
        jContentPane.setLayout(new BorderLayout(10, 15));
        jContentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        JPanel panelZentroa = new JPanel(new GridLayout(1,2,15,0));
        panelZentroa.add(btnViewMovements);
        panelZentroa.add(btnDiruaKudeatu);
        jContentPane.add(panelZentroa, BorderLayout.CENTER);
        
        JPanel panelItxi = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelItxi.add(btnAtzera);
        jContentPane.add(panelItxi, BorderLayout.SOUTH);
        
        setContentPane(jContentPane);
    }
}