package main.Pages.HistoryPage;
import main.Bill.FixedBill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import main.Barang.*;
import main.Transaksi.*;

public class HistoryPage extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public HistoryPage() {
        super("History Page");
    
        // create main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        add(mainPanel, BorderLayout.CENTER);
    
        // create label and add to main panel
        JLabel label = new JLabel("Riwayat Transaksi");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // add top padding
        mainPanel.add(label, gbc);
    
        // create table and add to scroll pane
        table = new JTable();
        tableModel = new DefaultTableModel();
        table.setModel(tableModel);
    
        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridy = 1;
        gbc.weightx = 1.0; // set horizontal weight to fill remaining space
        gbc.fill = GridBagConstraints.BOTH; // set both vertical and horizontal fill
        gbc.insets = new Insets(0, 10, 0, 10); // add left and right padding
        mainPanel.add(scrollPane, gbc);
    
        // add columns to table
        tableModel.addColumn("ID Customer");
        tableModel.addColumn("Waktu");
        tableModel.addColumn("Tanggal");
        tableModel.addColumn("idBarang");
        tableModel.addColumn("Nama Barang");
        tableModel.addColumn("Jumlah Barang");
        tableModel.addColumn("Subtotal");
    
        // populate table with data from fixed bills
        ArrayList<FixedBill> fixedBills = getFixedBills(); // assume this method returns a list of FixedBill objects
        for (FixedBill bill : fixedBills) {
            DetailTransaksi details = bill.getDetailTransaksi();
            for (int i = 0; i < details.getElement().size(); i++) {
                Object[] row = new Object[7];
                if (i == 0) { // add customer, waktu, and tanggal to the first row only
                    row[0] = bill.getIdCustomer();
                    row[1] = bill.getWaktu();
                    row[2] = bill.getTanggal();
                } else {
                    row[0] = ""; // leave the first 3 columns blank for subsequent rows
                    row[1] = "";
                    row[2] = "";
                }
                row[3] = details.getElement().get(i).getIdBarang();
                row[4] = details.getElement().get(i).getNamaBarang();
                row[5] = details.getElement().get(i).getJumlahBarang();
                row[6] = details.getElement().get(i).getSubTotal();
                tableModel.addRow(row);
            }
        }
        
        // set JFrame properties
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // method to get fixed bills from database or other source
    private ArrayList<FixedBill> getFixedBills() {
        // TODO: implement method to get fixed bills from database or other source
    
        // ArrayList<Barang> listBarang = new ArrayList<>();
        Barang nasgor = new Barang("Nasi Goreng", 10, 11000.00, 13000.00, "Makanan", "../");
        Barang mie = new Barang("Mie Goreng", 10, 9000.00, 7000.00, "Makanan", "../");
        Barang eskrim = new Barang("Ice Cream", 12, 5000.00, 7000.00, "Makanan", "../");
        
        Inventory inv = new Inventory();
        inv.addBarang(mie);
        inv.addBarang(nasgor);
        inv.addBarang(eskrim);
        for (Barang barang : inv.getListBarang()) {
            System.out.println(barang.getName() + " " + barang.getID());
        }

        DetailTransaksi detail = new DetailTransaksi();
        detail.editBarang(nasgor, 3, inv);
        detail.editBarang(mie, 5, inv);
        detail.editBarang(nasgor, 5, inv);
        detail.editBarang(mie, 5, inv);
        detail.deleteDetail(eskrim);

        // FixedBill fixedbill = new FixedBill(2000, "12:08:23", "22/03/2022", detail);
        // System.out.println(fixedbill);

        // create an ArrayList of FixedBill
        ArrayList<FixedBill> fixedBills = new ArrayList<>();

        // create a FixedBill object and add it to the ArrayList
        // FixedBill fixedbill = new FixedBill(2000, "12:08:23", "22/03/2022", detail);
        // fixedBills.add(fixedbill);

        // return new ArrayList<FixedBill>();
        return fixedBills;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HistoryPage historyPage = new HistoryPage();
            historyPage.setVisible(true);
        });
    }
}
