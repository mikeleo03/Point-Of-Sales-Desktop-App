package main.Pages.InventoryPage;
import main.Barang.Barang;
import main.Barang.Inventory;  

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;  

class InvPane extends JPanel implements ActionListener {
    static final int MAXCOLUMN=6;
    private JLabel title;
    private JButton addItem;
    private JButton removeItem;
    private Inventory inv;
    private AddItemPane addBarangPanel;
    private ItemsDisplay itemDisplay;
    private JScrollPane itemDisplayScroll;
    private boolean remove;

    public InvPane(Inventory inv) {
        this.remove = false;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.gridwidth = MAXCOLUMN;

        this.addBarangPanel = new AddItemPane(this);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.title = new JLabel("Inventory");
        this.title.setFont(new Font("Arial", ALLBITS, 40));
        add(this.title, gbc);
        gbc.gridy++;
        
        gbc.weightx = 0.8;
        gbc.weighty = 0.8;
        gbc.fill = GridBagConstraints.BOTH;
        this.inv = inv;
        this.itemDisplay = new ItemsDisplay(this.inv);
        this.itemDisplayScroll = new JScrollPane(this.itemDisplay);
        this.itemDisplayScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.itemDisplayScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.itemDisplayScroll.setPreferredSize(new Dimension(400,400));
        add(this.itemDisplayScroll,gbc);
        
        gbc.weightx = 0.2;
        gbc.weighty = 0.2;
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.addItem = new JButton("Add");
        add(this.addItem, gbc);
        this.addItem.addActionListener(this);
        gbc.gridy++;
        this.removeItem = new JButton("Remove");
        this.removeItem.addActionListener(this);
        add(this.removeItem, gbc);
    }

    public void addBrng(Barang b) {
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
            int inputSuccess = JOptionPane.showConfirmDialog(this, this.addBarangPanel, "Insert item details", JOptionPane.OK_CANCEL_OPTION);
            Barang b = null;
            if (inputSuccess == JOptionPane.OK_OPTION) {
                try {
                    b = this.addBarangPanel.getBarang();
                    this.addBrng(b);
                } catch (Exception err) {
                    System.out.println(err);
                }
            }
        } else {
            this.remove = true;
        }
    }
}
