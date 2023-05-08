package main.Bill;

import main.Transaksi.DetailTransaksi;

/**
 * File : FixedBill.java
 * Class untuk menyimpan data fixed bill
 */

public class FixedBill extends Recap {
    public FixedBill() {

    }
    
    public FixedBill(Integer idCustomer, DetailTransaksi detailTransaksi) {
        super(idCustomer, detailTransaksi);
    }
}
