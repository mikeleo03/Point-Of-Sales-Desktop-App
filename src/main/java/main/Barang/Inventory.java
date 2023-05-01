package main.Barang;
/* 
 * File : Inventory.java
 * Class untuk merepresentasikan inventory yang berisi semua barang yang dijual
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventory {
    private HashMap<Integer,Barang> listBarang;

    // CONSTRUCTOR
    public Inventory() {
        this.listBarang = new HashMap<Integer,Barang>();
    }

    // GETTER
    public List<Barang> getListBarang() {
        List<Barang> temp = new ArrayList<Barang>();
        for (Barang b : this.listBarang.values()) {
            temp.add(b);
        }
        return temp;
    }
    public Barang getBarangByID(int ID) {
        return this.listBarang.get(ID);
    }

    // PENAMBAHAN/PENGURANGAN BARANG
    public void addBarang(Barang b) {
        this.listBarang.put(b.getID(), b);
    }
    public void deleteBarang(int ID) {
        this.listBarang.remove(ID);
    }
}