package com.editor;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.*;

public class Language {

    private static class SectionMarker { //klasa przechowująca znaki rozpoczynające i kończące sekcję (np. "<" i ">", "{" i "}")
        public char beginning;
        public char ending;
        public boolean colorWholeSection;
        public Color color;

        public SectionMarker(char beginning, char ending, boolean colorWholeSection, Color color) {
            this.beginning = beginning;
            this.ending = ending;
            this.colorWholeSection = colorWholeSection;
            this.color = color;
        }
    }

    private Map<String, Color> keywords;
    private ArrayList<SectionMarker> sectionMarkers;
    private boolean highlightNumbers;
    private ArrayList<Character> acceptedCharacters;

    private int index;
    public static String word;
    private int newLines;
    private boolean modifiedIndex;

    public Language() {
        keywords = new HashMap<>();
        sectionMarkers = new ArrayList<SectionMarker>();
        highlightNumbers = false;
        acceptedCharacters = new ArrayList<>();
    }

    private boolean isWhitespaceCharacter(char c) {
        /*for (SectionMarker sm : sectionMarkers) {
            if (c == sm.beginning || c == sm.ending)
                return false;
        }*/
        for (char ac : acceptedCharacters) {
            if (c == ac)
                return false;
        }
        return ((int)c <= 47 || (int)c >= 58 && (int)c <= 64 || (int)c >= 91 && (int)c <= 96 || (int)c >= 123 && (int)c <= 127) ? true : false;
    }

    private boolean isSkippableWhitespaceCharacter(char c) {
        for (SectionMarker sm : sectionMarkers) {
            if (c == sm.beginning || c == sm.ending)
                return false;
        }
        for (char ac : acceptedCharacters) {
            if (c == ac)
                return false;
        }
        return ((int)c <= 47 || (int)c >= 58 && (int)c <= 64 || (int)c >= 91 && (int)c <= 96 || (int)c >= 123 && (int)c <= 127) ? true : false;
    }

    private static void resetTextColor() {
        StyledDocument doc = NotepadWindow.textPane.getStyledDocument();
        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setForeground(sas, NotepadWindow.textPane.getForeground());
        NotepadWindow.ignoreNextEdit = true;
        doc.setCharacterAttributes(0, NotepadWindow.textPane.getText().length(), sas, false);
    }

    private void changeTextColor(int pos, int length, Color color) { //funkcja zmieniająca kolor danego fragmentu tekstu (z tego co wiem trzeba usuwać tekst i wstawiać go na nowo z kolorkiem)
        pos -= newLines; // Korekcja pozycji, bo StyledDocument ignoruje znaki nowych linii
        StyledDocument doc = NotepadWindow.textPane.getStyledDocument();
        SimpleAttributeSet sas = new SimpleAttributeSet();

        StyleConstants.setForeground(sas, color);
        NotepadWindow.ignoreNextEdit = true;
        doc.setCharacterAttributes(pos, length, sas, false);
    }

    private void skipWhitespaceCharacters() {
        String text = NotepadWindow.textPane.getText();
        char c;
        do { //szukanie początku słowa
            c = text.charAt(index++);
            if (c == '\n')
                newLines++;
        } while (isSkippableWhitespaceCharacter(c) && index < text.length());
        index--;
    }

    private void getNextWord() { //zwraca indeks znalezionego słowa; słowo zapisuje w statycznej zmiennej globalnej "word"; startingIndex to indeks, od którego zaczynane jest sprawdzanie
        word = "";
        String text = NotepadWindow.textPane.getText();
        int i = index;
        char c = text.charAt(i++);
        while (!isWhitespaceCharacter(c) && i < text.length()) { //szukanie końca słowa
            word += c;
            c = text.charAt(i++);
            if (c == '\n')
                newLines++;
        }
        if (!isWhitespaceCharacter(c) && i == text.length()) //dodawanie ostatniego znaku
            word += c;
    }

    private void checkKeywords() {
        if (keywords.containsKey(word)) {
            changeTextColor(index, word.length(), (Color) keywords.get(word));
        }
    }

    private void checkSections() {
        String text = NotepadWindow.textPane.getText();
        for (SectionMarker marker : sectionMarkers) { // Sekcje
            if (index < text.length()) {
                if (text.charAt(index) == marker.beginning) {
                    modifiedIndex = true;
                    if (marker.colorWholeSection) {
                        int startingIndex = index;
                        int length = 0;
                        char c;
                        while (index + 1 < text.length()) {
                            c = text.charAt(++index);
                            length++;
                            if (c == '\n')
                                newLines++;
                            if (c == marker.ending || index >= text.length())
                                break;
                        }
                        changeTextColor(startingIndex, length + 1, marker.color);
                        index++;
                        //System.out.println("index = " + index + " ; " + text.charAt(startingIndex));
                    } else {
                        changeTextColor(index, 1, marker.color);
                        //System.out.println(text.charAt(index));
                        index++;
                    }
                } else if (text.charAt(index) == marker.ending) {
                    modifiedIndex = true;
                    changeTextColor(index, 1, marker.color);
                    //System.out.println(text.charAt(index));
                    index++;
                }
            }
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public void updateTextColors() {
        String text = NotepadWindow.textPane.getText();
        resetTextColor();
        index = 0;
        word = "";
        newLines = 0;
        while (index < text.length()) {
            modifiedIndex = false;
            skipWhitespaceCharacters();
            checkSections();
            if (!modifiedIndex) {
                getNextWord(); // Pobieranie następnego słowa w tekście
                checkKeywords(); // Słowa kluczowe
                if (isNumeric(word) && highlightNumbers) // Liczby
                    changeTextColor(index, word.length(), Color.MAGENTA);
                index += word.length();
                boolean skipNextCharacter = true;
                if (index < text.length()) {
                    for (SectionMarker sm : sectionMarkers) {
                        if (text.charAt(index) == sm.beginning || text.charAt(index) == sm.ending)
                            skipNextCharacter = false;
                    }
                }
                if (skipNextCharacter) index++;
                System.out.println("index = " + index + " ; " + word);
            }
        }
    }

    public void addKeyword(String word, Color c) {
        keywords.put(word, c);
    }

    public void addSectionMarker(char begin, char end) {
        sectionMarkers.add(new SectionMarker(begin, end, false, Color.blue));
    }

    public void addSectionMarker(char begin, char end, boolean colorWholeSection, Color color) {
        sectionMarkers.add(new SectionMarker(begin, end, colorWholeSection, color));
    }

    public void addAcceptedCharacter(char c) {
        acceptedCharacters.add(c);
    }

    public void setHighlightNumbers(boolean value) {
        highlightNumbers = value;
    }

    //do usunięcia potem
    public static void main(String[] args) {
        Language l = new Language();
        String text = "Alamakota.\nno i jeszcze   psa!";
        int index = -1;
        l.word = "";
        while (index + word.length() + 1 < text.length()) {
            //index = l.getNextWord(index + word.length() + 1);
            System.out.println(index + ". " + word);
        }
        System.out.println(text.charAt(11));
    }

}