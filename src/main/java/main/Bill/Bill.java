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
    
    public Bill(Integer idCustomer, DetailTransaksi detailTransaksi) {
        super(idCustomer, detailTransaksi);
    }

    public void editItemQuantity(Integer idBarang, Integer jumlahPerubahanBarang, Inventory inventory) {
        // mengubah jumlah barang di detail transaksi
        // jumlahPerubahanBarang bisa negatif dan positif
        DetailTransaksi dT = this.getDetailTransaksi();
        Barang barang = inventory.getBarangByID(idBarang);
        dT.editBarang(barang, jumlahPerubahanBarang, inventory);
        this.setDetailTransaksi(dT);
    }

    public void deleteItem(Integer idBarang, Integer jumlahBarang, Inventory inventory) {
        DetailTransaksi dT = this.getDetailTransaksi();
        Barang barang = inventory.getBarangByID(idBarang);
        dT.deleteDetail(barang);
        this.setDetailTransaksi(dT);
    }
}
