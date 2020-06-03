package com.editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplaceWindow extends JFrame implements ActionListener {
    private final JPanel panel;
    private final JLabel labelToFind;
    private final JLabel labelToReplaceWith;
    private final JTextField textFieldToFind;
    private final JTextField textFieldToReplaceWith;
    private final JButton singleButton;
    private final JButton allButton;
    private PatternFinder pf;
    private String lastPattern;

    public ReplaceWindow() {
        setTitle("Replace in text");
        setSize(320, 160);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        labelToFind = new JLabel("String to find:");
        panel.add(labelToFind);
        textFieldToFind = new JTextField();
        panel.add(textFieldToFind);

        labelToReplaceWith = new JLabel("String to replace it with:");
        panel.add(labelToReplaceWith);
        textFieldToReplaceWith = new JTextField();
        panel.add(textFieldToReplaceWith);

        singleButton = new JButton("Replace next");
        singleButton.addActionListener(this);
        singleButton.setActionCommand("next");
        panel.add(singleButton);

        allButton = new JButton("Replace all");
        allButton.addActionListener(this);
        allButton.setActionCommand("all");
        panel.add(allButton);

        add(panel);

        pf = new PatternFinder();
        lastPattern = "";
    }

    private static void replaceText(JTextPane text, String replacement, int startingIndex, int endingIndex) {
        text.setSelectionStart(startingIndex);
        text.setSelectionEnd(endingIndex);
        text.replaceSelection(replacement);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (textFieldToFind.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please type in a string to find.");
            return;
        }
        if (NotepadWindow.textPane.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "There is no text to search in!");
            return;
        }
        if (!lastPattern.equals(textFieldToFind.getText())) {
            pf.setStrings(NotepadWindow.textPane.getText(), textFieldToFind.getText());
            lastPattern = textFieldToFind.getText();
        }
        if (e.getActionCommand().equals("next")) {
            int position = pf.findNext();
            if (position == -1) {
                JOptionPane.showMessageDialog(null, "No more occurrences of the string in the text.");
            } else {
                int endPosition = position + textFieldToFind.getText().length();
                NotepadWindow.textPane.setCaretPosition(endPosition);
                replaceText(NotepadWindow.textPane, textFieldToReplaceWith.getText(), position, endPosition);
                pf.updateTextString(NotepadWindow.textPane.getText());
            }
        } else if (e.getActionCommand().equals("all")) {
            int position = pf.findNext();
            int elementsReplaced = 0;
            while (position != -1) {
                int endPosition = position + textFieldToFind.getText().length();
                replaceText(NotepadWindow.textPane, textFieldToReplaceWith.getText(), position, endPosition);
                pf.updateTextString(NotepadWindow.textPane.getText());
                elementsReplaced++;
                position = pf.findNext();
            }
            JOptionPane.showMessageDialog(null, "Replaced " + elementsReplaced + " element(s).");
        }
    }
}