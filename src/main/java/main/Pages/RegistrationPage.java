package main.Pages;

import java.awt.*;
import javax.swing.*;

class RegistrationPane extends JPanel {
    public RegistrationPane() {
        String memberType[] = {"1 - Member", "2 - VIP"};
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        this.setLayout(new GridBagLayout());
        
        JLabel nameLabel = new JLabel("Name              : ");
        nameLabel.setFont(new Font("Serif", Font.BOLD, 20));

        JLabel phoneLabel = new JLabel("Phone             : ");
        phoneLabel.setFont(new Font("Serif", Font.BOLD, 20));

        JLabel memberLabel = new JLabel("Membership : ");
        memberLabel.setFont(new Font("Serif", Font.BOLD, 20));

        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(1000, 25));
        nameField.setFont(new Font("Serif", Font.PLAIN, 20));

        JTextField phoneField = new JTextField();
        phoneField.setPreferredSize(new Dimension(1000, 25)); 
        phoneField.setFont(new Font("Serif", Font.PLAIN, 20));

        JComboBox<String> regTypeOpt = new JComboBox<>(memberType);
        regTypeOpt.setPreferredSize(new Dimension(1000, 25));
        regTypeOpt.setFont(new Font("Serif", Font.BOLD, 17));

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Serif", Font.BOLD, 17));

        this.add(nameLabel, gbc);
        gbc.gridy++;
        this.add(phoneLabel, gbc);
        gbc.gridy++;
        this.add(memberLabel, gbc);

        gbc.gridx++;
        gbc.gridy = 0;
        this.add(nameField, gbc);
        gbc.gridy++;
        this.add(phoneField, gbc);
        gbc.gridy++;
        this.add(regTypeOpt, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets.left = 400;
        gbc.insets.right = 400;
        this.add(submitButton, gbc);
    }
}
public class RegistrationPage {
    private JFrame pageFrame;

    public RegistrationPage() {
        this.pageFrame = new JFrame("Registration");
        this.pageFrame.setLayout(new BorderLayout());
        this.pageFrame.add(new RegistrationPane());
        this.pageFrame.pack();
        //this.pageFrame.setLocationRelativeTo(null);
        this.pageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pageFrame.setSize(1440, 810);
        this.pageFrame.setVisible(true);
    }
}
