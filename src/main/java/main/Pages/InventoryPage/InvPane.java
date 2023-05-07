package main.Pages.InventoryPage;
import main.Barang.Barang;
import main.Barang.Inventory;
import main.Pages.Observer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.ArrayList;

public class InvPane extends JPanel implements ActionListener{
    static final int MAXCOLUMN=6;
    private Observer<Barang> obs;

    private JLabel title;
    private JButton searchItem;
    private JButton addItem;
    private Inventory inv;
    private ItemPane itemPanel;
    private ItemsDisplay itemDisplay;
    private JScrollPane itemDisplayScroll;
    private ItemSearchPane itemSearchPanel;

    private Integer selectedID;

    public InvPane(Inventory inv) {
        this.selectedID = null;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(2, 2, 2, 2);

        this.itemPanel = new ItemPane();
        this.itemSearchPanel = new ItemSearchPane();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.title = new JLabel("Inventory");
        this.title.setFont(new Font("Arial", ALLBITS, 40));
        add(this.title, gbc);
        gbc.gridy++;

        gbc.gridwidth = MAXCOLUMN;
        gbc.gridx = 0;
        gbc.weightx = 0.8;
        gbc.weighty = 0.8;
        gbc.fill = GridBagConstraints.BOTH;
        this.inv = inv;
        this.itemDisplay = new ItemsDisplay(this.inv, this);
        this.itemDisplayScroll = new JScrollPane(this.itemDisplay);
        this.itemDisplayScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.itemDisplayScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(this.itemDisplayScroll,gbc);

        gbc.weightx = 0.2;
        gbc.weighty = 0.2;
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.searchItem = new JButton("Search");
        this.searchItem.addActionListener(this);
        add(this.searchItem, gbc);
        gbc.gridy++;
        this.addItem = new JButton("Add");
        this.addItem.addActionListener(this);
        add(this.addItem, gbc);
    }

    public void addBarang(Barang b) {
        this.inv.addBarang(b);
        this.itemDisplay.addButton(b);
        this.itemDisplay.revalidate();
        this.itemDisplay.repaint();
        this.itemDisplayScroll.revalidate();
        this.itemDisplayScroll.repaint();
        this.revalidate();
        this.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();
        if (com.equals("Add")) {
            int inputSuccess = JOptionPane.showConfirmDialog(this, this.itemPanel, "Insert item details", JOptionPane.OK_CANCEL_OPTION);
            Barang b = null;
            this.itemDisplay.reshowAll(this.inv);
            if (inputSuccess == JOptionPane.OK_OPTION) {
                try {
                    b = this.itemPanel.getBarang();
                    this.addBarang(b);
                } catch (Exception err) {
                    System.out.println(err);
                }
            }
            this.itemPanel.resetFields();
        } else if (com.equals("Search")) { // Search barang
            String[] options = new String[] {"Search", "Cancel"};
            int input = JOptionPane.showOptionDialog(this, this.itemSearchPanel, "Search Item",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                                null, options, options[0]);
            if (input == 0) {
                ArrayList<Barang> result = this.inv.getBarangByMainParams(this.itemSearchPanel.getSearchName(), 
                                            this.itemSearchPanel.getLowPriceBound(), this.itemSearchPanel.getHighPriceBound(), 
                                            this.itemSearchPanel.getSearchCategory());
                this.itemDisplay.generateButtonsFromList(result);
            }
            this.itemSearchPanel.resetFields();
        }
        else { // Lihat info barang
            Object source = e.getSource();
            if (source.getClass() == ItemButton.class) {
                ItemButton button = (ItemButton) source;
                this.selectedID = button.getItemID();
                this.itemPanel.insertItemInfo(this.inv.getBarangByID(this.selectedID));
                String[] options = new String[] {"Save", "Remove", "Cancel"};
                int input = JOptionPane.showOptionDialog(this, this.itemPanel, "Item info",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                                    null, options, options[0]);
                if (input == 0) { // Save
                    try {
                        Barang b = this.itemPanel.getBarang();
                        this.inv.updateBarang(this.selectedID, b.getName(), b.getStock(), b.getPrice(), b.getBuyPrice(), b.getCategory(), b.getPicturePath());
                        this.itemDisplay.updateButton(this.selectedID, b);
                    } catch (Exception err) {
                        System.out.println(err);
                    }
                } else if (input == 1) { //Remove
                    this.itemDisplay.reshowAll(this.inv);
                    this.inv.deleteBarang(this.selectedID);
                    this.itemDisplay.deleteButton(this.selectedID);
                }
                this.itemPanel.resetFields();
            }
        }
    }
}
