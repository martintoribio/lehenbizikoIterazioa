package gui;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ResourceBundle;
import javax.swing.border.EmptyBorder;

public class MenuNotifikazioakGUI extends JFrame {
    private JPanel jContentPane;
    
    public MenuNotifikazioakGUI(JFrame pantailaNagusia, String sellerMail) {
        setSize(750, 300);
        setTitle("Notifikazioak - " + sellerMail);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton viewSalaketak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryReports"));
        viewSalaketak.addActionListener(e -> {
            new QuerySalaketakGUI(this, sellerMail).setVisible(true);
            this.setVisible(false);
        });
        
        JButton viewErreklamazioak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryErreklamazioak")); 
        viewErreklamazioak.addActionListener(e -> {
            new QueryErreklamazioakGUI(this, sellerMail, this).setVisible(true);
            this.setVisible(false);
        });
        
        JButton jButtonViewNotifikazioak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryNotifikazioak")); 
        jButtonViewNotifikazioak.addActionListener(e -> {
            new QueryNotifikazioakGUI(sellerMail, this).setVisible(true);
            this.setVisible(false);
        });

        JButton btnAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close")); 
        btnAtzera.addActionListener(e -> {
            pantailaNagusia.setVisible(true);
            dispose();
        });

        jContentPane = new JPanel(new BorderLayout(10, 15));
        jContentPane.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel panelZentroa = new JPanel(new GridLayout(1, 3, 15, 0));
        panelZentroa.add(viewSalaketak);
        panelZentroa.add(viewErreklamazioak);
        panelZentroa.add(jButtonViewNotifikazioak);
        
        jContentPane.add(panelZentroa, BorderLayout.CENTER);

        JPanel panelItxi = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelItxi.add(btnAtzera);
        
        jContentPane.add(panelItxi, BorderLayout.SOUTH);
        setContentPane(jContentPane);
    }
}