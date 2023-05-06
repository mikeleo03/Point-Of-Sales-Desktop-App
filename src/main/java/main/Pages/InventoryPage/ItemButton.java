package main.Pages.InventoryPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class ItemButton extends JButton {
    private int ID;
    private String Name;
    
    ItemButton(String arg, Icon i, int ID) {
        super(arg, i);
        this.Name = arg;
        this.ID = ID;
    }
    
    public int getItemID() {
        return this.ID;
    }
}
