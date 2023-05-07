package main.Pages.SettingPage;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;  

public class SettingPage extends JPanel {
    private JComboBox<String> comboBox;
    private JFileChooser fileChooser;
    private JButton saveButton, cancelButton, deleteButton;
    private JPanel panel;
    private JLabel label;

    public SettingPage() {
        this.setSize(400, 200);
    
        // create main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // add padding
    
        // create label
        JLabel label = new JLabel("Select data storage format: ");
        mainPanel.add(label, gbc);
    
        // create combo box
        String[] formats = {"XML", "JSON", "Object"};
        JComboBox<String> comboBox = new JComboBox<String>(formats);
        gbc.gridx = 1;
        mainPanel.add(comboBox, gbc);
    
        // create file chooser
        JFileChooser fileChooser = new JFileChooser();
    
        // create buttons
        JButton saveButton = new JButton("Save");
        gbc.gridx = 0;
        gbc.gridy = 1;
        // gbc.weightx = 1.0;
        // gbc.fill = GridBagConstraints.HORIZONTAL; // set horizontal fill
        gbc.anchor = GridBagConstraints.LINE_START; // set anchor to left
        gbc.insets = new Insets(0, 10, 10, 5); // add left and bottom padding
        mainPanel.add(saveButton, gbc);
    
        JButton cancelButton = new JButton("Cancel");
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END; // set anchor to right
        gbc.insets = new Insets(0, 5, 10, 10); // add right and bottom padding
        mainPanel.add(cancelButton, gbc);
    
        JButton deleteButton = new JButton("Delete Plugin");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        // gbc.weightx = 1.0; // set horizontal weight to fill remaining space
        // gbc.fill = GridBagConstraints.HORIZONTAL; // set horizontal fill
        gbc.anchor = GridBagConstraints.CENTER; // set anchor to center
        gbc.insets = new Insets(0, 10, 10, 10); // add bottom padding
        mainPanel.add(deleteButton, gbc);
    
        // add main panel to frame
        this.add(mainPanel);
    
        // add action listener for save button
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // get selected format
                String selectedFormat = (String) comboBox.getSelectedItem();
    
                // display file chooser dialog
                int returnValue = fileChooser.showDialog(mainPanel, "Choose");
    
                // if user chooses a file
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // get selected file
                    File file = fileChooser.getSelectedFile();
    
                    // save selected format and file path to config file
                    saveConfig(selectedFormat, file.getPath());
                }
            }
        });
    
        // add action listener for cancel button
        /* cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }); */
    
        // add action listener for delete button
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // remove plugin
                removePlugin();
            }
        });
    
        // display frame
        this.setVisible(true);
    }
    
    private void saveConfig(String format, String path) {
        // save format and path to config file
        try {
            FileWriter writer = new FileWriter("config.txt");
            writer.write(format + "\n" + path);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void removePlugin() {
        // remove plugin
        // code for removing plugin
    }
}
