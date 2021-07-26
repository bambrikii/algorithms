package org.bambrikii.examples.graphs.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private char val;
    private String word;
    private Map<Character, Trie> children = new HashMap<>();

    public Trie(char ch) {
        this.val = ch;
    }

    public void insert(String str) {
        Trie curr = null;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!children.containsKey(ch)) {
                children.put(ch, new Trie(ch));
            }
            curr = children.get(ch);
        }
        curr.word = str;
    }

    public boolean find(String str) {
        Trie curr = null;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!children.containsKey(ch)) {
                return false;
            }
            curr = children.get(ch);
        }
        return curr.word != null;
    }
}
