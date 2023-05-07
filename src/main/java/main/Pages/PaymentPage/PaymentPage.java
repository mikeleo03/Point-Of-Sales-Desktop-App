package main.Pages.PaymentPage;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Bill.*;
import main.Barang.*;
import main.Client.*;
import main.Observer.*;
import main.Transaksi.DetailTransaksi;
import main.Transaksi.ElemenDetailTransaksi;  

public class PaymentPage extends JPanel implements ActionListener, Subscriber {
    private JButton saveButton;
    private JButton process;
    private JLabel pengantar;
    private JLabel total;
    final int WIDTH = 700, HEIGHT = 400;

    private BillManager billmanager;
    private ClientManager clientManager;
    private Inventory inventory;
    private FixedBillManager fixedbillmanager;
    private Bill currentbill;

    public PaymentPage (BillManager billmanager, Bill currentbill, Inventory inventory, FixedBillManager fixedbillmanager, ClientManager clientManager) {
        // Pass the bill object to the attributes
        this.billmanager = billmanager;
        this.clientManager = clientManager;
        this.inventory = inventory;
        this.fixedbillmanager = fixedbillmanager;
        this.currentbill = currentbill;
        this.billmanager.observer.subscribe(this);
        this.fixedbillmanager.observer.subscribe(this);
        
        // Create the table with some sample data
        List<Bill> listbill = this.billmanager.getListBill();
        int index = listbill.indexOf(currentbill);
        if (index != -1) {
            if (this.currentbill.getIdCustomer() == null) {
                clientManager.generateCustomer();
                this.currentbill.setIdCustomer(clientManager.getLastClientID());
            }
            this.currentbill.recalculateNominal();
            ArrayList<Object[]> data = new ArrayList<>();

            DetailTransaksi details = this.currentbill.getDetailTransaksi();
            for (int i = 0; i < details.getElement().size(); i++) {
                Object[] row = new Object[4];
                row[0] = details.getElement().get(i).getJumlahBarang();
                row[1] = details.getElement().get(i).getIdBarang();
                row[2] = details.getElement().get(i).getNamaBarang();
                row[3] = details.getElement().get(i).getSubTotal();
                data.add(row);
            }

            JTable table = new JTable(data.toArray(new Object[data.size()][]), new String[] {"Qty", "ID Barang", "Nama Barang", "Sub Total"});

            // Create a panel to hold the buttons
            JPanel buttonPanel = new JPanel();
            saveButton = new JButton("Save");
            buttonPanel.add(this.saveButton);
            saveButton.addActionListener(this);

            // Create a panel to hold the table
            JPanel tablePanel = new JPanel(new BorderLayout());
            tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
            
            // Create a panel to hold both the button panel and the table panel
            JPanel mainPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.EAST;
            mainPanel.add(buttonPanel, gbc);
        
            gbc.gridy++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            this.pengantar = new JLabel("Daftar Barang");
            this.pengantar.setFont(new Font("Arial", ALLBITS, 18));
            mainPanel.add(this.pengantar, gbc);

            gbc.gridy++;
            mainPanel.add(tablePanel, gbc);

            gbc.gridy++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            this.total = new JLabel("Total : " + this.currentbill.getNominal().toString());
            this.total.setFont(new Font("Arial", ALLBITS, 20));
            mainPanel.add(this.total, gbc);

            gbc.gridy++;
            this.process = new JButton("Process");
            process.addActionListener(this);
            mainPanel.add(this.process, gbc);
            
            // Set up the frame
            setSize(WIDTH, HEIGHT);
            setVisible(true);
            
            // Add the main panel to the content pane
            add(mainPanel);
        }
    }

    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == this.saveButton) {
            System.out.println("Button 2 clicked!");
        } else if (e.getSource() == this.process) {
            int index = this.billmanager.getListBill().indexOf(currentbill);
            if (index != -1) {
                // Membuat fixed bill
                FixedBill fixed = new FixedBill(this.currentbill.getIdCustomer(), this.currentbill.getDetailTransaksi());
                this.fixedbillmanager.addFixedBill(fixed);
                // Mengurangi nilai barang dari inventory
                for (ElemenDetailTransaksi elemen : this.currentbill.getDetailTransaksi().getElement()) {
                    this.inventory.changeStock(elemen.getIdBarang(), -1 * elemen.getJumlahBarang());
                }
                // Delete dari bill
                this.billmanager.deleteBill(this.currentbill);
                JOptionPane.showMessageDialog(null, "Transaksi berhasil diproses.");
            } else {
                JOptionPane.showMessageDialog(null, "Transaksi ini sudah pernah diproses sebelumnya.");
            }
        }
    }

    public void update() {
        //
    }
}