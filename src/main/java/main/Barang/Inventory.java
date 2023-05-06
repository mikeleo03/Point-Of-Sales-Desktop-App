package main.Barang;
/* 
 * File : Inventory.java
 * Class untuk merepresentasikan inventory yang berisi semua barang yang dijual
 */

import java.util.ArrayList;
import java.io.Serializable;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class Inventory implements Serializable {
    private Integer lastID;
    private ArrayList<Barang> listBarang;

    // CONSTRUCTOR
    public Inventory() {
        this.listBarang = new ArrayList<Barang>();
        this.lastID = 0;
    }

    public Inventory(ArrayList<Barang> list) {
        // Konstruktor untuk masukan dari file
        this.listBarang = list;
        this.lastID = 0;
        for (Barang b : list) {
            if (b.getID() > this.lastID) {
                this.lastID = b.getID();
            }
        }
    }

    // GETTER
    @XmlAttribute
    public Integer getLastID() {
        // Getter lastID
        return this.lastID;
    }
    public void setLastID(Integer lastID) {
        // Setter lastID
        this.lastID = lastID;
    }

    @XmlElement
    public ArrayList<Barang> getListBarang() {
        // Getter listBarang
        return this.listBarang;
    }
    public void setListBarang(ArrayList<Barang> listBarang) {
        // Setter listBarang
        this.listBarang = listBarang;
    }

    public Barang getBarangByID(int ID) {
        // Mengembalikan barang dengan ID yang diminta
        for (Barang b : this.listBarang) {
            if (b.getID() == ID) {
                return b;
            }
        }
        return null;
    }

    // PENAMBAHAN/PENGURANGAN BARANG
    public void addBarang(Barang b) {
        // Menambah barang baru
        b.setID(this.lastID);
        this.listBarang.add(b);
        this.lastID++;
    }

    public void deleteBarang(int ID) {
        // Menghapus barang dengan ID yang diberikan
        for (int i=0; i<this.listBarang.size(); i++) {
            if (this.listBarang.get(i).getID() == ID) {
                this.listBarang.remove(this.listBarang.get(i));
            }
        }
    }

    public void updateBarang(int ID, String name, Integer stock, Double price, Double buyPrice, String category, String picturePath) {
        // Update barang dengan data baru. Atribut yang tidak ingin diupdate diberi parameter null
        Barang b = this.getBarangByID(ID);
        if (b != null) {
            if (name != null) {
                b.setName(name);
            }
            if (stock != null) {
                b.setStock(stock);
            }
            if (price != null) {
                b.setPrice(price);
            }
            if (buyPrice != null) {
                b.setBuyPrice(buyPrice);
            }
            if (category != null) {
                b.setCategory(category);
            }
            if (picturePath != null) {
                b.setPicturePath(picturePath);
            }
        }
    }
}