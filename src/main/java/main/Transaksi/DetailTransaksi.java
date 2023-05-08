/*
 * File : DetailTransaksi.java
 * Class untuk menambahkan elemen detail transaksi detail transaksi
 */

package main.Transaksi;
import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.*;

import main.Barang.Barang;
import main.Barang.Inventory;

@XmlRootElement
public class DetailTransaksi implements Serializable {
    // ATTRIBUTES
    private ArrayList<ElemenDetailTransaksi> element;

    // CONSTRUCTORS
    public DetailTransaksi() {
        this.element = new ArrayList<ElemenDetailTransaksi>();
    }

    @XmlElement
    public ArrayList<ElemenDetailTransaksi> getElement() {
        return this.element;
    }

    public void setElement(ArrayList<ElemenDetailTransaksi> element) {
        this.element = element;
    }

    // METHOD
    public Integer getID (Integer idBarang) {
        int id = 0;
        for (ElemenDetailTransaksi detail : this.element) {
            if (detail.getIdBarang() == idBarang) {
                return id;
            }
            id++;
        }
        return -1;
    }

    public void editBarang (Barang barang, Integer jumlahBarang, Inventory inventory) {
        Integer id = getID(barang.getID());
        Double harga = barang.getPrice();
        // Jika barang tidak ada, maka tambahkan nilainya ke list
        if (id == -1) {
            this.element.add(new ElemenDetailTransaksi(barang.getID(), barang.getName(), jumlahBarang, harga * jumlahBarang));
        } else {
            Integer jumlahSebelumnya = this.element.get(id).getJumlahBarang();
            if (jumlahBarang > 0) {
                if (jumlahBarang + jumlahSebelumnya > barang.getStock()) {
                    System.out.println("Stok tidak mencukupi.");
                } else {
                    this.element.set(id, new ElemenDetailTransaksi(barang.getID(), barang.getName(), jumlahSebelumnya + jumlahBarang, harga * (jumlahSebelumnya + jumlahBarang)));
                }
            } else {
                this.element.set(id, new ElemenDetailTransaksi(barang.getID(), barang.getName(), jumlahSebelumnya + jumlahBarang, harga * (jumlahSebelumnya + jumlahBarang)));
            }
        }
    }

    public void setQuantity(Barang barang, Integer quantity) {
        Integer id = getID(barang.getID());
        Double harga = barang.getPrice();
        // Jika barang tidak ada, maka tambahkan nilainya ke list
        if (id != -1) {
            if (quantity > barang.getStock() || quantity < 0) {
                System.out.println("Nilai kuantitas tidak valid.");
            } else {
                this.element.set(id, new ElemenDetailTransaksi(barang.getID(), barang.getName(), quantity, harga * quantity));
            }
        } else {
            System.out.println("Barang belum ada pada detail transaksi.");
        }
    }

    public void deleteDetail (Barang barang) {
        Integer id = getID(barang.getID());
        // Jika barang tidak ada, maka tambahkan nilainya ke list
        if (id != -1) {
            ElemenDetailTransaksi element = this.element.get(id);
            this.element.remove(element);
        } else {
            System.out.println("Barang belum ada pada detail transaksi.");
        }
    }
}
