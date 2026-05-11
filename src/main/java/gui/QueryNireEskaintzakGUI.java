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

public class QueryNireEskaintzakGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JLabel jLabelEskaerak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QuerySalesGUI.Products"));
    private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
    private JScrollPane scrollPanelProducts = new JScrollPane();
    private JTable tableProducts = new JTable();
    private DefaultTableModel tableModelProducts;
    private JFrame thisFrame;
    private JFrame jasotakoPantaila;
    private String[] columnNamesProducts = new String[] {
            ResourceBundle.getBundle("Etiquetas").getString("EskaeraSortuGUI.productName"),
            ResourceBundle.getBundle("Etiquetas").getString("Price"),
            ResourceBundle.getBundle("Etiquetas").getString("Status")
    };
    private String loggedEmail;

    public QueryNireEskaintzakGUI(JFrame pantaila, String email) {
        jasotakoPantaila = pantaila;
        this.loggedEmail = email;
        tableProducts.setEnabled(false);
        thisFrame = this;
        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(500, 400));

        jLabelEskaerak.setBounds(50, 20, 400, 20);
        this.getContentPane().add(jLabelEskaerak);

        scrollPanelProducts.setBounds(new Rectangle(50, 60, 380, 200));
        scrollPanelProducts.setViewportView(tableProducts);
        tableModelProducts = new DefaultTableModel(null, columnNamesProducts);
        tableProducts.setModel(tableModelProducts);
        tableModelProducts.setColumnCount(3);
        tableProducts.getColumnModel().getColumn(0).setPreferredWidth(180);
        tableProducts.getColumnModel().getColumn(1).setPreferredWidth(120);
        tableProducts.getColumnModel().getColumn(2).setPreferredWidth(100);
        this.getContentPane().add(scrollPanelProducts, null);

        jButtonClose.setBounds(170, 300, 130, 30);
        jButtonClose.addActionListener(e -> {
            thisFrame.setVisible(false);
            jasotakoPantaila.setVisible(true);
        });
        this.getContentPane().add(jButtonClose, null);


        loadNireEskaintzak();
    }

    private void loadNireEskaintzak() {
        try {
            BLFacade facade = MainGUI.getBusinessLogic();
            List<Eskaintza> eskaintzak = facade.getNireEskaintzak(loggedEmail);
            for (Eskaintza esk : eskaintzak) {
                Vector<Object> row = new Vector<Object>();
                Eskaera eskaera = esk.getEskaera();
                row.add(eskaera.getProduktuIzena());
                row.add(esk.getPrezioa());
                row.add(esk.getEgoera());
                tableModelProducts.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}