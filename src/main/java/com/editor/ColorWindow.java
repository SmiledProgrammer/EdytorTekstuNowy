package com.editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorWindow extends Window implements ActionListener {

    JLabel jButtonLabel;
    JButton jBackgroundColorButton, jTextColorButton, jMenuBarColorButton, jMenuTreeColorButton, jMenuSelectColorButton, jMenuHoverButton, jMenuTreeHoverButton;

    public static Color defaultForegroundColor = Color.BLACK;

    public void CreateColor(){
        MakeColorWindow();
        setupDefaultComponentOptions();
        AddColorButtons();
        AddColorLabel();
        this.setLayout(null);
        this.setVisible(true);
    }

    public void MakeColorWindow(){
        setTitle("Color Options");
        setSize(275, 275);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public void AddColorButtons() {
        jBackgroundColorButton = new JButton("Background");
        jBackgroundColorButton.setBounds(25,50,200,20);
        jBackgroundColorButton.addActionListener(this);
        jBackgroundColorButton.setActionCommand("Background");
        this.add(jBackgroundColorButton);

        jTextColorButton = new JButton("Text");
        jTextColorButton.setBounds(25,75,200,20);
        jTextColorButton.addActionListener(this);
        jTextColorButton.setActionCommand("Text");
        this.add(jTextColorButton);

        jMenuBarColorButton = new JButton("Menu Bar");
        jMenuBarColorButton.setBounds(25,100,200,20);
        jMenuBarColorButton.addActionListener(this);
        jMenuBarColorButton.setActionCommand("Menu Bar");
        this.add(jMenuBarColorButton);

        jMenuTreeColorButton = new JButton("Menu Tree");
        jMenuTreeColorButton.setBounds(25,125,200,20);
        jMenuTreeColorButton.addActionListener(this);
        jMenuTreeColorButton.setActionCommand("Menu Tree");
        this.add(jMenuTreeColorButton);

        jMenuSelectColorButton = new JButton("Menu Selected");
        jMenuSelectColorButton.setBounds(25,150,200,20);
        jMenuSelectColorButton.addActionListener(this);
        jMenuSelectColorButton.setActionCommand("Menu Selected");
        this.add(jMenuSelectColorButton);

        jMenuHoverButton = new JButton("Menu Hovered");
        jMenuHoverButton.setBounds(25,175,200,20);
        jMenuHoverButton.addActionListener(this);
        jMenuHoverButton.setActionCommand("Menu Hovered");
        this.add(jMenuHoverButton);

        jMenuTreeHoverButton = new JButton("Menu Tree Hovered");
        jMenuTreeHoverButton.setBounds(25,200,200,20);
        jMenuTreeHoverButton.addActionListener(this);
        jMenuTreeHoverButton.setActionCommand("Menu Tree Hovered");
        this.add(jMenuTreeHoverButton);
    }

    public void AddColorLabel() {
        jButtonLabel = new JLabel("Change the color of:");
        jButtonLabel.setBounds(25,25,150,20);
        this.add(jButtonLabel);
    }

    public void changeBackground(){
        Color initialBackground = NotepadWindow.textPane.getBackground();
        Color background = JColorChooser.showDialog(this, "Choose", initialBackground);
        NotepadWindow.textPane.setBackground(background);
    }

    public void changeText(){
        Color initialText = NotepadWindow.textPane.getForeground();
        Color text = JColorChooser.showDialog(this, "Choose", initialText);
        defaultForegroundColor = text;
        NotepadWindow.textPane.setForeground(text);
        AddLanguages.updateText();
    }

    public void changeMenuBar(){
        Color menuBar = JColorChooser.showDialog(this, "Choose", MenuBar.color);
        MenuBar.color = menuBar;
        MenuOptionTree.defaultColor = menuBar;
        NotepadWindow.menuBar.setColor();
        MenuBar.fileTree.ChangeTreeColor();
        MenuBar.editTree.ChangeTreeColor();
        MenuBar.formatTree.ChangeTreeColor();
        MenuBar.viewTree.ChangeTreeColor();
        MenuBar.languageTree.ChangeTreeColor();
    }

    public void changeMenuTree(){
        Color initialMenuTree = MenuOption.barColor;
        MenuOption.barColor = JColorChooser.showDialog(this, "Choose", initialMenuTree);
        MenuBar.fileNew.SetOptionColor();
        MenuBar.fileOpen.SetOptionColor();
        MenuBar.fileSave.SetOptionColor();
        MenuBar.fileSaveAs.SetOptionColor();
        MenuBar.fileExit.SetOptionColor();
        MenuBar.editUndo.SetOptionColor();
        MenuBar.editRedo.SetOptionColor();
        MenuBar.editFind.SetOptionColor();
        MenuBar.editReplace.SetOptionColor();
        MenuBar.chooseLanguageC.SetOptionColor();
        MenuBar.chooseLanguageJava.SetOptionColor();
        MenuBar.formatFont.SetOptionColor();
        MenuBar.viewThemes.SetOptionColor();
        MenuBar.chooseLanguageHTML.SetOptionColor();
        MenuBar.chooseNone.SetOptionColor();
        MenuBar.wordWrap.setBackground(MenuOption.barColor);
    }

    public void changeMenuTreeHovered(){
        Color initialMenuTreeHovered = MenuOption.selectionColor;
        MenuOption.selectionColor = JColorChooser.showDialog(this,"Choose", initialMenuTreeHovered);
        MenuBar.fileNew.SetSelectionColor();
        SwingUtilities.updateComponentTreeUI(Notepad.notepadWindow);
    }

    public void changeMenuSelected(){
        Color initialMenuSelected = MenuOptionTree.selectionColor;
        MenuOptionTree.selectionColor = JColorChooser.showDialog(this, "Choose", initialMenuSelected);
        MenuBar.fileTree.ChangeSelectionColor();
        SwingUtilities.updateComponentTreeUI(Notepad.notepadWindow);
    }

    public void changeMenuHovered(){
        Color initialMenuHovered = MenuOptionTree.hoverColor;
        MenuOptionTree.hoverColor = JColorChooser.showDialog(this, "Choose", initialMenuHovered);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Background": changeBackground(); break;
            case "Text": changeText(); break;
            case "Menu Bar": changeMenuBar(); break;
            case "Menu Tree": changeMenuTree(); break;
            case "Menu Selected": changeMenuSelected(); break;
            case "Menu Hovered": changeMenuHovered(); break;
            case "Menu Tree Hovered": changeMenuTreeHovered(); break;
        }
    }
}