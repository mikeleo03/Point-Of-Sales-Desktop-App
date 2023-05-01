package main.Pages;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import main.Barang.Barang;
import main.Barang.Inventory;  

public class InventoryPage extends JFrame {  
    private JFrame f;
    private ArrayList<JButton> itemButtons;
    private JButton addItem;
    private JButton removeItem;
    private Inventory inv;

    public InventoryPage(Inventory inv) {
        this.f = new JFrame("Inventory");
        this.inv = inv;
        this.itemButtons = new ArrayList<JButton>();
        int counter = 0;
        for (Barang b : inv.getListBarang()) {
            // Icon icon = new ImageIcon(b.getPicturePath());
            this.itemButtons.add(new JButton(b.getName()));
            this.itemButtons.get(counter).setBounds(100+(counter)*40, 50+(counter)*40, 
                        30, 30);
            counter++;
        }
        f.setLayout(new GridLayout(counter/5+1, 5));
        for(JButton button : this.itemButtons) {
            f.add(button);
        }
        this.addItem = new JButton("Add");
        this.addItem.setBounds(130, 400, 30, 30);
        f.add(this.addItem);
        f.setSize(1500,1500);
        f.setVisible(true);
    }

    public static void main(String[] args) { 
        Inventory test = new Inventory();
        Barang testBarang = new Barang(0, "test", 10, 10, 10, "test", "./img/test.jpg");
        for (int i=0; i<10; i++) {
            testBarang.setID(i+1);
            test.addBarang(testBarang);
        }
        JFrame f = new InventoryPage(test);//creating instance of JFrame
    }  
}  
