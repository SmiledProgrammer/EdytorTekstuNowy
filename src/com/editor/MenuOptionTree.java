package com.editor;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuOptionTree extends JMenu {

    static Color defaultColor = new Color(153, 204, 255);//153, 204, 255
    static Color hoverColor = new Color(130, 190, 255);//130, 190, 255
    static Color selectionColor = new Color(70, 160, 255);//70, 160, 255

    public MenuOptionTree(String name) {
        ChangeTreeColor();
        setText(name);
        setupHoverEvent();
    }

    public void ChangeTreeColor() {
        setBorder(BorderFactory.createLineBorder(defaultColor, 1));
        setBackground(defaultColor);
    }

    public void ChangeFontColor(Color fontColor) {
        setForeground(fontColor);
    }

    public void ChangeSelectionColor() {
        UIManager.put("Menu.selectionBackground", new ColorUIResource(MenuOptionTree.selectionColor));
        UIManager.put("Menu.opaque", true);
    }

    public void setupHoverEvent() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultColor);
            }
        });
    }
}