package main.Pages.HistoryPage;
import main.Bill.FixedBill;
import main.Bill.FixedBillManager;
import main.Laporan.Laporan;
import main.Laporan.LaporanPenjualan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import main.Barang.*;
import main.Transaksi.*;

public class HistoryPage extends JPanel implements ActionListener {
    private JTable table;
    private JButton print;
    private DefaultTableModel tableModel;
    private List<FixedBill> fixedBills;

    public HistoryPage(FixedBillManager fixedBillManager) {    
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
        this.fixedBills = fixedBillManager.getListFixedBill(); // assume this method returns a list of FixedBill objects
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

        this.print = new JButton("Print Laporan Penjualan");
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 0, 0);
        mainPanel.add(this.print, gbc);
        this.print.addActionListener(this);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FixedBillManager fxm = new FixedBillManager();
            HistoryPage historyPage = new HistoryPage(fxm);
            historyPage.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == print) {
            Thread pdfThread = new Thread(() -> {
                try {
                    Laporan laporan = new LaporanPenjualan(this.fixedBills, "Laporan_Penjualan");
                    laporan.generatePDF();
                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                }
            });
            pdfThread.start();
            Thread popupThread = new Thread(() -> {
                try {
                    Thread.sleep(10000);
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(null, "PDF berhasil di-download!");
                    });
                } catch (InterruptedException exception) {
                    System.out.println(exception.getMessage());
                }
            });
            popupThread.start();
        }
    }
}
