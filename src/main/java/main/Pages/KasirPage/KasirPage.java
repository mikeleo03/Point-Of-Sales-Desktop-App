package main.Pages.KasirPage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.*;
import javax.swing.*;

import main.Transaksi.DetailTransaksi;
import main.Barang.*;
import main.Bill.*;

public class KasirPage extends JFrame {
    private JTabbedPane tabbedPane;
    private Map<JPanel, String> panelMap;
    private Map<String, Map<String, Integer>> stateMap;
    private Map<String, Bill> billMap;
    private CustomerTuple customerTuple;

    public KasirPage() {
        panelMap = new HashMap<>();
        stateMap = new HashMap<>();
        billMap = new HashMap<>();
        customerTuple = new CustomerTuple("", -1);
        initUI();
    }

    private void initUI() {
        setTitle("Halaman Kasir");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        
        // Membuat tabbedPane
        tabbedPane = new JTabbedPane();
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        
        // Membuat tombol untuk menambah tab baru
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Tambah");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                addTab();
            }
        });
        buttonPanel.add(addButton);
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
    }

    private void addTab() {
        DetailTransaksi details = new DetailTransaksi();
        Bill bill = new Bill(this.customerTuple.getCustomerId(), details); // Set idCustomer ke 0 karena belum ada input dari user
        // JPanel panel = new JPanel(new GridBagLayout());
        
        // Membuat panel baru untuk menangani pelanggan baru
        JPanel panel = new PelangganPanel(panelMap.size() + 1, this, bill, this.customerTuple);
        
        panelMap.put(panel, "Pelanggan " + (panelMap.size() + 1));
        stateMap.put(panelMap.get(panel), new HashMap<>());
        billMap.put(panelMap.get(panel), bill);

        // Menambahkan panel ke dalam tabbedPane
        tabbedPane.addTab(panelMap.get(panel), panel);
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
    }

    public Map<String, Map<String, Integer>> getStateMap() {
        return stateMap;
    }

    public Map<JPanel, String> getPanelMap() {
        return panelMap;
    }
    
    public Map<String, Bill> getBillMap() {
        return billMap;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            KasirPage kasirPage = new KasirPage();
            kasirPage.setVisible(true);
        });
    }
}
