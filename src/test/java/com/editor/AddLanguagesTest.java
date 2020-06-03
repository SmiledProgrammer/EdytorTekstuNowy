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
}