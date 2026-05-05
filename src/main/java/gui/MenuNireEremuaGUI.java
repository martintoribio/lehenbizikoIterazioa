package gui;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ResourceBundle;

public class MenuNireEremuaGUI extends JFrame {
    private JPanel jContentPane;
    public MenuNireEremuaGUI(JFrame pantailaNagusia, String sellerMail) {
        setSize(495, 290);
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

        jContentPane = new JPanel(new GridLayout(3, 1, 0, 0));
        jContentPane.add(btnViewFavorites);
        jContentPane.add(btnViewBought);
        jContentPane.add(btnAtzera);
        setContentPane(jContentPane);
    }
}