package main.Pages.DashboardPage;

import java.awt.*;
import javax.swing.*;

public class MainPanel extends JPanel {
    private DeveloperPanel dev;
    private TimePanel time;
    
    public MainPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        setSize(400, 400);
        setBackground(new Color(0x1a1e3b));
        this.time = new TimePanel();
        // gbc.weightx = 0.15;
        this.time.setPreferredSize(new Dimension(4000, 400));
        gbc.fill = GridBagConstraints.BOTH;
        add(this.time, gbc);

        // Spacing
        JLabel empty = new JLabel(" ");
        gbc.ipady = 50;
        gbc.gridy++;
        add(empty, gbc);

        gbc.gridy++;
        this.dev = new DeveloperPanel();

        add(this.dev, gbc);
    }
}
