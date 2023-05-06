package main.Pages.InventoryPage;
import main.Barang.Barang;
import main.Barang.Inventory;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

class ItemsDisplay extends JPanel {
    private ArrayList<ItemButton> itemButtons;
    private GridBagConstraints gbc;
    private ActionListener al; // ActionListener untuk masing-masing button

    ItemsDisplay(Inventory inv, ActionListener a) {
        this.al = a;
        setLayout(new GridBagLayout());
        setSize(500,400);
        this.gbc = new GridBagConstraints();
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.insets = new Insets(2, 2, 2, 2);
        this.itemButtons = new ArrayList<ItemButton>();
        for (Barang b : inv.getListBarang()) {
            ImageIcon icon = new ImageIcon(b.getPicturePath());
            Image img = icon.getImage();
            img = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            icon.setImage(img);
            ItemButton newButton = new ItemButton(b.getName(), icon, b.getID());
            newButton.addActionListener(this.al);
            this.itemButtons.add(newButton);
        }
        for(JButton button : this.itemButtons) {
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            button.setPreferredSize(new Dimension(200, 200));
            add(button, this.gbc);
            if (this.gbc.gridx < InvPane.MAXCOLUMN-1) {
                this.gbc.gridx++;
            } else {
                this.gbc.gridx = 0;
                this.gbc.gridy++;
            }
        }
    }

    public void updateDisplay(Inventory inv) {
        for (int i=0; i<this.itemButtons.size(); i++) {
            int currID = this.itemButtons.get(i).getItemID();
            Barang b = inv.getBarangByID(currID);
            if (b == null) {
                ItemButton deletedButton = this.itemButtons.get(i);
                remove(deletedButton);
                this.itemButtons.remove(deletedButton);
                continue;
            }
            this.itemButtons.get(i).setText(b.getName());
            ImageIcon icon = new ImageIcon(b.getPicturePath());
            Image img = icon.getImage();
            img = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            icon.setImage(img);
            this.itemButtons.get(i).setIcon(icon);
        }
        revalidate();
        repaint();
    }

    public void addButton(Barang b) {
        ImageIcon icon = new ImageIcon(b.getPicturePath());
        Image img = icon.getImage();
        img = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        icon.setImage(img);
        ItemButton newButton = new ItemButton(b.getName(), icon, b.getID());
        newButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        newButton.setPreferredSize(new Dimension(200, 200));
        newButton.addActionListener(this.al);
        this.itemButtons.add(newButton);
        add(newButton, this.gbc);
        if (this.gbc.gridx < InvPane.MAXCOLUMN-1) {
            this.gbc.gridx++;
        } else {
            this.gbc.gridx = 0;
            this.gbc.gridy++;
        }
    }

    public void deleteButton (int ID) {
        for (ItemButton iButton : this.itemButtons) {
            if (iButton.getItemID() == ID) {
                remove(iButton);
                break;
            }
        }
        revalidate();
        repaint();
    }
}
