package main.Pages.RegistrationPage;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import main.Client.*;

public class RegistrationPane extends JPanel implements ActionListener {

    private static final String memberType[] = {"1 - Member", "2 - VIP"};
    private ClientManager clientManager;
    private JLabel title, idLabel, nameLabel, phoneLabel, typeLabel;
    private JTextField nameField, phoneField;
    private JComboBox<String> regTypeOption;
    private JComboBox<Integer> idOption;
    private JButton submitButton;

    public RegistrationPane(ClientManager clientManager) {
        this.clientManager = clientManager;

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        this.title = new JLabel("Registration");
        this.title.setFont(new Font("Serif", Font.BOLD, 30));

        this.idLabel = new JLabel("ID                  : ");
        this.idLabel.setFont(new Font("Serif", Font.BOLD, 20));

        this.nameLabel = new JLabel("Name              : ");
        this.nameLabel.setFont(new Font("Serif", Font.BOLD, 20));

        this.phoneLabel = new JLabel("Phone             : ");
        this.phoneLabel.setFont(new Font("Serif", Font.BOLD, 20));

        this.typeLabel = new JLabel("Membership : ");
        this.typeLabel.setFont(new Font("Serif", Font.BOLD, 20));

        this.nameField = new JTextField();
        this.nameField.setPreferredSize(new Dimension(800, 25));
        this.nameField.setFont(new Font("Serif", Font.PLAIN, 20));

        this.phoneField = new JTextField();
        this.phoneField.setPreferredSize(new Dimension(800, 25)); 
        this.phoneField.setFont(new Font("Serif", Font.PLAIN, 20));

        this.regTypeOption = new JComboBox<>(memberType);
        this.regTypeOption.setPreferredSize(new Dimension(800, 25));
        this.regTypeOption.setFont(new Font("Serif", Font.BOLD, 17));

        this.idOption = new JComboBox<>(this.clientManager.getAllCustomerID());
        this.idOption.setPreferredSize(new Dimension(800, 25));
        this.idOption.setFont(new Font("Serif", Font.BOLD, 17));

        this.submitButton = new JButton("Submit");
        this.submitButton.setFont(new Font("Serif", Font.BOLD, 17));
        this.submitButton.addActionListener(this);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets.left = gbc.insets.right = 400;
        gbc.gridwidth = 2;
        this.add(this.title, gbc);

        gbc.insets.left = gbc.insets.right = 10;
        gbc.gridwidth = 1;
        gbc.gridy++;
        this.add(this.idLabel, gbc);
        gbc.gridy++;
        this.add(this.nameLabel, gbc);
        gbc.gridy++;
        this.add(this.phoneLabel, gbc);
        gbc.gridy++;
        this.add(this.typeLabel, gbc);

        gbc.gridx++;
        gbc.gridy = 1;
        this.add(this.idOption, gbc);
        gbc.gridy++;
        this.add(this.nameField, gbc);
        gbc.gridy++;
        this.add(this.phoneField, gbc);
        gbc.gridy++;
        this.add(this.regTypeOption, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets.left = 400;
        gbc.insets.right = 400;
        this.add(this.submitButton, gbc);
    }

    public void reloadComponent() {
        this.idOption.removeItem(this.idOption.getSelectedItem());
        this.nameField.setText("");
        this.phoneField.setText("");
        this.idOption.revalidate();
        this.idOption.repaint();
        this.revalidate();
        this.repaint();
    }

    public void addNewMember() {
        Integer membership = Character.getNumericValue(((String) this.regTypeOption.getSelectedItem()).charAt(0));
        this.clientManager.promoteMembership(membership, ((Integer) this.idOption.getSelectedItem()), this.nameField.getText().trim(), this.phoneField.getText().trim());
        this.reloadComponent();
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("Submit")) {
            if (this.nameField.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Mohon masukkan nama yang valid");
            }
            else if (this.phoneField.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Mohon masukkan nomor telepon yang valid");
            }
            else if (this.idOption.getItemCount() == 0) {
                JOptionPane.showMessageDialog(null, "Tidak ada pelanggan lagi untuk dijadikan member atau VIP");
            }
            else {
                this.addNewMember();
            }
        }
    }
}
