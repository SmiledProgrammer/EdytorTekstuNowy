package com.editor;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar implements ActionListener {

    NotepadWindow notepadWindow;
    FontWindow fontWindow;
    ThemeWindow themeWindow;
    static Color color = new Color(153, 204, 255);
    static MenuOptionTree fileTree; //new, open, save, save as, exit
    static MenuOptionTree editTree; //undo, redo
    static MenuOptionTree formatTree; //font (window pops out)
    static MenuOptionTree viewTree; //theme (window pops out)
    static MenuOptionTree languageTree;
    static MenuOption fileNew, fileOpen, fileSave, fileSaveAs, fileExit;
    static MenuOption editUndo, editRedo, editFind, editReplace;
    static MenuOption chooseLanguageC, chooseLanguageJava, chooseLanguageHTML, chooseNone;
    static MenuOption formatFont;
    static JCheckBoxMenuItem wordWrap;
    static MenuOption viewThemes;

    FileFunctions fileFunctions;
    EditFunctions editFunctions;
    FormatFunctions formatFunctions;
    ViewFunctions viewFunctions;
    AddLanguages addLanguages;

    public MenuBar(NotepadWindow notepadWindow) {
        this.notepadWindow = notepadWindow;
        setColor();
        setupFileTree();
        setupEditTree();
        setupFormatTree();
        setupViewTree();
        setupFunctions();
        setupLanguage();
    }

    public void setColor() {
        setBackground(color);
        setBorder(BorderFactory.createLineBorder(color, 2));
    }

    public void setupFileTree() {
        fileTree = new MenuOptionTree("File");
        add(fileTree);

        fileNew = new MenuOption("New");
        fileNew.setToolTipText("Ctrl+N");
        fileNew.addActionListener(this);
        fileNew.setActionCommand("New");
        fileTree.add(fileNew);

        fileOpen = new MenuOption("Open");
        fileOpen.setToolTipText("Ctrl+O");
        fileOpen.addActionListener(this);
        fileOpen.setActionCommand("Open");
        fileTree.add(fileOpen);

        fileSave = new MenuOption("Save");
        fileSave.setToolTipText("Ctrl+S");
        fileSave.addActionListener(this);
        fileSave.setActionCommand("Save");
        fileTree.add(fileSave);

        fileSaveAs = new MenuOption("SaveAs");
        fileSaveAs.addActionListener(this);
        fileSaveAs.setActionCommand("SaveAs");
        fileTree.add(fileSaveAs);

        fileExit = new MenuOption("Exit");
        fileExit.addActionListener(this);
        fileExit.setActionCommand("Exit");
        fileTree.add(fileExit);
    }


    public void setupEditTree() {
        editTree = new MenuOptionTree("Edit");
        add(editTree);

        editUndo = new MenuOption("Undo");
        editUndo.addActionListener(this);
        editUndo.setActionCommand("Undo");
        editTree.add(editUndo);

        editRedo = new MenuOption("Redo");
        editRedo.addActionListener(this);
        editRedo.setActionCommand("Redo");
        editTree.add(editRedo);

        editFind = new MenuOption("Find");
        editFind.addActionListener(this);
        editFind.setActionCommand("Find");
        editTree.add(editFind);

        editReplace = new MenuOption("Find and Replace");
        editReplace.addActionListener(this);
        editReplace.setActionCommand("Replace");
        editTree.add(editReplace);

    }

    public void setupFormatTree() {
        formatTree = new MenuOptionTree("Format");
        add(formatTree);

        formatFont = new MenuOption("Font");
        formatFont.addActionListener(this);
        formatFont.setActionCommand("Font");
        formatTree.add(formatFont);


        wordWrap = new JCheckBoxMenuItem("Word wrap");
        wordWrap.addItemListener(e ->
                formatFunctions.wrap(((AbstractButton)e.getSource()).isSelected()));
        wordWrap.setActionCommand("Word wrap");
        formatTree.add(wordWrap);
        MenuBar.wordWrap.setBackground(MenuOption.barColor);
    }

    public void setupViewTree() {
        viewTree = new MenuOptionTree("View");
        add(viewTree);

        viewThemes = new MenuOption("Theme");
        viewThemes.addActionListener(this);
        viewThemes.setActionCommand("Theme");
        viewTree.add(viewThemes);
    }

    public void setupFunctions() {
        fileFunctions = new FileFunctions(notepadWindow);
        editFunctions = new EditFunctions(notepadWindow);
        formatFunctions = new FormatFunctions(fontWindow);
        viewFunctions = new ViewFunctions(themeWindow);
        addLanguages = new AddLanguages();
    }

    public void setupLanguage() {
        languageTree = new MenuOptionTree("Language");
        add(languageTree);

        chooseNone = new MenuOption("Disable languages");
        chooseNone.addActionListener(this);
        chooseNone.setActionCommand("None");

        chooseLanguageC = new MenuOption("C language");
        chooseLanguageC.addActionListener(this);
        chooseLanguageC.setActionCommand("c");

        chooseLanguageJava = new MenuOption("Java language");
        chooseLanguageJava.addActionListener(this);
        chooseLanguageJava.setActionCommand("java");

        chooseLanguageHTML = new MenuOption("HTML language");
        chooseLanguageHTML.addActionListener(this);
        chooseLanguageHTML.setActionCommand("html");

        languageTree.add(chooseNone);
        languageTree.add(chooseLanguageC);
        languageTree.add(chooseLanguageJava);
        languageTree.add(chooseLanguageHTML);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "New": fileFunctions.newFile(); break;
            case "Open": fileFunctions.openFile(); break;
            case "Save": FileFunctions.saveFile(); break;
            case "SaveAs": FileFunctions.saveFileAs(); break;
            case "Exit": fileFunctions.exitFile(); break;
            case "Undo": editFunctions.undo(); break;
            case "Redo": editFunctions.redo(); break;
            case "Find": editFunctions.find(); break;
            case "Replace": editFunctions.replace(); break;
            case "Font": formatFunctions.font(); break;
            case "Theme": viewFunctions.color(); break;
            case "None": AddLanguages.disableAll(); AddLanguages.updateText(); break;
            case AddLanguages.C:
                AddLanguages.disableAllExcept(command);
                AddLanguages.setUpC();
                AddLanguages.updateText();
                break;
            case AddLanguages.JAVA:
                AddLanguages.disableAllExcept(command);
                AddLanguages.setUpJava();
                AddLanguages.updateText();
                break;
            case AddLanguages.HTML:
                AddLanguages.disableAllExcept(command);
                AddLanguages.setUpHTML();
                AddLanguages.updateText();
                break;
            // case AddLanguages.InnyJezyk: AddLanguages.disableAllExcept(command); AddLanguages.setUpInny(); break;
        }
    }
}
