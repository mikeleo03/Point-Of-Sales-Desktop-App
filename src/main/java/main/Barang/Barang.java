/* 
 * File : Barang.java
 * Class untuk merepresentasikan barang-barang yang dijual
 */

package main.Barang;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement
public class Barang implements Priceable, Serializable {
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

    // GETTER-SETTER
    @XmlAttribute
    public Integer getID() {
        return this.ID;
    }
    public void setID(Integer ID) {
        this.ID = ID;
    }

    @XmlElement
    public String getName() {
        return this.name;
    }
    public void setName(String nama) {
        this.name = nama;
    }

    @XmlElement
    public Integer getStock() {
        return this.stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @XmlElement
    public Double getBuyPrice() {
        return this.buyPrice;
    }
    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    @XmlElement
    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    @XmlElement
    public String getPicturePath() {
        return this.picturePath;
    }
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    // Interface
    @XmlElement
    public Double getPrice() {
        return this.price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}