package main.Bill;

import main.Transaksi.DetailTransaksi;

/**
 * File : FixedBill.java
 * Class untuk menyimpan data fixed bill
 */

public class FixedBill extends Recap {
    public FixedBill(Integer idCustomer, Integer waktu, Integer tanggal, DetailTransaksi detailTransaksi, Float nominal) {
        super(idCustomer, waktu, tanggal, detailTransaksi, nominal);
    }

    public void save() {
        
    }
}
