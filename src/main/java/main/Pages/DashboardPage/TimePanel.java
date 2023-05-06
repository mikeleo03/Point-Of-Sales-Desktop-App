package main.Pages.DashboardPage;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

public class TimePanel extends JPanel {
    private JLabel dateInfo;
    private JLabel timeInfo;
    private DateTimeFormatter dtf1;
    private DateTimeFormatter dtf2;
    private Font dateFont;
    private Font timeFont;
    private Timer timer;

    public TimePanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        setSize(2000, 400);
        setBackground(new Color(0xFFC107));

        this.dtf1 = DateTimeFormatter.ofPattern("EEE, d MMMM yyyy");  
        this.dtf2 = DateTimeFormatter.ofPattern(" HH:mm:ss");  

        // Set Time Info
        this.dateFont = new Font("Arial", Font.PLAIN, 15);
        this.timeFont = new Font("Arial", Font.BOLD, 38);
        this.dateInfo = new JLabel(this.dtf1.format(LocalDateTime.now()));
        this.dateInfo.setForeground(Color.WHITE);
        this.dateInfo.setFont(this.dateFont);
        gbc.insets = new Insets(20, 20, 0, 20); // add top padding of 10 pixels
        // this.dateInfo.setPreferredSize(new Dimension(4000, 400));
        add(this.dateInfo, gbc);
        gbc.gridy++;
        this.timeInfo = new JLabel(this.dtf2.format(LocalDateTime.now()));
        this.timeInfo.setFont(this.timeFont);
        this.timeInfo.setForeground(Color.WHITE);
        gbc.insets = new Insets(0, 20, 20, 20); // add bottom padding of 20 pixels
        // this.timeInfo.setPreferredSize(new Dimension(4000, 400));
        add(this.timeInfo, gbc);

        // Update the time info every second using a Timer
        this.timer = new Timer(1000, e -> updateTimeInfo());
        this.timer.start();
    }

    public void updateTimeInfo() {
        LocalDateTime now = LocalDateTime.now();
        dateInfo.setText(dtf1.format(now));
        timeInfo.setText(dtf2.format(now));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int arc = 20; 
        RoundRectangle2D roundedPanel = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arc, arc);
        g2.setClip(roundedPanel);
        super.paintComponent(g2);
    }
}
