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
import main.Client.ClientManager;

public class KasirPage extends JPanel {
    private JTabbedPane tabbedPane;
    private Map<JPanel, String> panelMap;
    private Map<String, Map<String, Integer>> stateMap;
    private Map<String, Bill> billMap;
    private CustomerTuple customerTuple;
    private BillManager billManager;
    private FixedBillManager fixedBillManager;
    private ClientManager clientManager;
    private Inventory inventory;

    public KasirPage(BillManager billManager, FixedBillManager fixedBillManager, ClientManager clientManager, Inventory inventory) {
        this.panelMap = new HashMap<>();
        this.stateMap = new HashMap<>();
        this.billMap = new HashMap<>();
        this.customerTuple = new CustomerTuple("", -1);
        this.billManager = billManager;
        this.fixedBillManager = fixedBillManager;
        this.clientManager = clientManager;
        this.inventory = inventory;
        initUI();
    }

    private void initUI() {
        // Mengatur layout dari panel
        setLayout(new BorderLayout());

        // Membuat tabbedPane
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
        
        // Membuat tombol untuk menambah tab baru
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Tambah");
        if (!this.billManager.isEmpty()) {
            for (Bill bill : billManager.getListBill()) {
                addTab(bill);
            }
        }
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                addTab(null);
            }
        });
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.NORTH);
    }

    private void addTab(Bill inputBill) {
        DetailTransaksi details = new DetailTransaksi();
        Bill bill = new Bill(this.customerTuple.getCustomerId(), details); // Set idCustomer ke 0 karena belum ada input dari user
        if (inputBill != null) {
            bill = inputBill;
        } else {
            // JPanel panel = new JPanel(new GridBagLayout());
            billManager.addBill(bill);
        }
        
        // Membuat panel baru untuk menangani pelanggan baru
        JPanel panel = new PelangganPanel(panelMap.size() + 1, this, bill, this.customerTuple, this.clientManager, this.inventory, this.billManager, this.fixedBillManager, this.tabbedPane);

        JButton closeButton = new JButton("X");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String panelName = panelMap.get(panel);

                stateMap.remove(panelName);
                panelMap.remove(panel);
                billMap.remove(panelName);
                tabbedPane.remove(panel);

                // remove the tab
                tabbedPane.removeTabAt(tabbedPane.indexOfComponent(panel));
            }
        });
        // closeButton.setPreferredSize(new Dimension(30,20));
        JPanel tabPanel = new JPanel(new BorderLayout(3,3));
        JLabel tabTitle = new JLabel("Pelanggan " + (panelMap.size() + 1));
        tabPanel.add(tabTitle, BorderLayout.WEST);
        tabPanel.add(closeButton, BorderLayout.EAST);

        panelMap.put(panel, "Pelanggan " + (panelMap.size() + 1));
        stateMap.put(panelMap.get(panel), new HashMap<>());
        billMap.put(panelMap.get(panel), bill);

        // Menambahkan panel ke dalam tabbedPane
        tabbedPane.addTab(panelMap.get(panel), panel);
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tabPanel);
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
}
