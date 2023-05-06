package main.Bill;

import main.Transaksi.DetailTransaksi;

/**
 * File : Recap.java
 * Class abstrak untuk menyimpan data bill
 */

public abstract class Recap {
    private Integer idCustomer;
    private String waktu;
    private String tanggal;
    private DetailTransaksi detailTransaksi;
    private Double nominal;

    public Recap() {
        
    }

    public Recap(Integer idCustomer, String waktu, String tanggal, DetailTransaksi detailTransaksi, Double nominal) {
        this.idCustomer = idCustomer;
        this.waktu = waktu;
        this.tanggal = tanggal;
        this.detailTransaksi = detailTransaksi;
        this.nominal = nominal;
    }

    public Integer getIdCustomer() {
        return this.idCustomer;
    }

    public String getWaktu() {
        return this.waktu;
    }

    public String getTanggal() {
        return this.tanggal;
    }

    public DetailTransaksi getDetailTransaksi() {
        return this.detailTransaksi;
    }

    public Double getNominal() {
        return this.nominal;
    }

    public void setDetailTransaksi(DetailTransaksi detailTransaksi) {
        this.detailTransaksi = detailTransaksi;
    }
}
