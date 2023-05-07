package main.Pages.UpdateInformationPage;

import java.awt.*;
import javax.swing.*;
import main.Client.*;

public class UpdateInformationPage extends JFrame {
    private UpdateInformationPane pane;
    private final Integer width = 700;
    private final Integer height = 400;

    public UpdateInformationPage(ClientManager clientManager) {
        this.setTitle("Update Information");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(this.width, this.height);
        this.pane = new UpdateInformationPane(clientManager);
        this.add(this.pane);
        this.setVisible(true);
    }
}