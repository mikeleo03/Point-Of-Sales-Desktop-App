package main.Pages.InventoryPage;

import javax.swing.*;

class ItemButton extends JButton {
    private int ID;
    private String Name;
    
    public ItemButton(String arg, Icon i, int ID) {
        super(arg, i);
        this.Name = arg;
        this.ID = ID;
    }
}
