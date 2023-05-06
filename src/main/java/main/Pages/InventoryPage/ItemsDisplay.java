package main.Pages.InventoryPage;
import main.Barang.Barang;
import main.Barang.Inventory;
import main.Pages.Utils;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

class ItemsDisplay extends JPanel {
    private ArrayList<ItemButton> itemButtons;
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private ActionListener al; // ActionListener untuk masing-masing button

    ItemsDisplay(Inventory inv, ActionListener a) {
        this.al = a;
        this.gbl = new GridBagLayout();
        setLayout(this.gbl);
        setSize(500,400);
        this.gbc = new GridBagConstraints();
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.gbc.insets = new Insets(2, 2, 2, 2);
        this.itemButtons = new ArrayList<ItemButton>();
        for (Barang b : inv.getListBarang()) {
            ItemButton newButton = new ItemButton(b.getName(), Utils.getImageIcon(b.getPicturePath()), b.getID());
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
                this.gbc = this.gbl.getConstraints(deletedButton);
                remove(deletedButton);
                this.itemButtons.remove(deletedButton);
                if (i < this.itemButtons.size()) {
                    repositionButtons(i);
                }
                continue;
            }
            this.itemButtons.get(i).setText(b.getName());
            this.itemButtons.get(i).setIcon(Utils.getImageIcon(b.getPicturePath()));
        }
        revalidate();
        repaint();
    }

    public void addButton(Barang b) {
        ItemButton newButton = new ItemButton(b.getName(), Utils.getImageIcon(b.getPicturePath()), b.getID());
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

    public void updateButton (int ID, Barang b) {
        for (int i = 0; i < this.itemButtons.size(); i++) {
            if (this.itemButtons.get(i).getItemID() == ID) {
                ItemButton updatedButton = this.itemButtons.get(i);
                updatedButton.setText(b.getName());
                updatedButton.setIcon(Utils.getImageIcon(b.getPicturePath()));
                break;
            }
        }
        revalidate();
        repaint();
    }

    public void deleteButton (int ID) {
        for (int i = 0; i < this.itemButtons.size(); i++) {
            if (this.itemButtons.get(i).getItemID() == ID) {
                ItemButton deletedButton = this.itemButtons.get(i);
                this.gbc = this.gbl.getConstraints(deletedButton);
                remove(deletedButton);
                this.itemButtons.remove(deletedButton);
                if (i < this.itemButtons.size()) {
                    repositionButtons(i);
                }
                break;
            }
        }
        revalidate();
        repaint();
    }

    public void repositionButtons(int idxStart) {
        for (int i=idxStart; i<this.itemButtons.size(); i++) {
            this.gbl.setConstraints(this.itemButtons.get(i), this.gbc);
            if (this.gbc.gridx < InvPane.MAXCOLUMN-1) {
                this.gbc.gridx++;
            } else {
                this.gbc.gridx = 0;
                this.gbc.gridy++;
            }
        }
        revalidate();
        repaint();
    }
}
