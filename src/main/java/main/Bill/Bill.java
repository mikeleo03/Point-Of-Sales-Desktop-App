package main.Bill;

import main.Transaksi.DetailTransaksi;

/**
 * File : Bill.java
 * Class untuk menyimpan data fixed bill
 */

public class Bill extends Recap {
    public Bill(Integer idCustomer, Integer waktu, Integer tanggal, DetailTransaksi detailTransaksi, Double nominal) {
        super(idCustomer, waktu, tanggal, detailTransaksi, nominal);
    }

    public void addItem() {
        
    }
}
