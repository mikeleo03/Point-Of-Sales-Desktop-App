package main.Pages.DashboardPage;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

public class DeveloperPanel extends JPanel {
    public DeveloperPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        setSize(400, 400);
        setBackground(new Color(0x333554));

        // Set Developer Info
        Font devTitle = new Font("Arial", Font.BOLD, 18);
        Font devFont = new Font("Arial", Font.PLAIN, 15);
        JLabel devInfo = new JLabel("Developer : ");
        devInfo.setForeground(Color.WHITE);
        devInfo.setFont(devTitle);
        gbc.gridy++;
        gbc.insets = new Insets(20, 20, 10, 20);
        add(devInfo, gbc);

        JLabel dev1 = new JLabel("13521062 ~ Go Dillon Audris ");
        dev1.setForeground(Color.WHITE);
        dev1.setFont(devFont);
        gbc.gridy++;
        gbc.insets = new Insets(5, 20, 0, 20);
        add(dev1, gbc);

        JLabel dev2 = new JLabel("13521084 ~ Austin Gabriel Pardosi ");
        dev2.setForeground(Color.WHITE);
        dev2.setFont(devFont);
        gbc.gridy++;
        add(dev2, gbc);

        JLabel dev3 = new JLabel("13521108 ~ Michael Leon Putra Widhi ");
        dev3.setForeground(Color.WHITE);
        dev3.setFont(devFont);
        gbc.gridy++;
        add(dev3, gbc);

        JLabel dev4 = new JLabel("13521160 ~ M. Dimas Sakti Widyatmaja ");
        dev4.setForeground(Color.WHITE);
        dev4.setFont(devFont);
        gbc.gridy++;
        add(dev4, gbc);
        
        JLabel dev5 = new JLabel("13521172 ~ Nathan Tenka ");
        dev5.setForeground(Color.WHITE);
        dev5.setFont(devFont);
        gbc.gridy++;
        gbc.insets = new Insets(5, 20, 20, 20);
        add(dev5, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int arc = 20; // set the arc size as desired
        RoundRectangle2D roundedPanel = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arc, arc);
        g2.setClip(roundedPanel);
        super.paintComponent(g2);
    }
}
