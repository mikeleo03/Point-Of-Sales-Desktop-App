package main.Pages.DashboardPage;

import javax.swing.JPanel;
import java.util.HashMap;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import javax.swing.JButton;
import org.javatuples.*;

public interface InterfacePage {
    // Function to add new page selection to main
    public void addPage(String pagename, ImageIcon imageicon, JPanel panel);

    // Function to get data from inventory
    public ArrayList<Septet<Integer, String, Integer, Double, Double, String, String>> getInventoryData();

    // Function to get data from client manager
    public HashMap<String,ArrayList<Quintet<Integer, String, String, Integer, Boolean>>> getClientManagerData();
}
