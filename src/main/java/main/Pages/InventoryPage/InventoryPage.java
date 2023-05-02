package main.Pages.InventoryPage;
import main.Barang.Inventory;  

import java.awt.*;

import javax.swing.*;  

public class InventoryPage extends JFrame {  
    private InvPane p;
    final int WIDTH = 700, HEIGHT = 400;

    public InventoryPage(Inventory inv) {
        this.setTitle("Inventory");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        this.p = new InvPane(inv);
        add(this.p);
        setVisible(true);
    }
} 