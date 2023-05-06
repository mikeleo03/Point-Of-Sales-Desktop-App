package main.Bill;

import main.Transaksi.DetailTransaksi;
import main.Barang.Inventory;

/**
 * File : Bill.java
 * Class untuk menyimpan data bill sementara
 */

public class Bill extends Recap {
    public Bill(Integer idCustomer, Integer waktu, Integer tanggal, DetailTransaksi detailTransaksi, Double nominal) {
        super(idCustomer, waktu, tanggal, detailTransaksi, nominal);
    }

    public void addItem(Integer idBarang, Integer jumlahBarang, Inventory inventory) {
        DetailTransaksi dT = this.getDetailTransaksi();
        dT.addBarang(idBarang, jumlahBarang, inventory);
        this.setDetailTransaksi(dT);
    }

    // public void removeItem(Integer idBarang, Integer jumlahBarang, Inventory inventory) {
    //     detailTransaksi.addBarang(idBarang, jumlahBarang, inventory);
    // }
}
