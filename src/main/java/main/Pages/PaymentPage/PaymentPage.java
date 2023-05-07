package main.Pages.PaymentPage;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Bill.*;
import main.Barang.*;
import main.Transaksi.DetailTransaksi;
import main.Transaksi.ElemenDetailTransaksi;  

public class PaymentPage extends JPanel implements ActionListener {
    private JButton cancelButton;
    private JButton saveButton;
    private JButton process;
    private JLabel pengantar;
    private JLabel total;
    final int WIDTH = 700, HEIGHT = 400;

    private BillManager billmanager;
    private Inventory inventory;
    private FixedBillManager fixedbillmanager;

    public PaymentPage (BillManager billmanager, Inventory inventory, FixedBillManager fixedbillmanager) {
        // Pass the bill object to the attributes
        this.billmanager = billmanager;
        this.inventory = inventory;
        this.fixedbillmanager = fixedbillmanager;
        
        // Create the table with some sample data
        List<Bill> listbill = this.billmanager.getListBill();
        ArrayList<Object[]> data = new ArrayList<>();
        for ()
        DetailTransaksi details = bill.getDetailTransaksi();
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
        cancelButton = new JButton("Cancel");
        saveButton = new JButton("Save");
        buttonPanel.add(this.cancelButton);
        buttonPanel.add(this.saveButton);
        cancelButton.addActionListener(this);
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
        this.total = new JLabel("Total :" + bill.getNominal().toString());
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

    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == this.cancelButton) {
            // Code to perform when button 1 is clicked
            System.out.println("Button 1 clicked!");
        } else if (e.getSource() == this.saveButton) {
            System.out.println("Button 2 clicked!");
        } else if (e.getSource() == this.process) {
            // Membuat fixed bill
            FixedBill fixed = new FixedBill(this.bill.getIdCustomer(), this.bill.getDetailTransaksi());
            this.fixedbillmanager.addFixedBill(fixed);
            // Mengurangi nilai barang dari inventory
            for (ElemenDetailTransaksi elemen : this.bill.getDetailTransaksi().getElement()) {
                this.inventory.changeStock(elemen.getIdBarang(), -1 * elemen.getJumlahBarang());
            }
        }
    }
}