package org.bambrikii.examples.rucksack;

/**
 * Created by Alexander Arakelyan on 02/04/17 19:49.
 * <p>
 * https://en.wikipedia.org/wiki/Knapsack_problem
 * <p>
 * 0/1 knapsack problem
 */
public class RucksackProblem01 {
	public static void main(String[] args) {
		int[][] vals = new int[][]{
				{5, 3, 2}, // values
				{3, 2, 1} // weights
		};
		RucksackProblem01 problem01 = new RucksackProblem01();
		int[][] result = problem01.solve(vals, 5);
		problem01.print(result);
	}

	public int[][] solve(int[][] vals, int capacity) {
		int[][] arr = new int[vals[0].length + 1][capacity];
		for (int i = 0; i < capacity; i++) {
			arr[0][i] = 0;
		}
		for (int item = 0; item < vals[0].length; item++) {
			for (int weight = 0; weight < capacity; weight++) {
				int item1 = item + 1;
				int item2 = item1 - 1;
				if (vals[1][item] > capacity) {
					arr[item1][item] = arr[item2][item];
				} else {
					int weight2 = weight - vals[1][item];
					if (weight2 > -1) {
						arr[item1][item] = Math.max(arr[item2][item], arr[item2][weight2] + vals[0][item]);
					} else {
						arr[item1][item] = arr[item2][weight];
					}
				}
			}
		}
		return arr;
	}

	private void print(int[][] result) {
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