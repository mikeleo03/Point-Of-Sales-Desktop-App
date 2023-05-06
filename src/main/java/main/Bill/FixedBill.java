package main.Bill;

import main.Transaksi.DetailTransaksi;

/**
 * File : FixedBill.java
 * Class untuk menyimpan data fixed bill
 */

public class FixedBill extends Recap {
    public FixedBill(Integer idCustomer, String waktu, String tanggal, DetailTransaksi detailTransaksi) {
        super(idCustomer, waktu, tanggal, detailTransaksi);
    }

    public void save() {
        
    }
}
