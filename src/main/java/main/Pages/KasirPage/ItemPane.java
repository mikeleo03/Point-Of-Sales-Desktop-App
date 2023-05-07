package main.Pages.KasirPage;
import main.Barang.Barang;

import java.awt.*;

import javax.swing.*;

class ItemPane extends JPanel {
    private JLabel itemIDLabel;
    private JLabel itemNameLbl;
    private JLabel priceLbl, buyPriceLbl;
    private JLabel stockLbl;
    private JLabel categLbl;
    private JLabel imgPathLbl;
    private JLabel image;
    private JTextField newItemNameBox;
    private JTextField priceBox, buyPriceBox;
    private JTextField stockBox;
    private JTextField categBox;
    private JTextField imgPathBox;

    ItemPane() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(2, 2, 2, 2);

        gbc.gridwidth = 2;
        this.itemIDLabel = new JLabel();
        add(this.itemIDLabel, gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;
        this.itemNameLbl = new JLabel("Item name :");
        add(this.itemNameLbl, gbc);
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

        gbc.gridy = 1;
        gbc.gridx++;
        this.newItemNameBox = new JTextField(30);
        add(this.newItemNameBox, gbc);
        gbc.gridy++;
        this.priceBox = new JTextField(30);
        add(this.priceBox, gbc);
        gbc.gridy++;
        this.buyPriceBox = new JTextField(30);
        add(this.buyPriceBox, gbc);
        gbc.gridy++;
        this.stockBox = new JTextField(30);
        add(this.stockBox, gbc);
        gbc.gridy++;
        this.categBox = new JTextField(30);
        add(this.categBox, gbc);
        gbc.gridy++;
        this.imgPathBox = new JTextField(30);
        add(this.imgPathBox, gbc);
        gbc.gridy++;
        
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        this.image = new JLabel();
        add(this.image);
    }

    public void insertItemInfo(Barang b) {
        this.itemIDLabel.setText("ID : "+ b.getID().toString());
        this.newItemNameBox.setText(b.getName());
        this.priceBox.setText(b.getPrice().toString());
        this.buyPriceBox.setText(b.getBuyPrice().toString());
        this.stockBox.setText(b.getStock().toString());
        this.categBox.setText(b.getCategory());
        this.imgPathBox.setText(b.getPicturePath());
        ImageIcon icon = new ImageIcon(b.getPicturePath());
        Image img = icon.getImage();
        img = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        icon.setImage(img);
        this.image.setIcon(icon);
        revalidate();
        repaint();
    }

    public Barang getBarang() {
        Barang b = new Barang(this.newItemNameBox.getText(), Integer.parseInt(this.stockBox.getText()), 
                    Double.parseDouble(this.priceBox.getText()),Double.parseDouble(this.buyPriceBox.getText()),
                    this.categBox.getText(), this.imgPathBox.getText());
        return b;
    }

    public void resetFields() {
        this.itemIDLabel.setText("");
        this.newItemNameBox.setText("");
        this.stockBox.setText("");
        this.priceBox.setText("");
        this.buyPriceBox.setText("");
        this.categBox.setText("");
        this.imgPathBox.setText("");
        this.image.setIcon(null);
    }   
}
