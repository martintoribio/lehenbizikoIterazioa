package gui;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ResourceBundle;

public class MenuMerkatuaGUI extends JFrame {
    private JPanel jContentPane;
    public MenuMerkatuaGUI(JFrame pantailaNagusia, String sellerMail) {
        setSize(495, 290);
        setTitle("Merkatua - " + sellerMail);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton btnCreateQuery = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.CreateSale"));
        btnCreateQuery.addActionListener(e -> {
            new CreateSaleGUI(sellerMail, this).setVisible(true);
            this.setVisible(false);
        });

        JButton btnQueryQueries = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QuerySales"));
        btnQueryQueries.addActionListener(e -> {
            new QuerySalesGUI(this, sellerMail).setVisible(true);
            this.setVisible(false);
        });
        
        JButton btnEskaeraSortu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.EskaeraSortu"));
        btnEskaeraSortu.addActionListener(e -> {
            new EskaeraSortuGUI(this, sellerMail).setVisible(true);
            this.setVisible(false);
        });
        
        JButton btnQueryEskaerak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryEskaerak"));
        btnQueryEskaerak.addActionListener(e -> {
            new QueryEskaerakGUI(this, sellerMail, this).setVisible(true);
            this.setVisible(false);
        });
        
        JButton btnAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
        btnAtzera.addActionListener(e -> {
            pantailaNagusia.setVisible(true);
            dispose();
        });
        

        jContentPane = new JPanel(new GridLayout(5, 1, 0, 0));
        jContentPane.add(btnCreateQuery);
        jContentPane.add(btnQueryQueries);
        jContentPane.add(btnEskaeraSortu);
        jContentPane.add(btnQueryEskaerak);
        jContentPane.add(btnAtzera);
        setContentPane(jContentPane);
    }
}