package main.Pages;

import javax.swing.ImageIcon;
import java.awt.*;

public class Utils {
    public static ImageIcon getImageIcon(String picturePath) {
        ImageIcon icon = new ImageIcon(picturePath);
        Image img = icon.getImage();
        img = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        icon.setImage(img);
        return icon;
    }

    public static ImageIcon getImageIcon(String picturePath, int width, int height) {
        ImageIcon icon = new ImageIcon(picturePath);
        Image img = icon.getImage();
        img = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        icon.setImage(img);
        return icon;
    }
}
