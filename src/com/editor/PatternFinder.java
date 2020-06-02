package com.editor;

public class PatternFinder {

    private String text;
    private String pattern;
    private int lastPosition;
    private int endOfLines = 0;
    public int[] prefixSuffixTable;

    public void createPrefixSuffixTable() {
        prefixSuffixTable = new int[pattern.length() + 1];
        prefixSuffixTable[0] = 0;
        int i = 0;
        for (int j = 1; j < pattern.length(); j++) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                i++;
                prefixSuffixTable[j + 1] = i;
            } else {
                i = 0;
                prefixSuffixTable[j + 1] = 0;
            }
        }
    }

    public int findPattern(int startingIndex) {
        int j = -1;
        for (int i = startingIndex; i < text.length(); i++) {
            if (text.charAt(i) == '\n')
                endOfLines++;
            j++;
            if (text.charAt(i) == pattern.charAt(j)) {
                if (j + 1 == pattern.length())
                    return i - pattern.length() + 1;
            } else {
                j = prefixSuffixTable[j] - 1;
                i--;
            }
        }
        return -1;
    }

    public int findNext() {
        //endOfLines = 0;
        if (lastPosition + pattern.length() < text.length()) {
            int pos = findPattern(lastPosition);
            lastPosition = pos + 1;
            return pos - endOfLines;
        } else {
            return -1;
        }
    }

    public void setStrings(String text, String pattern) {
        this.text = text;
        this.pattern = pattern;
        createPrefixSuffixTable();
        lastPosition = 0;
        endOfLines = 0;
    }

    public void updateTextString(String text) {
        this.text = text;
    }

}