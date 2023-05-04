package main.Bill;

import main.Transaksi.DetailTransaksi;

/**
 * File : Recap.java
 * Class abstrak untuk menyimpan data bill
 */

public abstract class Recap {
    private Integer idCustomer;
    private Integer waktu;
    private Integer tanggal;
    private DetailTransaksi detailTransaksi;
    private Double nominal;

    public Recap(Integer idCustomer, Integer waktu, Integer tanggal, DetailTransaksi detailTransaksi, Double nominal) {
        this.idCustomer = idCustomer;
        this.waktu = waktu;
        this.tanggal = tanggal;
        this.detailTransaksi = detailTransaksi;
        this.nominal = nominal;
    }

    public Integer getIdCustomer() {
        return this.idCustomer;
    }

    public Integer getWaktu() {
        return this.waktu;
    }

    public Integer getTanggal() {
        return this.tanggal;
    }

    public DetailTransaksi getDetailTransaksi() {
        return this.detailTransaksi;
    }

    public Double getNominal() {
        return this.nominal;
    }
}
