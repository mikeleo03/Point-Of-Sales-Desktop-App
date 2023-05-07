package main.Pages.DashboardPage;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.javatuples.*;
import java.awt.*;
import javax.swing.*;

import main.Client.*;
import main.Barang.*;
import main.Transaksi.*;
import main.Bill.*;
import main.DataStore.*;
import main.Observer.*;
import main.Pages.InventoryPage.*;
import main.Pages.PaymentPage.*;
import main.Pages.RegistrationPage.*;
import main.Pages.UpdateInformationPage.*;
import main.Plugin.InterfacePage;
import main.Pages.SettingPage.*;
import main.Pages.HistoryPage.*;
import main.Pages.PluginPage.*;
import main.Pages.KasirPage.*;

public class MainPage extends JFrame implements InterfacePage, Subscriber {
    private JTabbedPane tabbedPane;
    private JPanel leftPanel;

    private DataStoreAdapter dataStoreAdapter;
    private ClientManager clientManager;
    private Inventory inv;
    private FixedBillManager fixedbillmanager;
    private BillManager billManager;
    private PluginPanel pluginPane;

    public MainPage() {
        this.dataStoreAdapter = new JSONDataStoreAdapter();
        this.clientManager = this.dataStoreAdapter.readClientManager();
        this.billManager = new BillManager();

        this.clientManager.observer.subscribe(this);

        // set the layout of the JFrame to BorderLayout
        setLayout(new BorderLayout());

        clientManager = new ClientManager();
        pluginPane = new PluginPanel();

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
        // Bill bill = new Bill(2000, detail);

        // create the LeftPanel
        leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));
        leftPanel.setBackground(new Color(0x1a1e3b));
        ImageIcon logo = new ImageIcon(new ImageIcon("../img/icon/logo.png").getImage().getScaledInstance(150, 30, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));
        leftPanel.add(logoLabel);
        
        JLabel POS = new JLabel("Point Of Sale");
        POS.setForeground(new Color(0x9c9c9c));
        POS.setBorder(BorderFactory.createEmptyBorder(5, 50, 20, 0));
        leftPanel.add(POS);

        // create the buttons with icons
        String[] buttonNames = {"Dashboard", "Registration", "Customers", "History", "Payment", 
                                "Inventory", "Sales", "Stocks", "Settings", "Plugin"};
        ImageIcon[] buttonIcons = {
            new ImageIcon(new ImageIcon("../img/icon/home.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("../img/icon/registration.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("../img/icon/customer.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("../img/icon/history.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("../img/icon/payment.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("../img/icon/inventory.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("../img/icon/sales.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("../img/icon/stock.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("../img/icon/settings.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("../img/icon/plugin.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))
        };
        
        JButton[] buttons = new JButton[buttonNames.length];
        for (int i = 0; i < buttonNames.length; i++) {
            final int index = i;
            buttons[i] = new JButton(buttonNames[i], buttonIcons[i]);
            buttons[i].setPreferredSize(new Dimension(180, 50));
            buttons[i].setBorder(BorderFactory.createEmptyBorder());
            buttons[i].setContentAreaFilled(false);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].addMouseListener (new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    setBackground(Color.GRAY);
                }
                public void mouseExited(MouseEvent e) {
                    setBackground(new Color(0x1a1e3b));
                }
            });
            buttons[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // create a new panel and add it to the tabbedPane
                    JPanel newPanel;
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    if (buttonNames[index].equals("Dashboard")) {
                        newPanel = new MainPanel();
                    } else if (buttonNames[index].equals("Inventory")) {
                        newPanel = new InvPane(inv);
                    } else if (buttonNames[index].equals("Payment")) {
                        newPanel = new PaymentPage(billManager, inv, fixedbillmanager);
                    } else if (buttonNames[index].equals("Registration")) {
                        newPanel = new RegistrationPane(clientManager);
                    } else if (buttonNames[index].equals("Customers")) {
                        newPanel = new UpdateInformationPane(clientManager);
                    } else if (buttonNames[index].equals("Settings")) {
                        newPanel = new SettingPage(MainPage.this);
                    } else if (buttonNames[index].equals("History")) {
                        newPanel = new HistoryPage(fixedbillmanager);
                    } else if (buttonNames[index].equals("Plugin")) {
                        newPanel = pluginPane;
                    } else if (buttonNames[index].equals("Sales")) {
                        newPanel = new KasirPage(billManager, clientManager);
                    } else {
                        newPanel = new JPanel(new GridBagLayout());
                        gbc.anchor = GridBagConstraints.CENTER;
                        JLabel label = new JLabel("AAAAAA");
                        newPanel.add(label, gbc);
                    }
                    // create a close button and add it to the tab
                    JButton closeButton = new JButton("X");
                    // closeButton.setFont(new Font("Arial", Font.PLAIN, 5));
                    closeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // remove the tab
                            tabbedPane.removeTabAt(tabbedPane.indexOfComponent(newPanel));
                        }
                    });
                    // closeButton.setPreferredSize(new Dimension(30,20));
                    JPanel tabPanel = new JPanel(new BorderLayout(3,3));
                    JLabel tabTitle = new JLabel(buttonNames[index]);
                    tabPanel.add(tabTitle, BorderLayout.WEST);
                    tabPanel.add(closeButton, BorderLayout.EAST);

                    // add the panel to the tabbedPane
                    tabbedPane.addTab(buttonNames[index], newPanel);
                    tabbedPane.setTabComponentAt(tabbedPane.getTabCount()-1, tabPanel);
                    tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
                }
            });
            leftPanel.add(buttons[i]);
        }

        // create the JTabbedPane
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(0x1a1e3b));

        // // create startpanel
        // JPanel start;
        // start = new MainPanel();

        // add the LeftPanel and JTabbedPane to the JFrame
        add(leftPanel, BorderLayout.WEST);
        add(tabbedPane, BorderLayout.CENTER);

        // set JFrame properties
        setTitle("CPKBOS - Point of Sales");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JPanel getPluginPage() {
        return pluginPane;
    }

    public ArrayList<Septet<Integer, String, Integer, Double, Double, String, String>> getInventoryData() {
        ArrayList<Septet<Integer, String, Integer, Double, Double, String, String>> data = new ArrayList<>();
        for (Barang b : this.inv.getListBarang()) {
            Septet<Integer, String, Integer, Double, Double, String, String> currData = 
            new Septet<Integer, String, Integer, Double, Double, String, String> (b.getID(), b.getName(),
            b.getStock(), b.getPrice(), b.getBuyPrice(), b.getCategory(), b.getPicturePath());
            data.add(currData);
        }
        return data;
    }

    public HashMap<String,ArrayList<Quintet<Integer, String, String, Integer, Boolean>>> getClientManagerData() {
        HashMap<String,ArrayList<Quintet<Integer, String, String, Integer, Boolean>>> data = new HashMap<>();
        ArrayList<Quintet<Integer, String, String, Integer, Boolean>> customers = new ArrayList<>();
        for (Customer c : clientManager.getListCustomer()) {
            Quintet<Integer, String, String, Integer, Boolean> currData = new Quintet<Integer, String, String, Integer, Boolean>
                (c.getCustomerID(), null, null, null, null);
            customers.add(currData);
        }
        data.put("Customer", customers);
        customers.clear();
        for (Member m : clientManager.getListMember()) {
            Quintet<Integer, String, String, Integer, Boolean> currData = new Quintet<Integer, String, String, Integer, Boolean>
                (m.getCustomerID(), m.getCustomerName(), m.getNoOfPhone(), m.getPoint(), m.getActive());
            customers.add(currData);
        }
        data.put("Member", customers);
        customers.clear();
        for (VIP v : clientManager.getListVIP()) {
            Quintet<Integer, String, String, Integer, Boolean> currData = new Quintet<Integer, String, String, Integer, Boolean>
                (v.getCustomerID(), v.getCustomerName(), v.getNoOfPhone(), v.getPoint(), v.getActive());
            customers.add(currData);
        }
        data.put("VIP", customers);
        customers.clear();
        return data;
    }

    public void update() {
        this.dataStoreAdapter.writeClientManager(this.clientManager);
    }
}
