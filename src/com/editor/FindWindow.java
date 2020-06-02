package com.editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindWindow extends JFrame implements ActionListener {
    private final JPanel panel;
    private final JLabel label;
    private final JTextField textField;
    private final JButton button;
    private PatternFinder pf;
    private String lastPattern;

    public FindWindow() {
        setTitle("Find in text");
        setSize(320, 160);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        label = new JLabel("Type in a string to find:");
        panel.add(label);
        textField = new JTextField();
        panel.add(textField);
        button = new JButton("Find next");
        button.addActionListener(this);
        panel.add(button);
        add(panel);

        pf = new PatternFinder();
        lastPattern = "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (textField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please type in a pattern to find.");
            return;
        }
        if (NotepadWindow.textPane.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There is no text to search in!");
            return;
        }
        if (!lastPattern.equals(textField.getText())) {
            pf.setStrings(NotepadWindow.textPane.getText(), textField.getText());
            lastPattern = textField.getText();
        }
        int position = pf.findNext();
        if (position == -1) {
            JOptionPane.showMessageDialog(null, "No more occurrences of the pattern in the text.");
            pf.setStrings(NotepadWindow.textPane.getText(), textField.getText());
        } else {
            int endPosition = position + textField.getText().length();
            NotepadWindow.textPane.setCaretPosition(endPosition);
            NotepadWindow.textPane.setSelectionStart(position);
            NotepadWindow.textPane.setSelectionEnd(endPosition);
        }
    }
}