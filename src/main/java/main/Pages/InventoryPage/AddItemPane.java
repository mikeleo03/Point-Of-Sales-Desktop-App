package main.Pages.InventoryPage;
import main.Barang.Barang;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;  

class AddItemPane extends JPanel {
    private final int TEXTBOXWIDTH = 100;
    private final int TEXTBOXHEIGHT = 30;
    private JLabel newItemNameLbl;
    private JLabel priceLbl, buyPriceLbl;
    private JLabel stockLbl;
    private JLabel categLbl;
    private JLabel imgPathLbl;
    private JTextField newItemNameBox;
    private JTextField priceBox, buyPriceBox;
    private JTextField stockBox;
    private JTextField categBox;
    private JTextField imgPathBox;
    private JButton submitBtn;

    public AddItemPane(ActionListener a) {
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(2, 2, 2, 2);
        
        this.newItemNameLbl = new JLabel("Item name :");
        add(this.newItemNameLbl, gbc);
        gbc.gridy++;
        this.priceLbl = new JLabel("Item sell price :");
        add(this.priceLbl, gbc);
        gbc.gridy++;
        this.buyPriceLbl = new JLabel("Item buy price :");
        add(this.buyPriceLbl, gbc);
        gbc.gridy++;
        this.stockLbl = new JLabel("Item stock :");
        add(this.stockLbl, gbc);
        gbc.gridy++;
        this.categLbl = new JLabel("Item category :");
        add(this.categLbl, gbc);
        gbc.gridy++;
        this.imgPathLbl = new JLabel("Item image path :");
        add(this.imgPathLbl, gbc);

        gbc.gridy = 0;
        gbc.gridx++;
        this.newItemNameBox = new JTextField(20);
        this.newItemNameBox.requestFocus();
        this.newItemNameBox.setPreferredSize(new Dimension(TEXTBOXWIDTH,TEXTBOXHEIGHT));
        add(this.newItemNameBox, gbc);
        gbc.gridy++;
        this.priceBox = new JTextField(20);
        this.priceBox.requestFocus();
        this.priceBox.setPreferredSize(new Dimension(TEXTBOXWIDTH,TEXTBOXHEIGHT));
        add(this.priceBox, gbc);
        gbc.gridy++;
        this.buyPriceBox = new JTextField(20);
        this.buyPriceBox.requestFocus();
        this.buyPriceBox.setPreferredSize(new Dimension(TEXTBOXWIDTH,TEXTBOXHEIGHT));
        add(this.buyPriceBox, gbc);
        gbc.gridy++;
        this.stockBox = new JTextField(20);
        this.stockBox.setFocusable(true);
        this.stockBox.setPreferredSize(new Dimension(TEXTBOXWIDTH,TEXTBOXHEIGHT));
        add(this.stockBox, gbc);
        gbc.gridy++;
        this.categBox = new JTextField(20);
        this.categBox.setFocusable(true);
        this.categBox.setPreferredSize(new Dimension(TEXTBOXWIDTH,TEXTBOXHEIGHT));
        add(this.categBox, gbc);
        gbc.gridy++;
        this.imgPathBox = new JTextField(20);
        this.imgPathBox.setFocusable(true);
        this.imgPathBox.setPreferredSize(new Dimension(TEXTBOXWIDTH,TEXTBOXHEIGHT));
        add(this.imgPathBox, gbc);
        gbc.gridy++;
    }

    public Barang getBarang() {
        Barang b = new Barang(this.newItemNameBox.getText(), Integer.parseInt(this.stockBox.getText()), 
                    Double.parseDouble(this.priceBox.getText()),Double.parseDouble(this.buyPriceBox.getText()),
                    this.categBox.getText(), this.imgPathBox.getText());
        this.newItemNameBox.setText("");
        this.stockBox.setText("");
        this.priceBox.setText("");
        this.buyPriceBox.setText("");
        this.categBox.setText("");
        this.imgPathBox.setText("");
        return b;
    }
}
