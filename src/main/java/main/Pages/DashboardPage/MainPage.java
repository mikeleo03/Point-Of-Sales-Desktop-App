package main.Pages.DashboardPage;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.javatuples.*;
import java.awt.*;
import javax.swing.*;
import main.Barang.*;
import main.Transaksi.*;
import main.Bill.*;
import main.Client.*;
import main.Pages.InventoryPage.*;
import main.Pages.PaymentPage.*;
import main.Pages.RegistrationPage.*;
import main.Pages.UpdateInformationPage.*;
import main.Pages.SettingPage.*;
import main.Pages.HistoryPage.*;
import main.Pages.PluginPage.*;

public class MainPage extends JFrame implements InterfacePage {
    private JTabbedPane tabbedPane;
    private JPanel leftPanel;
    private Inventory inv;
    private ClientManager clientmanager;

    public MainPage() {
        // set the layout of the JFrame to BorderLayout
        setLayout(new BorderLayout());

        clientmanager = new ClientManager();

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
        ImageIcon logo = new ImageIcon(new ImageIcon("../img/icon/logo.png").getImage().getScaledInstance(150, 30, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));
        leftPanel.add(logoLabel);
        
        JLabel POS = new JLabel("Point Of Sale");
        POS.setForeground(new Color(0x9c9c9c));
        POS.setBorder(BorderFactory.createEmptyBorder(5, 50, 20, 0));
        leftPanel.add(POS);

        // create the buttons with icons
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
        for (Customer c : clientmanager.getListCustomer()) {
            Quintet<Integer, String, String, Integer, Boolean> currData = new Quintet<Integer, String, String, Integer, Boolean>
                (c.getCustomerID(), null, null, null, null);
            customers.add(currData);
        }
        data.put("Customer", customers);
        customers.clear();
        for (Member m : clientmanager.getListMember()) {
            Quintet<Integer, String, String, Integer, Boolean> currData = new Quintet<Integer, String, String, Integer, Boolean>
                (m.getCustomerID(), m.getCustomerName(), m.getNoOfPhone(), m.getPoint(), m.getActive());
            customers.add(currData);
        }
        data.put("Member", customers);
        customers.clear();
        for (VIP v : clientmanager.getListVIP()) {
            Quintet<Integer, String, String, Integer, Boolean> currData = new Quintet<Integer, String, String, Integer, Boolean>
                (v.getCustomerID(), v.getCustomerName(), v.getNoOfPhone(), v.getPoint(), v.getActive());
            customers.add(currData);
        }
        data.put("VIP", customers);
        customers.clear();
        return data;
    }
}
