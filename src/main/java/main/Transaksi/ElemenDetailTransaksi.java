package main.Transaksi;
/*
 * File : ElemenDetailTransaksi.java
 * Class untuk menambahkan elemen detail transaksi detail transaksi
 */

public class ElemenDetailTransaksi {
    // ATTRIBUTES
    private Integer idBarang;
    private Integer jumlahBarang;
    private Double subTotal;

    // CONSTRUCTOR
    public ElemenDetailTransaksi(Integer idBarang, Integer jumlahBarang, Double subTotal) {
        this.idBarang = idBarang;
        this.jumlahBarang = jumlahBarang;
        this.subTotal = subTotal;
    }

    // METHODS
    public Integer getIdBarang() {
        return this.idBarang;
    }

    public Integer getJumlahBarang() {
        return this.jumlahBarang;
    }

    public Double getSubTotal() {
        return this.subTotal;
    }

    public void setIdBarang(Integer idBarang) {
        this.idBarang = idBarang;
    }

    public void setJumlahBarang(Integer jumlahBarang) {
        this.jumlahBarang = jumlahBarang;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
}
