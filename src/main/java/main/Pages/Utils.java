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
}
