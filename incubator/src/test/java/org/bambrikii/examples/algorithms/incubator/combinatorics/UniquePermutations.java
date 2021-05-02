package org.bambrikii.examples.algorithms.incubator.combinatorics;

import java.util.Arrays;

public class UniquePermutations {

    void swap(char[] str, int i, int ceilIndex) {
        char ch = str[i];
        str[i] = str[ceilIndex];
        str[ceilIndex] = ch;
    }

    int findCeil(char str[], char first, int l, int h) {
        int ceilIndex = l;

        for (int i = l + 1; i <= h; i++)
            if (str[i] > first && str[i] < str[ceilIndex])
                ceilIndex = i;

        return ceilIndex;
    }

    void sortedPermutations(char str[]) {
        int size = str.length;

        Arrays.sort(str);

        boolean isFinished = false;
        while (!isFinished) {

            int x = 1;
            System.out.printf("%d  %s \n", x++, str);

            int i;
            for (i = size - 2; i >= 0; --i)
                if (str[i] < str[i + 1])
                    break;

            if (i == -1)
                isFinished = true;
            else {

                int ceilIndex = findCeil(str, str[i], i + 1, size - 1);
                swap(str, i, ceilIndex);

// TODO: uncomment:               Arrays.sort(str + i + 1, size - i - 1, sizeof(str[0]), compare);
            }
        }
    }

    // Driver program to test above function
    int main() {
        char str[] = "ACBC".toCharArray();
        sortedPermutations(str);
        return 0;
    }
}
