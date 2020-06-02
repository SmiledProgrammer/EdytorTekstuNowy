package com.editor;


import java.awt.*;
import java.util.Set;
import java.util.HashSet;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    private static final Set<Integer> pressedKeys = new HashSet<>();

    static void cleanSet() {
        pressedKeys.clear();
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
        boolean ifCtrl = pressedKeys.contains(KeyEvent.VK_CONTROL);

        if (!pressedKeys.isEmpty()) {
            if (ifCtrl && pressedKeys.contains(KeyEvent.VK_S)) {
                if(pressedKeys.contains(KeyEvent.VK_SHIFT)) {
                    FileFunctions.saveFileAs();
                }
                else { FileFunctions.saveFile(); }
                cleanSet();
            }

            if (ifCtrl && pressedKeys.contains(KeyEvent.VK_N)) FileFunctions.newFile();

            if (ifCtrl && pressedKeys.contains(KeyEvent.VK_O)) {
                FileFunctions.openFile();
                cleanSet();
            }

            if (ifCtrl && pressedKeys.contains(KeyEvent.VK_Z)) {
                if(pressedKeys.contains(KeyEvent.VK_SHIFT)) {
                    EditFunctions.redo();
                }
                else { EditFunctions.undo(); }
            }

            if (ifCtrl && pressedKeys.contains(KeyEvent.VK_EQUALS)) {
                Font f = NotepadWindow.textPane.getFont();
                Font newF = new Font(f.getFontName(), f.getStyle(), f.getSize()+1);
                NotepadWindow.textPane.setFont(newF);
            }

            if (ifCtrl && pressedKeys.contains(KeyEvent.VK_MINUS)) {
                Font f = NotepadWindow.textPane.getFont();
                Font newF = new Font(f.getFontName(), f.getStyle(), f.getSize()-1);
                NotepadWindow.textPane.setFont(newF);
            }
        }

    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
        //System.out.println(pressedKeys);
    }

    @Override
    public synchronized void keyTyped(KeyEvent e) {
        /* not used */
    }
}
