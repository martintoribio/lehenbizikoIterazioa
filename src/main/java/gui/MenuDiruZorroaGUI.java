package gui;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ResourceBundle;

public class MenuDiruZorroaGUI extends JFrame {
    private JPanel jContentPane;
    public MenuDiruZorroaGUI(JFrame pantailaNagusia, String sellerMail) {
        setSize(495, 290);
        setTitle("Diru-zorroa - " + sellerMail);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton btnViewMovements = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.QueryMovements"));
        btnViewMovements.addActionListener(e -> {
            new QueryMugimenduakGUI(this, sellerMail, this).setVisible(true);
            this.setVisible(false);
        });

        JButton btnDiruaKudeatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUIErregistratua.Wallet"));
        btnDiruaKudeatu.addActionListener(e -> {
            new WalletGUI(sellerMail).setVisible(true);
        });

        JButton btnAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
        btnAtzera.addActionListener(e -> {
            pantailaNagusia.setVisible(true);
            dispose();
        });

        jContentPane = new JPanel(new GridLayout(3, 1, 0, 0));
        jContentPane.add(btnViewMovements);
        jContentPane.add(btnDiruaKudeatu);
        jContentPane.add(btnAtzera);
        setContentPane(jContentPane);
    }
}