package main.Pages.PaymentPage;
import java.awt.*;
import javax.swing.*;

import main.Bill.*; 

public class PaymentInfo extends JFrame {
    public PaymentInfo (Bill bill) {
        // Create the data for the table
        Object[][] data = {
            {"John Doe", 31, "Male"},
            {"Jane Doe", 29, "Female"},
            {"Bob Smith", 45, "Male"}
        };
        
        // Create the table using the data
        JTable table = new JTable(data, new String[] {"Name", "Age", "Gender"});
        
        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Add the scroll pane to a panel
        JPanel panel = new JPanel();
        panel.add(scrollPane);
        
        // Set up the frame
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        
        // Add the panel to the frame
        add(panel);
    }
}