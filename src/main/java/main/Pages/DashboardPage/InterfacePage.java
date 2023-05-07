package main.Pages.DashboardPage;

import javax.swing.JPanel;
import java.util.HashMap;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import javax.swing.JButton;

public interface InterfacePage {
    // Function to add new page selection to main
    public void addPage(String pagename, ImageIcon imageicon, JPanel panel);

    // Function to get data from inventory
    // public ArrayList<Tuple> getInventoryData();

    // public ArrayList getClientManagerData();
}
