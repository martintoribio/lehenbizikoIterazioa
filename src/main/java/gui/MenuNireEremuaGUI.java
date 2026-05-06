package gui;
import javax.swing.*;
import java.awt.event.*;
import java.util.ResourceBundle;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;

public class MenuNireEremuaGUI extends JFrame {
    private JPanel jContentPane;
    
    public MenuNireEremuaGUI(JFrame pantailaNagusia, String sellerMail) {
        setSize(500, 300);
        setTitle("Nire Eremua - " + sellerMail);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton btnViewFavorites = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryFavorites"));
        btnViewFavorites.addActionListener(e -> {
            new QueryFavoritesGUI(this, sellerMail, this).setVisible(true);
            this.setVisible(false);
        });

        JButton btnViewBought = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryBought"));
        btnViewBought.addActionListener(e -> {
            new QueryBoughtGUI(this, sellerMail, this).setVisible(true);
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
        panelZentroa.add(btnViewFavorites);
        panelZentroa.add(btnViewBought);
        jContentPane.add(panelZentroa, BorderLayout.CENTER);
        
        JPanel panelItxi = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelItxi.add(btnAtzera);
        jContentPane.add(panelItxi, BorderLayout.SOUTH);
        
        setContentPane(jContentPane);
    }
}