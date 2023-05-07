package main.Plugin;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;
import org.javatuples.*;
import java.lang.Thread;
import java.util.ArrayList;

import java.util.HashMap;

public class Plugin2 extends BasePlugin implements PluginInterface, Runnable {
    private ChartPanel panel1, panel2;

    public Plugin2(InterfacePage ip) {
        super(ip);
        this.panel1 = new ChartPanel(null);
        this.panel2 = new ChartPanel(null);
    }

    public void onLoad() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        JPanel pluginPage = ip.getPluginPage();
        pluginPage.setLayout(new GridBagLayout());
        pluginPage.add(panel1, gbc);
        gbc.gridx++;
        pluginPage.add(panel2, gbc);
        Thread makeChart = new Thread(this);
        makeChart.start();
    }

    public void run() {
        while (true) {
            makeCategoryChart();
            makeMembershipChart();
            ip.getPluginPage().revalidate();
            ip.getPluginPage().repaint();
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void makeCategoryChart() {
        DefaultPieDataset pds = new DefaultPieDataset();
        HashMap<String,Integer> categorySum = new HashMap<>();
        for (Septet<Integer, String, Integer, Double, Double, String, String> data : ip.getInventoryData()) {
            categorySum.merge(data.getValue5(),1,Integer::sum);
        }
        for (String k : categorySum.keySet()) {
            pds.setValue(k, categorySum.get(k));
        }
        JFreeChart pieChart = ChartFactory.createPieChart(      
          "Number of items in category",   // chart title 
          pds,          // data    
          true,             // include legend   
          true, 
          false);
        this.panel1.setChart(pieChart);
    }

    public void makeMembershipChart() {
        DefaultPieDataset pds = new DefaultPieDataset();
        HashMap<String,ArrayList<Quintet<Integer, String, String, Integer, Boolean>>> data = ip.getClientManagerData();
        HashMap<String,Integer> membershipSum = new HashMap<>();
        membershipSum.put("Customer", data.get("Customer").size());
        membershipSum.put("Member", data.get("Member").size());
        membershipSum.put("VIP", data.get("VIP").size());
        for (String k : membershipSum.keySet()) {
            pds.setValue(k, membershipSum.get(k));
        }
        JFreeChart pieChart = ChartFactory.createPieChart(      
          "Number of people in each membership category",   // chart title 
          pds,          // data    
          true,             // include legend   
          true, 
          false);
          this.panel2.setChart(pieChart);
    }
}