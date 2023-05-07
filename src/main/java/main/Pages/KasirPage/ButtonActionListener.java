package main.Pages.KasirPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ButtonActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Save Bill")) {
                System.out.println("Save");
            } else if (button.getText().equals("Bayar")) {
                System.out.println("Bayar");
            }
        }
    }
}
