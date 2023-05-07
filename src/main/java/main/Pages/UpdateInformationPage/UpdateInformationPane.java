package main.Pages.UpdateInformationPage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import main.Client.*;

public class UpdateInformationPane extends JPanel implements ActionListener {

    private static final String memberType[] = {"1 - Member", "2 - VIP"};
    private static final String activeType[] = {"Active", "Not Active"};
    private ClientManager clientManager;
    private JLabel title, idLabel, nameLabel, phoneLabel, typeLabel, activeLabel;
    private JTextField nameField, phoneField;
    private JComboBox<String> regTypeOption, activeOption;
    private JComboBox<Integer> idOption;
    private JButton updateButton;

    public UpdateInformationPane(ClientManager clientManager) {
        this.clientManager = clientManager;

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        this.title = new JLabel("Update Information");
        this.title.setFont(new Font("Serif", Font.BOLD, 30));

        this.idLabel = new JLabel("ID                  : ");
        this.idLabel.setFont(new Font("Serif", Font.BOLD, 20));

        this.nameLabel = new JLabel("Name              : ");
        this.nameLabel.setFont(new Font("Serif", Font.BOLD, 20));

        this.phoneLabel = new JLabel("Phone             : ");
        this.phoneLabel.setFont(new Font("Serif", Font.BOLD, 20));

        this.typeLabel = new JLabel("Membership : ");
        this.typeLabel.setFont(new Font("Serif", Font.BOLD, 20));

        this.activeLabel = new JLabel("Activity  : ");
        this.activeLabel.setFont(new Font("Serif", Font.BOLD, 20));

        this.nameField = new JTextField();
        this.nameField.setPreferredSize(new Dimension(1000, 25));
        this.nameField.setFont(new Font("Serif", Font.PLAIN, 20));

        this.phoneField = new JTextField();
        this.phoneField.setPreferredSize(new Dimension(1000, 25)); 
        this.phoneField.setFont(new Font("Serif", Font.PLAIN, 20));

        this.regTypeOption = new JComboBox<>(memberType);
        this.regTypeOption.setPreferredSize(new Dimension(1000, 25));
        this.regTypeOption.setFont(new Font("Serif", Font.BOLD, 17));

        this.idOption = new JComboBox<>(this.clientManager.getAllNonCustomerID());
        this.idOption.setActionCommand("ID Change");
        this.idOption.setPreferredSize(new Dimension(1000, 25));
        this.idOption.setFont(new Font("Serif", Font.BOLD, 17));
        this.idOption.addActionListener(this);

        this.activeOption = new JComboBox<>(activeType);
        this.activeOption.setPreferredSize(new Dimension(1000, 25));
        this.activeOption.setFont(new Font("Serif", Font.BOLD, 17));

        this.updateButton = new JButton("Update");
        this.updateButton.setFont(new Font("Serif", Font.BOLD, 17));
        this.updateButton.addActionListener(this);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets.left = gbc.insets.right = 500;
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
        gbc.gridy++;
        this.add(this.activeLabel, gbc);

        gbc.gridx++;
        gbc.gridy = 1;
        this.add(this.idOption, gbc);
        gbc.gridy++;
        this.add(this.nameField, gbc);
        gbc.gridy++;
        this.add(this.phoneField, gbc);
        gbc.gridy++;
        this.add(this.regTypeOption, gbc);
        gbc.gridy++;
        this.add(this.activeOption, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets.left = 400;
        gbc.insets.right = 400;
        this.add(this.updateButton, gbc);
        this.reloadComponent();
    }

    public void reloadComponent() {
        Integer idSelected = (Integer) this.idOption.getSelectedItem();
        if (idSelected != null) {
            this.nameField.setText(this.clientManager.getClientName(idSelected));
            this.phoneField.setText(this.clientManager.getClientPhone(idSelected));
            Integer clientType = this.clientManager.getClientType(idSelected);
            Boolean active = this.clientManager.getClientActivity(idSelected);
            this.regTypeOption.setSelectedIndex(clientType);
            this.activeOption.setSelectedIndex(active ? 0 : 1);
        }
    }

    public void updateMember() {
        Integer membership = Character.getNumericValue(((String) this.activeOption.getSelectedItem()).charAt(0));
        Integer idSelected = (Integer) this.idOption.getSelectedItem();
        Boolean isActive = ((String) this.activeOption.getSelectedItem()).equals("Active");
        this.clientManager.changeClientStatus(idSelected, this.nameField.getText().trim(), this.phoneField.getText().trim(), isActive, membership);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ID Change")) {
            this.reloadComponent();
        }
        else if (e.getActionCommand().equals("Update")) {
            if (this.nameField.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Mohon masukkan nama yang valid");
            }
            else if (this.phoneField.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Mohon masukkan nomor telepon yang valid");
            }
            else if (this.idOption.getItemCount() == 0) {
                JOptionPane.showMessageDialog(null, "Belum ada member atau VIP yang terdaftar");
            }
            else {
                this.updateMember();
            }
        }
    }
}
