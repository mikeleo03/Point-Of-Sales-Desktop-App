package main.Pages.DashboardPage;
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

public class MainPage extends JFrame {

    private JTabbedPane tabbedPane;

    public MainPage() {
        // set the layout of the JFrame to BorderLayout
        setLayout(new BorderLayout());

        ClientManager clientmanager = new ClientManager();

        Barang nasgor = new Barang("Nasi Goreng", 10, 11000.00, 13000.00, "Makanan", "../");
        Barang mie = new Barang("Mie Goreng", 10, 9000.00, 7000.00, "Makanan", "../");
        Barang eskrim = new Barang("Ice Cream", 12, 5000.00, 7000.00, "Makanan", "../");
        
        Inventory inv = new Inventory();
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
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));
        leftPanel.setBackground(new Color(0x1a1e3b));

        // create the buttons with icons
        String[] buttonNames = {"Dashboard", "Registration", "Customers", "History", "Payment", 
                                "Inventory", "Sales", "Stocks", "Settings", "Plugin"};
        ImageIcon[] buttonIcons = {
                new ImageIcon("images.png"), new ImageIcon("registration.png"), new ImageIcon("customers.png"),
                new ImageIcon("history.png"), new ImageIcon("payment.png"), new ImageIcon("inventory.png"),
                new ImageIcon("sales.png"), new ImageIcon("stocks.png"), new ImageIcon("settings.png"), new ImageIcon("plugin.png")
        };
        JButton[] buttons = new JButton[buttonNames.length];
        for (int i = 0; i < buttonNames.length; i++) {
            final int index = i;
            buttons[i] = new JButton(buttonNames[i], buttonIcons[i]);
            buttons[i].setPreferredSize(new Dimension(180, 50));
            buttons[i].setBorder(BorderFactory.createEmptyBorder());
            buttons[i].setContentAreaFilled(false);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // create a new panel and add it to the tabbedPane
                    /* JPanel newPanel = new JPanel(new BorderLayout());
                    JLabel label = new JLabel("AAAAAA", JLabel.CENTER);
                    newPanel.add(label, BorderLayout.CENTER); */
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
                        newPanel = new PaymentPage(bill, inv, fixedbillmanager);
                    } else if (buttonNames[index].equals("Registration")) {
                        newPanel = new RegistrationPane(clientmanager);
                    } else if (buttonNames[index].equals("Customers")) {
                        newPanel = new UpdateInformationPane(clientmanager);
                    } else if (buttonNames[index].equals("Settings")) {
                        newPanel = new SettingPage();
                    } else if (buttonNames[index].equals("History")) {
                        newPanel = new HistoryPage(fixedbillmanager);
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
                }
            });
            leftPanel.add(buttons[i]);
        }

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
}
