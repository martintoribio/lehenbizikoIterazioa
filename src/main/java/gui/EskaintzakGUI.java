package gui;

import businessLogic.BLFacade;
import domain.Eskaera;
import domain.Eskaintza;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class EskaintzakGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private final JLabel jLabelEskaintzak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QueryEskaerak.Products"));
    private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
    private JScrollPane scrollPanelProducts = new JScrollPane();
    private JTable tableProducts = new JTable();
    private DefaultTableModel tableModelProducts;
    private JFrame thisFrame;
    private JFrame jasotakoPantaila;
    private Eskaera eskaera;
    private String[] columnNamesProducts = new String[] {
            ResourceBundle.getBundle("Etiquetas").getString("EskaeraSortuGUI.productName"),
            ResourceBundle.getBundle("Etiquetas").getString("CreateSaleGUI.Category"),
    };

    public EskaintzakGUI(JFrame pantaila, Eskaera eskaera) {
        this.jasotakoPantaila = pantaila;
        this.eskaera = eskaera;
        tableProducts.setEnabled(false);
        thisFrame = this;
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(500, 400));

        jLabelEskaintzak.setBounds(50, 20, 400, 20);
        this.getContentPane().add(jLabelEskaintzak);

        scrollPanelProducts.setBounds(new Rectangle(50, 60, 380, 200));
        scrollPanelProducts.setViewportView(tableProducts);
        tableModelProducts = new DefaultTableModel(null, columnNamesProducts);
        tableProducts.setModel(tableModelProducts);
        this.getContentPane().add(scrollPanelProducts, null);

        jButtonClose.setBounds(170, 300, 130, 30);
        jButtonClose.addActionListener(e -> {
            thisFrame.setVisible(false);
            jasotakoPantaila.setVisible(true);
        });
        this.getContentPane().add(jButtonClose, null);

        loadEskaintzak();
    }

    private void loadEskaintzak() {
        try {
            BLFacade facade = MainGUI.getBusinessLogic();
            List<Eskaintza> eskaintzak = facade.getEskaintzak(eskaera);
            for (Eskaintza e : eskaintzak) {
                Vector<Object> row = new Vector<Object>();
                row.add(e.getErantzunMezua());
                row.add(e.getPrezioa());
                row.add(e.getEgoera());
                tableModelProducts.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}