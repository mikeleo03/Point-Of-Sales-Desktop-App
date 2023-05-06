package main.Pages.RegistrationPage;

import java.awt.*;
import javax.swing.*;

import main.Client.*;

public class RegistrationPage extends JFrame{
    private RegistrationPane pane;
    private final Integer width = 700;
    private final Integer height = 400;

    public RegistrationPage(ClientManager clientManager) {
        this.setTitle("Registration");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(this.width, this.height);
        this.pane = new RegistrationPane(clientManager);
        this.add(this.pane);
        this.setVisible(true);
    }
}
