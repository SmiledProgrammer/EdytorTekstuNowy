package com.editor;

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.awt.*;

public class EditFunctions {
    static Window window;
    static FindWindow findWindow;
    static ReplaceWindow replaceWindow;

    public EditFunctions(Window window) {
        this.window = window;
        findWindow = new FindWindow();
        replaceWindow = new ReplaceWindow();
    }

    public static void undo() {
        try {
            window.undoManager.undo();
        }catch (CannotUndoException e) {
            System.err.println("No text to undo");
        }
        FileFunctions.ifEdited = true;
    }

    public static void redo() {
        try {
            window.undoManager.redo();
        }catch (CannotRedoException e) {
            System.err.println("No text to redo");
        }
        FileFunctions.ifEdited = true;
    }

    public static void find() {
        findWindow.setVisible(true);
    }

    public void replace() {
        replaceWindow.setVisible(true);
    }


}
