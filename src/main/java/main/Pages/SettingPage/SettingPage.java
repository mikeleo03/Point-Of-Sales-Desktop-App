package main.Pages.SettingPage;

import main.Pages.DashboardPage.MainPage;
import main.Plugin.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;  
import javax.swing.filechooser.FileNameExtensionFilter;

public class SettingPage extends JPanel {
    private JComboBox<String> comboBox;
    private JFileChooser fileChooser;
    private JButton openButton, cancelButton, deleteButton;
    private JPanel panel;
    private JLabel label;
    private String jarPath;

    public SettingPage(MainPage mp) {
        this.setSize(400, 200);
        this.jarPath = "";
    
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
        File currDir = new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(currDir);
        fileChooser.setFileFilter(new FileNameExtensionFilter("JAR files", "jar"));
    
        // create buttons
        JButton openButton = new JButton("Open JAR");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START; // set anchor to left
        gbc.insets = new Insets(0, 10, 10, 5); // add left and bottom padding
        mainPanel.add(openButton, gbc);
    
        JLabel pathLabel = new JLabel();
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 5, 10, 10);
        mainPanel.add(pathLabel, gbc);
    
        JButton cancelButton = new JButton("Cancel");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 10, 10, 5);
        mainPanel.add(cancelButton, gbc);
    
        JButton deleteButton = new JButton("Delete Plugin");
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 5, 10, 10);
        mainPanel.add(deleteButton, gbc);
    
        // add main panel to frame
        this.add(mainPanel);
    
        // add action listener for open button
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // display file chooser dialog
                int returnValue = fileChooser.showDialog(mainPanel, "Open");
    
                // if user chooses a file
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // get selected file
                    File file = fileChooser.getSelectedFile();
    
                    // check if the selected file has the extension .jar
                    if (file.getName().toLowerCase().endsWith(".jar")) {
                        // display selected file path
                        pathLabel.setText(file.getPath());
                        jarPath = file.getPath();
                        PluginLoader pl = new PluginLoader();
                        try {
                            pl.loadPlugin(jarPath, mp);
                        } catch (Exception err) {
                            System.out.println(err.getMessage());
                        }
                    } else {
                        // display an error message
                        JOptionPane.showMessageDialog(mainPanel, "Please select a JAR file.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    
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

    private void removePlugin() {
        // remove plugin
        // code for removing plugin
    }
}
