package com.editor;

public class ViewFunctions {

    ThemeWindow viewWindow;

    public ViewFunctions(ThemeWindow viewWindow) { this.viewWindow = viewWindow; }

    public void color() {
        viewWindow = new ThemeWindow();
        viewWindow.CreateTheme();
    }
}
