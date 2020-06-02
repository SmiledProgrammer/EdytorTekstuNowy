package com.editor;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.Color;

public class MenuOption extends JMenuItem {

    static Color barColor = new Color(120, 190, 255);//120, 190, 255
    static Color selectionColor = new Color(100, 180, 255);//100, 180, 255

    public MenuOption(String text) {
        SetOptionColor();
        setText(text);
    }

    public void SetOptionColor() {
        setBackground(barColor);
        setBorder(BorderFactory.createLineBorder(barColor, 2));
    }

    public void SetSelectionColor(){
        UIManager.put("MenuItem.selectionBackground", new ColorUIResource(MenuOption.selectionColor));
        UIManager.put("MenuItem.opaque", true);
    }

    public void SetOptionFontColor(Color color) {
        setForeground(color);
    }
}