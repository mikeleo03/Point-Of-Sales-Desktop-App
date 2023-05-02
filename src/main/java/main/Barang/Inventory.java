package main.Barang;
/* 
 * File : Inventory.java
 * Class untuk merepresentasikan inventory yang berisi semua barang yang dijual
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;

public class Inventory {
    private HashMap<Integer,Barang> listBarang;
    private int lastID;

    // CONSTRUCTOR
    public Inventory() {
        this.listBarang = new HashMap<Integer,Barang>();
        this.lastID = 0;
    }
    public Inventory(HashMap<Integer,Barang> Map) {
        this.listBarang = Map;
        this.lastID = Collections.max(Map.keySet());
    }

    // GETTER
    public HashMap<Integer,Barang> getMapBarang() {
        return this.listBarang;
    }
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
        b.setID(this.lastID);
        this.listBarang.put(this.lastID, b);
        this.lastID++;
    }
    public void deleteBarang(int ID) {
        this.listBarang.remove(ID);
    }
}