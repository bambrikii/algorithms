package org.bambrikii.examples.algorithms.incubator.strings;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class BracketsMatchLoop {
    public static void main(String[] args) {
        BracketsMatchLoop match = new BracketsMatchLoop();
        System.out.println(match.isValid("([]){}") + " == " + true);
        System.out.println(match.isValid("([]){}}") + " == " + false);
        System.out.println(match.isValid("([])[}") + " == " + false);
        System.out.println(match.isValid("([])") + " == " + true);
        System.out.println(match.isValid("([})") + " == " + false);
    }

    private static final Map<Character, Character> close;

    private static final Set<Character> open;

    static {
        Map<Character, Character> close0 = new HashMap<>();
        close0.put(')', '(');
        close0.put('}', '{');
        close0.put(']', '[');
        close = Collections.unmodifiableMap(close0);
        open = Collections.unmodifiableSet(new HashSet<>(close0.values()));
    }

    private boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (open.contains(ch)) {
                stack.add(ch);
            } else if (close.containsKey(ch)) {
                if (stack.isEmpty() || stack.pollLast() != close.get(ch)) {
                    return false;
                }
            }
        }
        return true;
    }
}
