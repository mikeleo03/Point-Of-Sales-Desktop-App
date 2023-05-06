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

    public ArrayList<ElemenDetailTransaksi> getElement() {
        return this.detailTransaksi;
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

    public void addBarang (Barang barang, Integer jumlahBarang, Inventory inventory) {
        Integer id = getID(barang.getID());
        Double harga = barang.getPrice();
        // Jika barang tidak ada, maka tambahkan nilainya ke list
        if (id == -1) {
            this.detailTransaksi.add(new ElemenDetailTransaksi(barang.getID(), barang.getName(), jumlahBarang, harga * jumlahBarang));
        } else {
            Integer jumlahSebelumnya = this.detailTransaksi.get(id).getJumlahBarang();
            if (jumlahBarang + jumlahSebelumnya > barang.getStock()) {
                System.out.println("Stok tidak mencukupi.");
            } else {
                this.detailTransaksi.set(id, new ElemenDetailTransaksi(barang.getID(), barang.getName(), jumlahSebelumnya + jumlahBarang, harga * (jumlahSebelumnya + jumlahBarang)));
            }
        }
    }

    public void editQuantity(Barang barang, Integer quantity) {
        Integer id = getID(barang.getID());
        Double harga = barang.getPrice();
        // Jika barang tidak ada, maka tambahkan nilainya ke list
        if (id != -1) {
            if (quantity > barang.getStock() || quantity < 0) {
                System.out.println("Nilai kuantitas tidak valid.");
            } else {
                this.detailTransaksi.set(id, new ElemenDetailTransaksi(barang.getID(), barang.getName(), quantity, harga * quantity));
            }
        } else {
            System.out.println("Barang belum ada pada detail transaksi.");
        }
    }

    public void deleteDetail (Barang barang) {
        Integer id = getID(barang.getID());
        // Jika barang tidak ada, maka tambahkan nilainya ke list
        if (id != -1) {
            ElemenDetailTransaksi element = this.detailTransaksi.get(id);
            this.detailTransaksi.remove(element);
        } else {
            System.out.println("Barang belum ada pada detail transaksi.");
        }
    }
}
