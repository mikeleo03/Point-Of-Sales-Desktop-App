package main.Bill;

import main.Transaksi.DetailTransaksi;
import main.Barang.Inventory;
import main.Barang.Barang;

/**
 * File : Bill.java
 * Class untuk menyimpan data bill sementara
 */

public class Bill extends Recap {
    public Bill() {

    }
    
    public Bill(Integer idCustomer, String waktu, String tanggal, DetailTransaksi detailTransaksi, Double nominal) {
        super(idCustomer, waktu, tanggal, detailTransaksi, nominal);
    }

    public void addItem(Integer idBarang, Integer jumlahBarang, Inventory inventory) {
        DetailTransaksi dT = this.getDetailTransaksi();
        Barang barang = inventory.getBarangByID(idBarang);
        dT.addBarang(barang, jumlahBarang, inventory);
        this.setDetailTransaksi(dT);
    }

    // public void removeItem(Integer idBarang, Integer jumlahBarang, Inventory inventory) {
    //     detailTransaksi.addBarang(idBarang, jumlahBarang, inventory);
    // }
}
