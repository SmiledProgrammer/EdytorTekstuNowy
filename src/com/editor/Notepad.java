package com.editor;

import javax.swing.*;

public class Notepad {

    static NotepadWindow notepadWindow;

    public static void main(String [] args) {
        notepadWindow = new NotepadWindow();
        SwingUtilities.invokeLater(notepadWindow);
    }
}