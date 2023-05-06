package main.Pages.DashboardPage;

import java.awt.*;

import javax.swing.*;

public class DashboardPage extends JFrame{
    private LeftPanel lp;
    private MainPanel mp;
    
    public DashboardPage() {
        setTitle("CPKBOS ~ Point Of Sale");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        setSize(1500, 800);
        gbc.weightx = 0.15;
        gbc.weighty = 0.15;
        gbc.fill = GridBagConstraints.BOTH;
        this.lp = new LeftPanel();
        add(this.lp, gbc);
        gbc.gridx++;
        
        gbc.weightx = 0.85;
        gbc.weighty = 0.85;
        this.mp = new MainPanel();
        add(this.mp, gbc);
        setVisible(true);
    }

}
