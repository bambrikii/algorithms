package org.bambrikii.examples.algorithms.incubator.strings;

public class StairsWithOneTwoThreeSteps {
    public static void main(String argc[]) {
        System.out.println(step(4));
    }

    public static int step(int stairsLeft) {
        if (stairsLeft == 1 || stairsLeft == 0) {
            return 1;
        }
        if (stairsLeft == 2) {
            return 2;
        }
        return step(stairsLeft - 3) + step(stairsLeft - 2) + step(stairsLeft - 1);
    }
}
