/*
 * File : DetailTransaksi.java
 * Class untuk menambahkan elemen detail transaksi detail transaksi
 */

package main.Transaksi;
import java.util.ArrayList;

import main.Barang.Barang;
import main.Barang.Inventory;

public class DetailTransaksi {
    // ATTRIBUTES
    private ArrayList<ElemenDetailTransaksi> detailTransaksi;

    // CONSTRUCTORS
    public DetailTransaksi() {
        this.detailTransaksi = new ArrayList<ElemenDetailTransaksi>();
    }

    // METHOD
    public Integer getID (Integer idBarang) {
        int id = 0;
        for (ElemenDetailTransaksi detail : this.detailTransaksi) {
            if (detail.getIdBarang() == idBarang) {
                return id;
            }
            id++;
        }
        return -1;
    }

    public void addBarang(Integer idBarang, Integer jumlahBarang, Inventory inventory) {
        Integer id = getID(idBarang);
        Barang barang = inventory.getBarangByID(id);
        Double harga = barang.getPrice();
        // Jika barang tidak ada, maka tambahkan nilainya ke list
        if (id == -1) {
            this.detailTransaksi.add(new ElemenDetailTransaksi(barang.getID(), jumlahBarang, harga * jumlahBarang));
        } else {
            Integer jumlahSebelumnya = this.detailTransaksi.get(id).getJumlahBarang();
            this.detailTransaksi.set(id, new ElemenDetailTransaksi(barang.getID(), jumlahSebelumnya + jumlahBarang, harga * (jumlahSebelumnya + jumlahBarang)));
        }
    }
}
