/* 
 * File : Barang.java
 * Class untuk merepresentasikan barang-barang yang dijual
 */

package main.Barang;

public class Barang implements Priceable {
    // ATTRIBUTES
    private int id;
    private String nama;
    private int stok;
    private float hargaJual,hargaBeli;
    private String kategori;
    private String pathGambar;

    // CONSTRUCTOR
    public Barang(int id, String nama, int stok, float hargaJual, float hargaBeli, String kategori, String pathGambar) {
        this.id = id;
        this.nama = nama;
        this.stok = stok;
        this.hargaJual = hargaJual;
        this.hargaBeli = hargaBeli;
        this.kategori = kategori;
        this.pathGambar = pathGambar;
    }

    // GETTER
    public int getID() {
        return this.id;
    }
    public String getName() {
        return this.nama;
    }
    public int getStock() {
        return this.stok;
    }
    public float getBuyPrice() {
        return this.hargaBeli;
    }
    public String getCategory() {
        return this.kategori;
    }
    public String getPicturePath() {
        return this.pathGambar;
    }

    // SETTER
    public void setID(int ID) {
        this.id = ID;
    }
    public void setName(String nama) {
        this.nama = nama;
    }
    public void setStock(int stok) {
        this.stok = stok;
    }
    public void setSellPrice(int hargaJual) {
        this.hargaJual = hargaJual;
    }
    public void setBuyPrice(int hargaBeli) {
        this.hargaBeli = hargaBeli;
    }
    public void setCategory(String kategori) {
        this.kategori = kategori;
    }
    public void setPicturePath(String pathGambar) {
        this.pathGambar = pathGambar;
    }

    // Interface
    public float getPrice() {
        return this.hargaJual;
    }
}