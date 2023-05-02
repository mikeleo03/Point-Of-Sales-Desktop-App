package main.Barang;
/* 
 * File : Inventory.java
 * Class untuk merepresentasikan inventory yang berisi semua barang yang dijual
 */

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Barang> listBarang;
    private int lastID;

    // CONSTRUCTOR
    public Inventory() {
        this.listBarang = new ArrayList<Barang>();
        this.lastID = 0;
    }
    public Inventory(ArrayList<Barang> list) {
        this.listBarang = list;
    }

    // GETTER
    public ArrayList<Barang> getListBarang() {
        return this.listBarang;
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
                return;
            }
        }
    }
}