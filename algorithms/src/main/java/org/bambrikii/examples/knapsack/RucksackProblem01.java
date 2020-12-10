package org.bambrikii.examples.knapsack;

/**
 * Created by Alexander Arakelyan on 02/04/17 19:49.
 * <p>
 * https://en.wikipedia.org/wiki/Knapsack_problem
 * <p>
 * 0/1 knapsack problem
 */
public class RucksackProblem01 {
    public int[][] solve(int[][] vals, int capacity) {
        int[][] arr = new int[vals[0].length + 1][capacity];
        for (int i = 0; i < capacity; i++) {
            arr[0][i] = 0;
        }
        for (int item = 0; item < vals[0].length; item++) {
            for (int weight = 0; weight < capacity; weight++) {
                arr[item + 1][item] = vals[1][item] > capacity
                        ? arr[item][item]
                        : max(vals, arr, item, weight);
            }
        }
        return arr;
    }

    private int max(int[][] vals, int[][] arr, int item, int weight) {
        int weight2 = weight - vals[1][item];
        return weight2 > -1
                ? Math.max(arr[item][item], arr[item][weight2] + vals[0][item])
                : arr[item][weight];
    }

    public void print(int[][] result) {
        int max = result[result.length - 1][0];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                if (max < result[i][j]) {
                    max = result[i][j];
                }
            }
        }
        System.out.println(max);
    }
}
