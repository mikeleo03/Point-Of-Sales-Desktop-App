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
        return this.lastID;
    }
    public void setLastID(Integer lastID) {
        this.lastID = lastID;
    }

    @XmlElement
    public ArrayList<Barang> getListBarang() {
        return this.listBarang;
    }
    public void setListBarang(ArrayList<Barang> listBarang) {
        this.listBarang = listBarang;
    }

    public Barang getBarangByID(int ID) {
        for (Barang b : this.listBarang) {
            if (b.getID() == ID) {
                return b;
            }
        }
        return null;
    }

    // PENAMBAHAN/PENGURANGAN BARANG
    public void addBarang(Barang b) {
        b.setID(this.lastID);
        this.listBarang.add(b);
        this.lastID++;
    }

    public void deleteBarang(int ID) {
        for (Barang b : this.listBarang) {
            if (b.getID() == ID) {
                this.listBarang.remove(b);
            }
        }
    }
}