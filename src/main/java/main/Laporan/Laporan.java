package main.Laporan;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        } catch (Exception e) {}
    }

    public String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    protected abstract void generatePDF() throws IOException;


}
