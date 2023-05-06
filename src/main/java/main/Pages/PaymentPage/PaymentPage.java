package main.Pages.PaymentPage;
import java.awt.*;

import javax.swing.*;

import main.Bill.*;  

public class PaymentPage extends JFrame {
    private JButton cancelButton = new JButton("Cancel");
    private JButton saveButton = new JButton("Save");
    
    final int WIDTH = 700, HEIGHT = 400;

    public PaymentPage(Bill bill) {
        
        // Create the table with some sample data
        Object[][] data = {
            {"Item 1", "$10.00"},
            {"Item 2", "$20.00"},
            {"Item 3", "$30.00"}
        };
        JTable table = new JTable(data, new String[] {"Item", "Price"});

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(this.cancelButton);
        buttonPanel.add(this.saveButton);

        // Create a panel to hold the table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        // Create a panel to hold both the button panel and the table panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(buttonPanel, gbc);
        
        gbc.gridy = 1;
        mainPanel.add(tablePanel, gbc);
        
        // Set up the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Payment Page");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        
        // Add the main panel to the content pane
        Container contentPane = getContentPane();
        contentPane.add(mainPanel);
    }
}