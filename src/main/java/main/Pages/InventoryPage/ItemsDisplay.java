package main.Pages.InventoryPage;
import main.Barang.Barang;
import main.Barang.Inventory;  

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;  

class ItemsDisplay extends JPanel {
    private ArrayList<ItemButton> itemButtons;
    private GridBagConstraints gbc;
    public ItemsDisplay(Inventory inv) {
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
            newButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    
                }
            });
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

    public void addButton(Barang b) {
        ImageIcon icon = new ImageIcon(b.getPicturePath());
        Image img = icon.getImage();
        img = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        icon.setImage(img);
        ItemButton newButton = new ItemButton(b.getName(), icon, b.getID());
        newButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        newButton.setPreferredSize(new Dimension(200, 200));
        this.itemButtons.add(newButton);
        add(newButton, this.gbc);
        if (this.gbc.gridx < InvPane.MAXCOLUMN-1) {
            this.gbc.gridx++;
        } else {
            this.gbc.gridx = 0;
            this.gbc.gridy++;
        }
    }
}
