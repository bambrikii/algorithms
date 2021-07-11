package org.bambrikii.examples.numbers;

public class KaratsubaMultiplication {
    public static void main(String[] args) {
        KaratsubaMultiplication algo = new KaratsubaMultiplication();
        System.out.println(algo.multiply("123", "456"));
        System.out.println(algo.multiply("2", "3"));
        System.out.println(algo.multiply("20", "30"));
        System.out.println(algo.multiply("200", "300"));

        System.out.println(algo.add("123", "456"));
        System.out.println(algo.add("999", "11"));
    }

    /**
     * Split multiplication by two digits.
     */
    public String multiply(String str1, String str2) {
        int sign1 = 1;
        if (str1.startsWith("+")) {
            str1 = str1.substring(1);
        } else if (str1.startsWith("-")) {
            sign1 = -1;
            str1 = str1.substring(1);
        }
        int sign2 = 1;
        if (str2.startsWith("+")) {
            str2.substring(1);
        } else if (str2.startsWith("-")) {
            sign2 = -1;
            str2 = str2.substring(1);
        }
        String sum = "0";
        for (int i1 = 0; i1 < str1.length(); i1 += 2) {
            for (int i2 = 0; i2 < str2.length(); i2 += 2) {
                int val1 = doubleDigitIntAt(str1, i1);
                int val2 = doubleDigitIntAt(str2, i2);
                String val = val1 * val2 + pow(i1, i2);
                sum = add(sum, val);
            }
        }
        sum = trimLeadingZeroes(sum);
        return sign1 * sign2 == -1
                ? "-" + sum
                : sum;
    }

    private String add(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int len = Math.max(len1, len2) + 1;
        int[] vals = new int[len];
        for (int i = 0; i < len - 1; i++) {
            int digit1 = intAt(str1, len1 - i - 1);
            int digit2 = intAt(str2, len2 - i - 1);
            int pos = len - i - 1;
            int sum = digit1 + digit2 + vals[pos];
            vals[pos] = sum % 10;
            vals[pos - 1] = sum / 10;
        }
        return trimLeadingZeroes(vals);
    }

    private String pow(int pow1, int pow2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pow1 + pow2; i++) {
            sb.append("0");
        }
        return sb.toString();
    }

    private int doubleDigitIntAt(String str, int pos) {
        int len = str.length();
        int val = intAt(str, len - pos - 1);
        if (len - pos - 2 >= 0) {
            val += intAt(str, len - pos - 2) * 10;
        }
        return val;
    }

    private int intAt(String str, int pos) {
        if (pos < 0) {
            return 0;
        }
        return str.charAt(pos) - '0';
    }

    private String trimLeadingZeroes(String sum) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sum.length(); i++) {
            char ch = sum.charAt(i);
            if (sb.length() == 0 && ch == '0') {
                continue;
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    private String trimLeadingZeroes(int[] vals) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vals.length; i++) {
            if (sb.length() == 0 && vals[i] == 0) {
                continue;
            }
            sb.append(vals[i]);
        }
        return sb.toString();
    }
}
