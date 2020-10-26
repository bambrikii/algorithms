package org.bambrikii.examples.graphs.bfs;

import java.util.LinkedList;

public class BfsMatrix {
    private int[][] arr;
    private int[][] weights;
    private int[][][] prevs;
    private int[][] processed;

    public BfsMatrix(int w, int h) {
        this(new int[w][h]);
    }

    public BfsMatrix(int[][] arr) {
        this.arr = arr;
        int length = arr.length;
        weights = new int[length][];
        prevs = new int[length][][];
        processed = new int[length][];
        for (int i = 0; i < length; i++) {
            weights[i] = new int[arr[i].length];
            prevs[i] = new int[arr[i].length][2];
            processed[i] = new int[arr[i].length];
        }
    }

    public BfsMatrix setWeight(int x, int y, int weight) {
        arr[x][y] = weight;
        return this;
    }

    public BfsMatrixResult find(int[] xy1, int[] xy2) {
        LinkedList<int[]> q = new LinkedList<>();
        int x0 = xy1[0];
        int y0 = xy1[1];
        if (arr[x0][y0] > 0) {
            q.add(xy1);
            weights[x0][y0] = arr[x0][y0];
        }

        int weight = -1;
        while (q.size() > 0) {
            int[] coord = q.poll();
            int x = coord[0];
            int y = coord[1];
            int newWeight = arr[x][y];
            int[] prev = prevs[x][y];
            int prevX = prev[0];
            int prevY = prev[1];
            if (prevX != x || prevY != y) {
                newWeight += weights[prevX][prevY];
            }
            if (weights[x][y] == 0 || weights[x][y] > newWeight) {
                weights[x][y] = newWeight;
                prevs[x][y] = prev;
                if (x == xy2[0] && y == xy2[1]) {
                    weight = newWeight;
                }

            }
            for (int[] move : moves) {
                int moveX = x + move[0];
                int moveY = y + move[1];
                if ((moveX > -1 && moveX < arr.length)
                        && (moveY > -1 && moveY < arr[moveX].length)
                        && (arr[moveX][moveY] > 0)
                        && (processed[moveX][moveY] == 0)
                ) {
                    q.add(new int[]{moveX, moveY});
                    prevs[moveX][moveY] = new int[]{x, y};
                }
            }
            processed[x][y] = 1;
        }
        BfsMatrixResult result = new BfsMatrixResult();
        result.weight = weight;
        result.weights = weights;
        result.prevs = prevs;
        return result;
    }

    public static class BfsMatrixResult {
        public int weight;
        public int[][][] prevs;
        public int[][] weights;
    }

    private int[][] moves = new int[][]{
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };
}
