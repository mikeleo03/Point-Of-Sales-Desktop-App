package main.Pages.InventoryPage;

import java.awt.*;
import javax.swing.*;

public class ItemSearchPane extends JPanel {
    private JLabel itemNameLbl;
    private JLabel priceLbl;
    private JLabel categLbl;
    private JTextField itemNameBox;
    private JTextField lowPriceBoundBox, highPriceBoundBox;
    private JTextField categBox;

    ItemSearchPane() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(2, 2, 2, 2);

        this.itemNameLbl = new JLabel("Item name :");
        add(this.itemNameLbl, gbc);
        gbc.gridy++;
        this.priceLbl = new JLabel("Price range :");
        add(this.priceLbl, gbc);
        gbc.gridy++;
        this.categLbl = new JLabel("Item category :");
        add(this.categLbl, gbc);
        gbc.gridy++;

        gbc.gridy = 0;
        gbc.gridx++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.itemNameBox = new JTextField(14);
        add(this.itemNameBox, gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;
        this.lowPriceBoundBox = new JTextField(7);
        add(this.lowPriceBoundBox, gbc);
        gbc.gridx++;
        this.highPriceBoundBox = new JTextField(7);
        add(this.highPriceBoundBox, gbc);
        gbc.gridwidth = 2;
        gbc.gridy++;
        gbc.gridx--;
        this.categBox = new JTextField(14);
        add(this.categBox, gbc);
    }

    public String getSearchName() {
        return this.itemNameBox.getText();
    }

    public Double getLowPriceBound() {
        try {
            return Double.parseDouble(this.lowPriceBoundBox.getText());
        } catch (Exception e) {
            return -1.0;
        }
    }

    public Double getHighPriceBound() {
        try {
            return Double.parseDouble(this.highPriceBoundBox.getText());
        } catch (Exception e) {
            return -1.0;
        }
    }

    public String getSearchCategory() {
        return this.categBox.getText();
    }

    public void resetFields() {
        this.itemNameBox.setText("");
        this.lowPriceBoundBox.setText("");
        this.highPriceBoundBox.setText("");
        this.categBox.setText("");
    }
}
