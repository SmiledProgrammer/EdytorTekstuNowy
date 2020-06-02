package com.editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThemeWindow extends Window implements ActionListener {

    JButton jLightThemeButton, jDarkThemeButton, jLazuliThemeButton, jCustomThemeButton;
    ColorWindow colorWindow;
    JLabel jThemeLabel;

    Color mainLight = new Color(153, 204, 255);
    Color mainDark = new Color(0, 0, 80);
    Color mainLazuli = new Color(2, 78, 161);

    public void CreateTheme() {
        MakeThemeWindow();
        setupDefaultComponentOptions();
        AddThemeButtons();
        AddThemeLabel();
        this.setLayout(null);
        this.setVisible(true);
    }

    public void MakeThemeWindow() {
        setTitle("Theme Window");
        setSize(275, 225);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public void AddThemeButtons() {
        jLightThemeButton = new JButton("Light");
        jLightThemeButton.setBounds(25, 50, 200, 20);
        jLightThemeButton.addActionListener(this);
        jLightThemeButton.setActionCommand("Light");
        this.add(jLightThemeButton);

        jDarkThemeButton = new JButton("Dark");
        jDarkThemeButton.setBounds(25, 75, 200, 20);
        jDarkThemeButton.addActionListener(this);
        jDarkThemeButton.setActionCommand("Dark");
        this.add(jDarkThemeButton);

        jLazuliThemeButton = new JButton("Lazuli");
        jLazuliThemeButton.setBounds(25, 100, 200, 20);
        jLazuliThemeButton.addActionListener(this);
        jLazuliThemeButton.setActionCommand("Lazuli");
        this.add(jLazuliThemeButton);

        jCustomThemeButton = new JButton("Custom");
        jCustomThemeButton.setBounds(25, 125, 200, 20);
        jCustomThemeButton.addActionListener(this);
        jCustomThemeButton.setActionCommand("Custom");
        this.add(jCustomThemeButton);
    }

    public void AddThemeLabel() {
        jThemeLabel = new JLabel("Choose your preferred theme:");
        jThemeLabel.setBounds(25,25,200,20);
        this.add(jThemeLabel);
    }

    public void setThemeLight() {       //wytłumaczenie poszczególnych linijek w setThemeLazuli
        MenuBar.color = mainLight;
        NotepadWindow.menuBar.setColor();
        MenuOptionTree.defaultColor = mainLight;
        changeTrees();
        changeTreeFont(Color.BLACK);
        MenuOptionTree.hoverColor = new Color(130, 190, 255);
        MenuOptionTree.selectionColor = new Color(70, 160, 255);
        MenuBar.fileTree.ChangeSelectionColor();
        MenuOption.barColor = new Color(120, 190, 255);
        changeOptions();
        MenuOption.selectionColor = new Color(100, 180, 255);
        MenuBar.fileNew.SetSelectionColor();
        SwingUtilities.updateComponentTreeUI(Notepad.notepadWindow);
        NotepadWindow.textPane.setBackground(Color.WHITE);
        NotepadWindow.textPane.setForeground(Color.BLACK);

        AddLanguages.updateText();
    }

    public void setThemeDark() {
        MenuBar.color = mainDark;
        NotepadWindow.menuBar.setColor();
        MenuOptionTree.defaultColor = mainDark;
        changeTrees();
        changeTreeFont(Color.WHITE);
        MenuOptionTree.hoverColor = mainDark.brighter().brighter();
        MenuOptionTree.selectionColor = mainDark.brighter().brighter().brighter();
        MenuBar.fileTree.ChangeSelectionColor();
        MenuOption.barColor = mainDark.brighter().brighter().brighter();
        changeOptions();
        MenuOption.selectionColor = mainDark.brighter().brighter().brighter().brighter();
        MenuBar.fileNew.SetSelectionColor();
        SwingUtilities.updateComponentTreeUI(Notepad.notepadWindow);
        NotepadWindow.textPane.setBackground(Color.BLACK);
        NotepadWindow.textPane.setForeground(Color.WHITE);

        AddLanguages.updateText();
    }

    public void setThemeLazuli() {

        MenuBar.color = mainLazuli;     //kolor całego menubara
        NotepadWindow.menuBar.setColor();

        MenuOptionTree.defaultColor = mainLazuli;   //kolor opcji na menubarze (file, theme, etc.)
        changeTrees();
        changeTreeFont(Color.WHITE);

        MenuOptionTree.hoverColor = new Color(19, 94, 175);      //zmiana koloru po najechaniu

        MenuOptionTree.selectionColor = mainLazuli.brighter();       //zmiana koloru po wybraniu
        MenuBar.fileTree.ChangeSelectionColor();

        MenuOption.barColor = mainLazuli.brighter();     //zmiana koloru opcji w drzewie (new, saveas, etc.)
        changeOptions();

        MenuOption.selectionColor = new Color(0, 173, 241);     //zmiana koloru opcji w drzewie po najechaniu na nie
        MenuBar.fileNew.SetSelectionColor();

        SwingUtilities.updateComponentTreeUI(Notepad.notepadWindow);        //to jest potrzebne aby UIManager się zaaktualizował

        NotepadWindow.textPane.setBackground(new Color(48, 214, 200));          //zmiana pola tekstowego
        NotepadWindow.textPane.setForeground(new Color(0, 127, 255));

        AddLanguages.updateText();
    }

    public void customSetup() {     //tworzy colorwindow
        colorWindow = new ColorWindow();
        colorWindow.CreateColor();
    }

    public void changeTrees() {     // zmienia kolor tła opcji na pasku menu
        MenuBar.fileTree.ChangeTreeColor();
        MenuBar.editTree.ChangeTreeColor();
        MenuBar.formatTree.ChangeTreeColor();
        MenuBar.viewTree.ChangeTreeColor();
        MenuBar.languageTree.ChangeTreeColor();
    }

    public void changeOptions() {       //zmienia kolor tła opcji w drzewkach
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

    public void changeTreeFont(Color fontColor){        //zmienia czcionke na menubarze i w menutree
        MenuBar.fileTree.ChangeFontColor(fontColor);
        MenuBar.editTree.ChangeFontColor(fontColor);
        MenuBar.formatTree.ChangeFontColor(fontColor);
        MenuBar.viewTree.ChangeFontColor(fontColor);
        MenuBar.languageTree.ChangeFontColor(fontColor);
        MenuBar.fileNew.SetOptionFontColor(fontColor);
        MenuBar.fileOpen.SetOptionFontColor(fontColor);
        MenuBar.fileSave.SetOptionFontColor(fontColor);
        MenuBar.fileSaveAs.SetOptionFontColor(fontColor);
        MenuBar.fileExit.SetOptionFontColor(fontColor);
        MenuBar.editUndo.SetOptionFontColor(fontColor);
        MenuBar.editRedo.SetOptionFontColor(fontColor);
        MenuBar.editFind.SetOptionFontColor(fontColor);
        MenuBar.editReplace.SetOptionFontColor(fontColor);
        MenuBar.chooseLanguageC.SetOptionFontColor(fontColor);
        MenuBar.chooseLanguageJava.SetOptionFontColor(fontColor);
        MenuBar.formatFont.SetOptionFontColor(fontColor);
        MenuBar.viewThemes.SetOptionFontColor(fontColor);
        MenuBar.chooseLanguageHTML.SetOptionFontColor(fontColor);
        MenuBar.chooseNone.SetOptionFontColor(fontColor);
        MenuBar.wordWrap.setForeground(fontColor);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Light": setThemeLight(); break;
            case "Dark": setThemeDark(); break;
            case "Lazuli": setThemeLazuli(); break;
            case "Custom": customSetup(); break;
        }
    }
}