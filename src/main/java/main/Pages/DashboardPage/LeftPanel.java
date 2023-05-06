package main.Pages.DashboardPage;

import java.awt.*;
import javax.swing.*;

public class LeftPanel extends JPanel {
    public LeftPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        setSize(250, 800);
        setBackground(new Color(0x1a1e3b));

        // Set Icon
        ImageIcon originalIcon = new ImageIcon("../img/icon/logo.png");
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(150, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel logo = new JLabel(scaledIcon);
        gbc.insets = new Insets(5, 5, 0, 5);
        add(logo, gbc);
        JLabel POS = new JLabel("Point Of Sale");
        POS.setForeground(new Color(0x9c9c9c));
        gbc.gridy++;
        gbc.ipady = 30;
        add(POS, gbc);

        // Set Border
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.WHITE)); 

        // Set Page Info

        // Spacing
        JLabel empty = new JLabel(" ");
        gbc.ipady = 30;
        gbc.gridy++;
        add(empty, gbc);
        
        Font fontPage = new Font("Arial", Font.BOLD, 15);
        
        // JButton DashButton = new JButton("../img/icon/home.png");
        gbc.gridx++;
        
        JLabel Dash = new JLabel("Dashboard");
        Dash.setFont(fontPage);
        Dash.setForeground(new Color(0xFFFFFF));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.ipady = 25;
        add(Dash, gbc);

        JLabel Registration = new JLabel("Registration");
        Registration.setFont(fontPage);
        Registration.setForeground(new Color(0x9c9c9c));
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(Registration, gbc);

        JLabel Customers = new JLabel("Customers");
        Customers.setFont(fontPage);
        Customers.setForeground(new Color(0x9c9c9c));
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(Customers, gbc);

        JLabel History = new JLabel("History");
        History.setFont(fontPage);
        History.setForeground(new Color(0x9c9c9c));
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(History, gbc);

        JLabel Payment = new JLabel("Payment");
        Payment.setFont(fontPage);
        Payment.setForeground(new Color(0x9c9c9c));
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(Payment, gbc);

        JLabel Inventory = new JLabel("Inventory");
        Inventory.setFont(fontPage);
        Inventory.setForeground(new Color(0x9c9c9c));
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(Inventory, gbc);

        JLabel Sale = new JLabel("Sales");
        Sale.setFont(fontPage);
        Sale.setForeground(new Color(0x9c9c9c));
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(Sale, gbc);

        JLabel Stock = new JLabel("Stocks");
        Stock.setFont(fontPage);
        Stock.setForeground(new Color(0x9c9c9c));
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(Stock, gbc);

        JLabel Setting = new JLabel("Settings");
        Setting.setFont(fontPage);
        Setting.setForeground(new Color(0x9c9c9c));
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(Setting, gbc);

        JLabel Plugin = new JLabel("Plugin");
        Plugin.setFont(fontPage);
        Plugin.setForeground(new Color(0x9c9c9c));
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(Plugin, gbc);
    }
}
