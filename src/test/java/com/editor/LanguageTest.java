package com.editor;

import com.editor.Language;
import org.junit.Assert;
import org.junit.Test;

public class LanguageTest {
    @Test
    public void isNumericTest() {
        Language l = new Language();
        Assert.assertTrue(l.isNumeric("1.3456"));
        Assert.assertTrue(l.isNumeric("12"));
        Assert.assertFalse(l.isNumeric("123a"));
    }
    @Test
    public void skipWhitespaceCharacters() {
        Language l = new Language();
        String text = "  \t \n ;., tekst";
        l.skipWhitespaceCharacters(text); //10 powinno być
        Assert.assertEquals(l.index, 10);
    }

    @Test
    public void getNextWord() {
        Language l = new Language();
        String text = "  \t \n ;., tekst \n ...() int";
        l.skipWhitespaceCharacters(text);
        l.getNextWord(text);
        Assert.assertEquals(l.word, "tekst");
        l.index += l.word.length();
        l.skipWhitespaceCharacters(text);
        l.getNextWord(text);
        Assert.assertEquals(l.word, "int");
    }

}