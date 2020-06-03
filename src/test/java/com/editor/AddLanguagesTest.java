package com.editor;

import junit.framework.TestCase;
import org.junit.Assert;

public class AddLanguagesTest extends TestCase {
    public void testDisableAllExcept() {
        AddLanguages langs = new AddLanguages();
        AddLanguages.setUpC();
        AddLanguages.setIfSetup("c",true);
        AddLanguages.setUpJava();
        AddLanguages.setIfSetup("java",true);
        AddLanguages.setUpHTML();
        AddLanguages.setIfSetup("html",true);

        AddLanguages.disableAllExcept("java");
        Assert.assertFalse(AddLanguages.getIfSetup("c"));
        Assert.assertFalse(AddLanguages.getIfSetup("html"));
        Assert.assertTrue(AddLanguages.getIfSetup("java"));
    }

    public void testGetIfSetup() {
        AddLanguages langs = new AddLanguages();
        AddLanguages.setUpC();
        AddLanguages.setIfSetup("c",true);
        AddLanguages.setUpJava();
        AddLanguages.setIfSetup("java",false);

        Assert.assertFalse(AddLanguages.getIfSetup("java"));
        Assert.assertTrue(AddLanguages.getIfSetup("c"));
    }

    public void testDisableAll() {
        AddLanguages langs = new AddLanguages();
        AddLanguages.setUpC();
        AddLanguages.setIfSetup("c",true);
        AddLanguages.setUpJava();
        AddLanguages.setIfSetup("java",false);

        AddLanguages.disableAll();
        Assert.assertFalse(AddLanguages.getIfSetup("c"));
        Assert.assertFalse(AddLanguages.getIfSetup("html"));
        Assert.assertFalse(AddLanguages.getIfSetup("java"));
    }
}