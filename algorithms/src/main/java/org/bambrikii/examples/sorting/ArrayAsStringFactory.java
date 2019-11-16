package org.bambrikii.examples.sorting;

public class ArrayAsStringFactory {
    public static boolean DEBUG = false;

    public static String asString(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i : array) {
            sb.append(i).append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void log(int[] array) {
        if (DEBUG) {
            System.out.println(asString(array));
        }
    }
}
