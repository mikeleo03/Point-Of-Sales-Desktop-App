package main.Laporan;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public abstract class Laporan extends Thread {
    private String fileName;

    public Laporan(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            generatePDF();
            JOptionPane.showMessageDialog(null, "PDF berhasil di-generate!");
        } catch (Exception e) {
            System.err.println("Exception occurred while generating PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public abstract void generatePDF() throws IOException;

}
