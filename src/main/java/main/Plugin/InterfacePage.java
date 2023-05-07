package main.Plugin;

import javax.swing.JPanel;
import java.util.HashMap;
import java.util.ArrayList;
import org.javatuples.*;

public interface InterfacePage {
    // Function to edit plugin page
    public JPanel getPluginPage();

    // Function to get data from inventory
    public ArrayList<Septet<Integer, String, Integer, Double, Double, String, String>> getInventoryData();

    // Function to get data from client manager
    public HashMap<String,ArrayList<Quintet<Integer, String, String, Integer, Boolean>>> getClientManagerData();
}
