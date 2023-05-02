/* 
 * File : Barang.java
 * Class untuk merepresentasikan barang-barang yang dijual
 */

package main.Barang;

public class Barang implements Priceable {
    // ATTRIBUTES
    private Integer ID;
    private String name;
    private Integer stock;
    private Double price;
    private Double buyPrice;
    private String category;
    private String picturePath;

    // CONSTRUCTOR
    public Barang() {
        this.ID = 0;
        this.name = "";
        this.stock = 0;
        this.price = 0.0;
        this.buyPrice = 0.0;
        this.category = "";
        this.picturePath = "";
    }

    public Barang(String name, Integer stock, Double price, Double buyPrice, String category, String picturePath) {
        this.ID = 0;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.buyPrice = buyPrice;
        this.category = category;
        this.picturePath = picturePath;
    }

    // GETTER
    public Integer getID() {
        return this.ID;
    }
    public String getName() {
        return this.name;
    }
    public Integer getStock() {
        return this.stock;
    }
    public Double getBuyPrice() {
        return this.buyPrice;
    }
    public String getCategory() {
        return this.category;
    }
    public String getPicturePath() {
        return this.picturePath;
    }

    // SETTER
    public void setID(Integer ID) {
        this.ID = ID;
    }
    public void setName(String nama) {
        this.name = nama;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    // Interface
    public Double getPrice() {
        return this.price;
    }
}