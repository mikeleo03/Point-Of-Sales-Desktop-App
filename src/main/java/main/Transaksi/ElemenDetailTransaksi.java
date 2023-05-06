/*
 * File : ElemenDetailTransaksi.java
 * Class untuk menambahkan elemen detail transaksi detail transaksi
 */

 package main.Transaksi;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class ElemenDetailTransaksi implements Serializable {
    // ATTRIBUTES
    private Integer idBarang;
    private Integer jumlahBarang;
    private Double subTotal;
    private String namaBarang;

    // CONSTRUCTOR
    public ElemenDetailTransaksi(Integer idBarang, String namaBarang, Integer jumlahBarang, Double subTotal) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.jumlahBarang = jumlahBarang;
        this.subTotal = subTotal;
    }

    // METHODS
    @XmlAttribute
    public Integer getIdBarang() {
        return this.idBarang;
    }

    public void setIdBarang(Integer idBarang) {
        this.idBarang = idBarang;
    }

    @XmlElement
    public Integer getJumlahBarang() {
        return this.jumlahBarang;
    }

    public void setJumlahBarang(Integer jumlahBarang) {
        this.jumlahBarang = jumlahBarang;
    }

    @XmlElement
    public Double getSubTotal() {
        return this.subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    @XmlElement
    public String getNamaBarang() {
        return this.namaBarang;
    }

    public void setNamaBarang(String nama) {
        this.namaBarang = nama;
    }
}
