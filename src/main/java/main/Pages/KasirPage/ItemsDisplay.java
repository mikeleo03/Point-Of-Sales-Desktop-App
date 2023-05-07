package main.Pages.KasirPage;
import main.Bill.*;
import main.Barang.Barang;
import main.Barang.Inventory;
import main.Pages.Utils;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.*;

class ItemsDisplay extends JPanel {
    // private ArrayList<ItemButton> itemButtons;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private ActionListener al; // ActionListener untuk masing-masing button

    ItemsDisplay(Inventory inv, Bill bill, ActionListener a) {
        this.al = a;
        this.gbl = new GridBagLayout();
        setLayout(this.gbl);
        setSize(500,400);
        this.gbc = new GridBagConstraints();
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.insets = new Insets(2, 2, 2, 2);
        // this.itemButtons = new ArrayList<ItemButton>();
        for (Barang b : inv.getListBarang()) {
            // Instansiasi ItemGrid
            ItemGrid itemGrid = new ItemGrid(inv, b, bill, a);
            // Menambahkan ke panel ItemsDisplay
            add(itemGrid, this.gbc);
            // Menambahkan ItemButton dari ItemGrid ke daftar itemButtons
            // itemButtons.add(itemGrid.getItemButton());
            if (this.gbc.gridx < 2) {
                this.gbc.gridx++;
            } else {
                this.gbc.gridx = 0;
                this.gbc.gridy++;
            }
        }
    }
    
    // ItemsDisplay(Inventory inv, ActionListener a) {
    //     this.al = a;
    //     this.gbl = new GridBagLayout();
    //     setLayout(this.gbl);
    //     setSize(500,400);
    //     this.gbc = new GridBagConstraints();
    //     this.gbc.gridx = 0;
    //     this.gbc.gridy = 0;
    //     this.gbc.insets = new Insets(2, 2, 2, 2);
    //     this.itemButtons = new ArrayList<ItemButton>();
    //     for (Barang b : inv.getListBarang()) {
    //         ItemButton newButton = new ItemButton(b.getName(), Utils.getImageIcon(b.getPicturePath()), b.getID());
    //         newButton.addActionListener(this.al);
    //         this.itemButtons.add(newButton);
    //     }
    //     for(JButton button : this.itemButtons) {
    //         button.setVerticalTextPosition(SwingConstants.BOTTOM);
    //         button.setPreferredSize(new Dimension(200, 200));
    //         add(button, this.gbc);
    //         if (this.gbc.gridx < InvPane.MAXCOLUMN-1) {
    //             this.gbc.gridx++;
    //         } else {
    //             this.gbc.gridx = 0;
    //             this.gbc.gridy++;
    //         }
    //     }
    // }
}
