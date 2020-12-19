package org.bambrikii.examples.algorithms.incubator;

import java.io.IOException;
import java.util.Scanner;

public class TimeConversion {
    /*
     * Complete the timeConversion function below.
     */
    static String timeConversion(String s) {
        /*
         * Write your code here.
         */
        boolean am = s.substring(8, 10).equals("AM");
        String hStr = s.substring(0, 2);
        Integer h = Integer.parseInt(hStr);
        if (am) {
            if (h == 12) {
                return "00" + s.substring(2, 8);
            }
            return s.substring(0, 8);
        }
        if (h == 12) {
            return s.substring(0, 8);
        }
        return (h + 12) + s.substring(2, 8);
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        System.out.println(timeConversion("11:01:02AM"));
//        System.out.println(timeConversion("11:01:02PM"));
//        System.out.println(timeConversion("12:40:22AM"));
//        System.out.println(timeConversion("06:40:03AM"));
        System.out.println(timeConversion("12:40:22AM"));
        System.out.println(timeConversion("12:45:54PM"));

    }
}
