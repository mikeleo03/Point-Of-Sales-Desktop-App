package main.Plugin;

import main.Barang.*;

import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;
import java.lang.Thread;

import java.io.File;
import java.util.HashMap;

public class Plugin2 extends BasePlugin implements PluginInterface, Runnable {
    public void onLoad() {
        
    }

    public void run() {
        makeCategoryChart(null);
    }

    public void makeCategoryChart(Inventory inv) {
        DefaultPieDataset pds = new DefaultPieDataset();
        HashMap<String,Integer> categorySum = new HashMap<>();
        for (Barang b : inv.getListBarang()) {
            categorySum.merge(b.getCategory(),1,Integer::sum);
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
        ChartPanel cp = new ChartPanel(pieChart);
        cp.setSize(560,400);
        File out = new File("Category Count.png");
        try {
            ChartUtils.saveChartAsPNG(out, pieChart, cp.getWidth(), cp.getHeight());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}