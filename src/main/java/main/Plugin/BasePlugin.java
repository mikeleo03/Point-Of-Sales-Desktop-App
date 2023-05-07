package main.Plugin;

import javax.swing.*;

public abstract class BasePlugin {
    static JPanel pluginPage;

    public void makeEmptyPage (JComponent c) {
        BasePlugin.pluginPage = new JPanel();
        c.add(BasePlugin.pluginPage);
    }
}