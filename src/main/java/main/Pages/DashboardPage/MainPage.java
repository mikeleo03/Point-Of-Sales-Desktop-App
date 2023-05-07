package main.Pages.DashboardPage;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import main.Barang.*;
import main.Transaksi.*;
import main.Bill.*;
import main.Client.ClientManager;
import main.Pages.InventoryPage.*;
import main.Pages.PaymentPage.*;
import main.Pages.RegistrationPage.*;
import main.Pages.UpdateInformationPage.*;
import main.Pages.SettingPage.*;
import main.Pages.HistoryPage.*;

public class MainPage extends JFrame implements InterfacePage {
    private JTabbedPane tabbedPane;
    private JPanel leftPanel;
    private Inventory inv;

    public MainPage() {
        // set the layout of the JFrame to BorderLayout
        setLayout(new BorderLayout());

        ClientManager clientmanager = new ClientManager();

        Barang nasgor = new Barang("Nasi Goreng", 10, 11000.00, 13000.00, "Makanan", "../");
        Barang mie = new Barang("Mie Goreng", 10, 9000.00, 7000.00, "Makanan", "../");
        Barang eskrim = new Barang("Ice Cream", 12, 5000.00, 7000.00, "Makanan", "../");
        
        inv = new Inventory();
        inv.addBarang(mie);
        inv.addBarang(nasgor);
        inv.addBarang(eskrim);

        DetailTransaksi detail = new DetailTransaksi();
        detail.editBarang(nasgor, 3, inv);
        detail.editBarang(mie, 5, inv);
        detail.editBarang(nasgor, 5, inv);
        detail.editBarang(mie, 5, inv);
        detail.deleteDetail(eskrim);
        Bill bill = new Bill(2000, detail);
        FixedBillManager fixedbillmanager = new FixedBillManager();

        // create the LeftPanel
        leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));
        leftPanel.setBackground(new Color(0x1a1e3b));

        // create the buttons with icons
        ImageIcon[] buttonIcons = {
                new ImageIcon("images.png"), new ImageIcon("registration.png"), new ImageIcon("customers.png"),
                new ImageIcon("history.png"), new ImageIcon("payment.png"), new ImageIcon("inventory.png"),
                new ImageIcon("sales.png"), new ImageIcon("stocks.png"), new ImageIcon("settings.png"), new ImageIcon("plugin.png")
        };
        
        // Add list of pages
        addPage("Dashboard", buttonIcons[0], new MainPanel());
        addPage("Inventory", buttonIcons[0], new InvPane(inv));
        addPage("Payment", buttonIcons[0], new PaymentPage(bill, inv, fixedbillmanager));
        addPage("Registration", buttonIcons[0], new InvPane(inv));
        addPage("Customers", buttonIcons[0], new UpdateInformationPane(clientmanager));
        addPage("Settings", buttonIcons[0], new SettingPage());
        addPage("History", buttonIcons[0], new HistoryPage(fixedbillmanager));

        // create the JTabbedPane
        tabbedPane = new JTabbedPane();

        // add the LeftPanel and JTabbedPane to the JFrame
        add(leftPanel, BorderLayout.WEST);
        add(tabbedPane, BorderLayout.CENTER);

        // set JFrame properties
        setTitle("CPKBOS - Point of Sales");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void addPage(String pagename, ImageIcon imageicon, JPanel panel) {
        JButton button = new JButton(pagename, imageicon);
        button.setPreferredSize(new Dimension(180, 50));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setForeground(Color.WHITE);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // create a new panel and add it to the tabbedPane
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                // create a close button and add it to the tab
                JButton closeButton = new JButton("X");
                // closeButton.setFont(new Font("Arial", Font.PLAIN, 5));
                closeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // remove the tab
                        tabbedPane.removeTabAt(tabbedPane.indexOfComponent(panel));
                    }
                });
                // closeButton.setPreferredSize(new Dimension(30,20));
                JPanel tabPanel = new JPanel(new BorderLayout(3,3));
                JLabel tabTitle = new JLabel(pagename);
                tabPanel.add(tabTitle, BorderLayout.WEST);
                tabPanel.add(closeButton, BorderLayout.EAST);

                // add the panel to the tabbedPane
                tabbedPane.addTab(pagename, panel);
                tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, tabPanel);
            }
        });
        this.leftPanel.add(button);
        this.leftPanel.revalidate();
        this.leftPanel.repaint();
    }

    // public ArrayList<Septet<Integer, String, Integer, Double, Double, String, String>> getInventoryData() {
    //     ArrayList<Septet> data = new ArrayList<>();
    //     for (Barang b : this.inv.getListBarang()) {
    //         Septet<Integer, String, Integer, Double, Double, String, String> currData = 
    //         new Septet<Integer, String, Integer, Double, Double, String, String> (b.getID(), b.getName(),
    //         b.getStock(), b.getPrice(), b.getBuyPrice(), b.getCategory(), b.getPicturePath());
    //     }
    // }
}
