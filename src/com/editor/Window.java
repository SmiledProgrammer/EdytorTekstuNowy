package com.editor;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.undo.UndoManager;

public class Window extends JFrame {

    UndoManager undoManager = new UndoManager();

    public void setupDefaultComponentOptions() {
        UIManager.put("Menu.selectionBackground", new ColorUIResource(MenuOptionTree.selectionColor));
        UIManager.put("Menu.opaque", true);
        UIManager.put("MenuItem.selectionBackground", new ColorUIResource(MenuOption.selectionColor));
        UIManager.put("MenuItem.opaque", true);
    }
}