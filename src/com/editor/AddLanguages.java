package com.editor;

import com.editor.Language;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class AddLanguages {
    public static final String C = "c";
    public static final String JAVA = "java";
    public static final String HTML = "html";
    static private Language languageC;
    static private Language languageJava;
    static private Language languageHTML;
    static private Map<String, Boolean> languages;

    public AddLanguages() {
        //ifCsetUp = false;
        languages = new HashMap<>();
        languages.put(C,false);
        languages.put(JAVA,false);
        languages.put(HTML,false);
    }

    public static boolean getIfSetup(String l) {
        return languages.get(l);
    }

    public static void setIfSetup(String l, boolean b) {
        try {
            languages.replace(l,b);
        } catch (NullPointerException e) {
            System.out.println("Jezyk nie istnieje, to po co go wpisujesz");
            System.exit(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Nie możesz podać takiego jezyka");
            System.exit(0);
        }
    }

    public static void disableAll() {
        languages.replaceAll((key,value)-> value=false);
    }

    public static void disableAllExcept(String s) {
        disableAll();
        setIfSetup(s,true);
    }

    public static void updateText() {
        if(getIfSetup(C)) {
            languageC.updateTextColors();
            return;
        }
        if (getIfSetup(JAVA)) {
            languageJava.updateTextColors();
            return;
        }

        if (getIfSetup(HTML)) {
            languageHTML.updateTextColors();
            return;
        }
        // dla kolejnych jezykow kolejne warunki
    }

    public static void setUpC() {
        if(!getIfSetup(C) || languageC != null)
            return;
        languageC = new Language();
        languageC.addKeyword("#include", Color.GREEN);
        languageC.addKeyword("void", Color.RED);
        languageC.addKeyword("int", Color.RED);
        languageC.addKeyword("char", Color.RED);
        languageC.addKeyword("unsigned", Color.RED);
        languageC.addKeyword("union", Color.RED);
        languageC.addKeyword("static", Color.RED);
        languageC.addKeyword("short", Color.RED);
        languageC.addKeyword("struct", Color.RED);
        languageC.addKeyword("signed", Color.RED);
        languageC.addKeyword("float", Color.RED);
        languageC.addKeyword("double", Color.RED);
        languageC.addKeyword("char", Color.RED);
        languageC.addKeyword("long", Color.RED);
        languageC.addKeyword("do", Color.blue);
        languageC.addKeyword("extern", Color.blue);
        languageC.addKeyword("if", Color.blue);
        languageC.addKeyword("return", Color.blue);
        languageC.addKeyword("break", Color.blue);
        languageC.addKeyword("default", Color.blue);
        languageC.addKeyword("enum", Color.blue);
        languageC.addKeyword("goto", Color.blue);
        languageC.addKeyword("far", Color.blue);
        languageC.addKeyword("huge", Color.blue);
        languageC.addKeyword("pascal", Color.blue);
        languageC.addKeyword("register", Color.blue);
        languageC.addKeyword("sizeof", Color.blue);
        languageC.addKeyword("while", Color.blue);
        languageC.addKeyword("case", Color.blue);
        languageC.addKeyword("auto", Color.blue);
        languageC.addKeyword("continue", Color.blue);
        languageC.addKeyword("else", Color.blue);
        languageC.addKeyword("for", Color.blue);
        languageC.addKeyword("switch", Color.blue);
        languageC.addKeyword("typedef", Color.blue);
        languageC.addKeyword("struct", Color.blue); 

        languageC.addSectionMarker('{', '}');
        languageC.addSectionMarker('<', '>', true, Color.GREEN);
        languageC.addSectionMarker('"', '"', true, Color.GREEN);
        languageC.addSectionMarker('\'', '\'', true, Color.GREEN);

        languageC.addAcceptedCharacter('#');
        languageC.addAcceptedCharacter('.');

        languageC.setHighlightNumbers(true);
    }

    public static void setUpJava() {
        if(!getIfSetup(JAVA) || languageJava != null)
            return;

        languageJava = new Language();
        languageJava.addKeyword("byte", Color.ORANGE);
        languageJava.addKeyword("short", Color.ORANGE);
        languageJava.addKeyword("int", Color.ORANGE);
        languageJava.addKeyword("long", Color.ORANGE);
        languageJava.addKeyword("float", Color.ORANGE);
        languageJava.addKeyword("double", Color.ORANGE);
        languageJava.addKeyword("boolean", Color.ORANGE);
        languageJava.addKeyword("void", Color.ORANGE);
        languageJava.addKeyword("true", Color.ORANGE);
        languageJava.addKeyword("false", Color.ORANGE);
        languageJava.addKeyword("class", Color.ORANGE);
        languageJava.addKeyword("new", Color.ORANGE);
        languageJava.addKeyword("null", Color.ORANGE);
        languageJava.addKeyword("instanceof", Color.ORANGE);
        languageJava.addKeyword("package", Color.ORANGE);
        languageJava.addKeyword("import", Color.ORANGE);
        languageJava.addKeyword("main", Color.YELLOW);
        languageJava.addKeyword("public", Color.ORANGE);
        languageJava.addKeyword("protected", Color.ORANGE);
        languageJava.addKeyword("default", Color.ORANGE);
        languageJava.addKeyword("private", Color.ORANGE);
        languageJava.addKeyword("if", Color.ORANGE);
        languageJava.addKeyword("else", Color.ORANGE);
        languageJava.addKeyword("switch", Color.ORANGE);
        languageJava.addKeyword("case", Color.ORANGE);
        languageJava.addKeyword("break", Color.ORANGE);
        languageJava.addKeyword("default", Color.ORANGE);
        languageJava.addKeyword("for", Color.ORANGE);
        languageJava.addKeyword("continue", Color.ORANGE);
        languageJava.addKeyword("return", Color.ORANGE);
        languageJava.addKeyword("while", Color.ORANGE);
        languageJava.addKeyword("do", Color.ORANGE);
        languageJava.addKeyword("static", Color.ORANGE);
        languageJava.addKeyword("final", Color.ORANGE);
        languageJava.addKeyword("extends", Color.ORANGE);
        languageJava.addKeyword("implements", Color.ORANGE);
        languageJava.addKeyword("@Override", Color.GREEN);
        languageJava.addKeyword("enum", Color.ORANGE);
        languageJava.addKeyword("try", Color.ORANGE);
        languageJava.addKeyword("catch", Color.ORANGE);
        languageJava.addKeyword("throws", Color.ORANGE);
        languageJava.addKeyword("throw", Color.ORANGE);
        languageJava.addKeyword("abstract", Color.ORANGE);
        languageJava.addKeyword("interface", Color.ORANGE);
        languageJava.addKeyword("var", Color.ORANGE);
        languageJava.addKeyword("transient", Color.ORANGE);

        languageJava.addSectionMarker('{', '}');
        languageJava.addSectionMarker('"', '"', true, Color.BLUE);
        languageJava.addSectionMarker('\'', '\'', true, Color.BLUE);
        languageJava.setHighlightNumbers(true);
    }

    public static void setUpHTML() {
        if (!getIfSetup(HTML) || languageHTML != null)
            return;

        languageHTML = new Language();
        languageHTML.addKeyword("html", Color.blue);
        languageHTML.addKeyword("head", Color.blue);
        languageHTML.addKeyword("body", Color.blue);
        languageHTML.addKeyword("title", Color.blue);
        languageHTML.addKeyword("br", Color.blue);
        languageHTML.addKeyword("u", Color.blue);
        languageHTML.addKeyword("b", Color.blue);
        languageHTML.addKeyword("i", Color.blue);
        languageHTML.addKeyword("tt", Color.blue);
        languageHTML.addKeyword("strike", Color.blue);
        languageHTML.addKeyword("big", Color.blue);
        languageHTML.addKeyword("small", Color.blue);
        languageHTML.addKeyword("font", Color.blue);
        languageHTML.addKeyword("basefont", Color.blue);
        languageHTML.addKeyword("sub", Color.blue);
        languageHTML.addKeyword("sup", Color.blue);
        languageHTML.addKeyword("h1", Color.blue);
        languageHTML.addKeyword("h2", Color.blue);
        languageHTML.addKeyword("h3", Color.blue);
        languageHTML.addKeyword("h4", Color.blue);
        languageHTML.addKeyword("h5", Color.blue);
        languageHTML.addKeyword("h6", Color.blue);
        languageHTML.addKeyword("hr", Color.blue);
        languageHTML.addKeyword("p", Color.blue);
        languageHTML.addKeyword("table", Color.blue);
        languageHTML.addKeyword("tr", Color.blue);
        languageHTML.addKeyword("td", Color.blue);
        languageHTML.addKeyword("img", Color.blue);
        languageHTML.addKeyword("div", Color.blue);
        languageHTML.addKeyword("link", Color.blue);
        languageHTML.addKeyword("meta", Color.blue);
        languageHTML.addKeyword("a", Color.blue);//-
        languageHTML.addKeyword("bgcolor", Color.red);
        languageHTML.addKeyword("text", Color.red);
        languageHTML.addKeyword("size", Color.red);
        languageHTML.addKeyword("color", Color.red);
        languageHTML.addKeyword("face", Color.red);
        languageHTML.addKeyword("border", Color.red);
        languageHTML.addKeyword("width", Color.red);
        languageHTML.addKeyword("height", Color.red);
        languageHTML.addKeyword("href", Color.red);
        languageHTML.addKeyword("src", Color.red);
        languageHTML.addKeyword("rel", Color.red);
        languageHTML.addKeyword("id", Color.red);
        languageHTML.addKeyword("class", Color.red);
        languageHTML.addKeyword("content", Color.red);
        languageHTML.addKeyword("public", Color.red);
        languageHTML.addKeyword("type", Color.red);

        languageHTML.addKeyword("HTML", Color.blue);
        languageHTML.addKeyword("HEAD", Color.blue);
        languageHTML.addKeyword("BODY", Color.blue);
        languageHTML.addKeyword("TITLE", Color.blue);
        languageHTML.addKeyword("BR", Color.blue);
        languageHTML.addKeyword("U", Color.blue);
        languageHTML.addKeyword("B", Color.blue);
        languageHTML.addKeyword("I", Color.blue);
        languageHTML.addKeyword("TT", Color.blue);
        languageHTML.addKeyword("STRIKE", Color.blue);
        languageHTML.addKeyword("BIG", Color.blue);
        languageHTML.addKeyword("SMALL", Color.blue);
        languageHTML.addKeyword("FONT", Color.blue);
        languageHTML.addKeyword("BASEFONT", Color.blue);
        languageHTML.addKeyword("SUB", Color.blue);
        languageHTML.addKeyword("SUP", Color.blue);
        languageHTML.addKeyword("H1", Color.blue);
        languageHTML.addKeyword("H2", Color.blue);
        languageHTML.addKeyword("H3", Color.blue);
        languageHTML.addKeyword("H4", Color.blue);
        languageHTML.addKeyword("H5", Color.blue);
        languageHTML.addKeyword("H6", Color.blue);
        languageHTML.addKeyword("HR", Color.blue);
        languageHTML.addKeyword("P", Color.blue);
        languageHTML.addKeyword("TABLE", Color.blue);
        languageHTML.addKeyword("TR", Color.blue);
        languageHTML.addKeyword("TD", Color.blue);
        languageHTML.addKeyword("IMG", Color.blue);
        languageHTML.addKeyword("DIV", Color.blue);
        languageHTML.addKeyword("LINK", Color.blue);
        languageHTML.addKeyword("META", Color.blue);
        languageHTML.addKeyword("A", Color.blue);//-
        languageHTML.addKeyword("BGCOLOR", Color.red);
        languageHTML.addKeyword("TEXT", Color.red);
        languageHTML.addKeyword("SIZE", Color.red);
        languageHTML.addKeyword("COLOR", Color.red);
        languageHTML.addKeyword("FACE", Color.red);
        languageHTML.addKeyword("BORDER", Color.red);
        languageHTML.addKeyword("WIDTH", Color.red);
        languageHTML.addKeyword("HEIGHT", Color.red);
        languageHTML.addKeyword("HREF", Color.red);
        languageHTML.addKeyword("SRC", Color.red);
        languageHTML.addKeyword("REL", Color.red);
        languageHTML.addKeyword("ID", Color.red);
        languageHTML.addKeyword("CLASS", Color.red);
        languageHTML.addKeyword("CONTENT", Color.red);
        languageHTML.addKeyword("PUBLIC", Color.red);
        languageHTML.addKeyword("TYPE", Color.red);

        languageHTML.setHighlightNumbers(true);
        languageHTML.addSectionMarker('"', '"');
        languageHTML.addSectionMarker('<', '>', false, Color.RED);
    }
}
