package main.Pages.KasirPage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import main.Barang.*;
import main.Bill.*;
import main.Observer.*;
import main.Pages.Utils;

public class ItemGrid extends JPanel implements Subscriber {
    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel stockLabel;
    private JLabel priceLabel;
    private JButton minusButton;
    private JLabel quantityLabel;
    private JButton plusButton;
    private QtyButton qtyButton;
    private Bill bill;
    private Barang barang;
    private Inventory inv;

    public ItemGrid(Inventory inv, Barang barang, Bill bill, ActionListener al) {
        this.barang = barang;
        this.inv = inv;
        this.inv.observer.subscribe(this);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 0.7;
    
        // Menambahkan gambar
        ImageIcon image = Utils.getImageIcon(barang.getPicturePath());
        JLabel imageLabel = new JLabel(image);
        imageLabel.setPreferredSize(new Dimension(200, 200));
        add(imageLabel, gbc);
    
        // Menambahkan label name, stock, dan price
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0.1;
        this.nameLabel = new JLabel(barang.getName());
        this.stockLabel = new JLabel("Stock: " + barang.getStock());
        this.priceLabel = new JLabel("Price: " + barang.getPrice());
        add(nameLabel, gbc);
        gbc.gridy++;
        add(stockLabel, gbc);
        gbc.gridy++;
        add(priceLabel, gbc);
    
        // Menambahkan container untuk tombol +, -, dan label quantity
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weighty = 0.1;
        JPanel quantityContainer = new JPanel();
        quantityContainer.setLayout(new BoxLayout(quantityContainer, BoxLayout.X_AXIS));
        minusButton = new QtyButton("-", barang.getID());
        minusButton.setPreferredSize(new Dimension(50, 50));
        minusButton.addActionListener(al);
        quantityContainer.add(minusButton);
        quantityLabel = new JLabel("0");
        quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        quantityLabel.setPreferredSize(new Dimension(50, 50));
        quantityContainer.add(quantityLabel);
        plusButton = new QtyButton("+", barang.getID());
        plusButton.setPreferredSize(new Dimension(50, 50));
        plusButton.addActionListener(al);
        quantityContainer.add(plusButton);
        add(quantityContainer, gbc);

        // Menambahkan listener untuk button -
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity = Integer.parseInt(quantityLabel.getText());
                if (quantity > 0) {
                    quantity--;
                    quantityLabel.setText(Integer.toString(quantity));
                    bill.editItemQuantity(barang.getID(), -1, inv);
                }
            }
        });

        // Menambahkan listener untuk button +
        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity = Integer.parseInt(quantityLabel.getText());
                int stock = barang.getStock();
                if (quantity < stock) {
                    quantity++;
                    quantityLabel.setText(Integer.toString(quantity));
                    bill.editItemQuantity(barang.getID(), 1, inv);
                }
            }
        });
    }
    
    public QtyButton getQtyButton() {
        return qtyButton;
    }

    public int getQuantity() {
        return Integer.parseInt(quantityLabel.getText());
    }

    public void setQuantity(int quantity) {
        quantityLabel.setText(Integer.toString(quantity));
    }

    public void update () {
        stockLabel.setText(this.barang.getName());
        stockLabel.setText("Stock: " + this.barang.getStock());
        priceLabel.setText("Price: " + this.barang.getPrice());
        this.revalidate();
        this.repaint();
    }
}
