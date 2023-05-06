package main.Bill;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

import main.Transaksi.DetailTransaksi;

/**
 * File : Recap.java
 * Class abstrak untuk menyimpan data bill
 */

@XmlRootElement
public abstract class Recap implements Serializable {
    private Integer idCustomer;
    private String waktu;
    private String tanggal;
    private DetailTransaksi detailTransaksi;
    private Double nominal;

    public Recap() {
        
    }

    public Recap(Integer idCustomer, String waktu, String tanggal, DetailTransaksi detailTransaksi) {
        this.idCustomer = idCustomer;
        this.waktu = waktu;
        this.tanggal = tanggal;
        this.detailTransaksi = detailTransaksi;
        this.nominal = 0.00;
        for (int i = 0; i < detailTransaksi.getElement().size(); i++) {
            this.nominal += detailTransaksi.getElement().get(i).getSubTotal();
        }
    }

    @XmlAttribute
    public Integer getIdCustomer() {
        return this.idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    @XmlElement
    public String getWaktu() {
        return this.waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    @XmlElement
    public String getTanggal() {
        return this.tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    @XmlElement
    public DetailTransaksi getDetailTransaksi() {
        return this.detailTransaksi;
    }

    public void setDetailTransaksi(DetailTransaksi detailTransaksi) {
        this.detailTransaksi = detailTransaksi;
    }

    @XmlElement
    public Double getNominal() {
        return this.nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }
}
