package com.editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FontWindow extends Window implements ActionListener {

    JTextField jSizeTextField;
    JLabel jSizeLabel, jTypeLabel;
    JButton jSetButton;
    JCheckBox jBoldCheckBox, jItalicCheckBox;
    JComboBox jTypeComboBox;

    public void CreateFont(){
        MakeFontWindow();
        AddFontSizePanel();
        AddFontTypePanel();
        AddSetButton();
        AddCheckboxPanel();
        this.setLayout(null);
        this.setVisible(true);
    }

    public void MakeFontWindow() {
        setTitle("Font Options");
        setSize(300, 225);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public void AddSetButton(){
        jSetButton = new JButton("Set");
        jSetButton.setBounds(100,135,100,20);
        jSetButton.addActionListener(this);
        this.add(jSetButton);
    }

    public void AddFontSizePanel() {
        jSizeTextField = new JTextField();
        jSizeTextField.setBounds(25,50,30,20);
        this.add(jSizeTextField);

        jSizeLabel = new JLabel("Font Size");
        jSizeLabel.setBounds(25,25,150,20);
        this.add(jSizeLabel);
    }

    public void AddFontTypePanel() {
        String fonts[]={"Arial", "Comic Sans MS"};
        jTypeComboBox = new JComboBox(fonts);
        jTypeComboBox.setBounds(25,100,100,20);
        this.add(jTypeComboBox);

        jTypeLabel = new JLabel("Font Type");
        jTypeLabel.setBounds(25,75,150,20);
        this.add(jTypeLabel);
    }
    public void AddCheckboxPanel() {
        jBoldCheckBox = new JCheckBox("Bold");
        jBoldCheckBox.setBounds(200,50,150,20);
        this.add(jBoldCheckBox);

        jItalicCheckBox = new JCheckBox("Italic");
        jItalicCheckBox.setBounds(200,100,150,20);
        this.add(jItalicCheckBox);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int value, style;

        String fontName = (String) jTypeComboBox.getSelectedItem();

        if(jBoldCheckBox.isSelected() && jItalicCheckBox.isSelected())
            style = Font.BOLD + Font.ITALIC;
        else if(jBoldCheckBox.isSelected())
            style = Font.BOLD;
        else if (jItalicCheckBox.isSelected())
            style = Font.ITALIC;
        else
            style = Font.PLAIN;

        String valueStr = jSizeTextField.getText();
        if(!valueStr.isEmpty())
            value = Integer.parseInt(valueStr);
        else
            value = NotepadWindow.textPane.getFont().getSize();

        NotepadWindow.currentFont = new Font(fontName, style, value);
        NotepadWindow.setFont();

    }
}