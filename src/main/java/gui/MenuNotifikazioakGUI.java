package gui;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ResourceBundle;

public class MenuNotifikazioakGUI extends JFrame {
    private JPanel jContentPane;
    public MenuNotifikazioakGUI(JFrame pantailaNagusia, String sellerMail) {
        setSize(495, 290);
        setTitle("Notifikazioak - " + sellerMail);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton viewSalaketak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryReports"));
        viewSalaketak.addActionListener(e -> {
            new QuerySalaketakGUI(this, sellerMail, this).setVisible(true);
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

        jContentPane = new JPanel(new GridLayout(4, 1, 0, 0)); 
        jContentPane.add(viewSalaketak);
        jContentPane.add(viewErreklamazioak);
        jContentPane.add(jButtonViewNotifikazioak);
        jContentPane.add(btnAtzera);
        setContentPane(jContentPane);
    }
}