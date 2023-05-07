package main.Pages.KasirPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class QtyButton extends JButton {
    private int ID;
    private String Name;
    
    QtyButton(String arg, int ID) {
        super(arg);
        this.Name = arg;
        this.ID = ID;
    }
    
    public int getQtyID() {
        return this.ID;
    }
}
