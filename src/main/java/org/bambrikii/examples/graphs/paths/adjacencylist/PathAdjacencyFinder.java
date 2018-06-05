package org.bambrikii.examples.graphs.paths.adjacencylist;

import java.util.LinkedList;

/**
 * Created by Alexander Arakelyan on 03/06/18 17:29.
 */
public class PathAdjacencyFinder {
	private final int[][][] graph;

	private final int startX;
	private final int startY;

	private final int targetX;
	private final int targetY;

	public PathAdjacencyFinder(int[][][] graph, int startX, int startY, int targetX, int targetY) {
		this.graph = graph;
		this.startX = startX;
		this.startY = startY;
		this.targetX = targetX;
		this.targetY = targetY;
	}


	public LinkedList<LinkedList<int[]>> findPaths() {
		LinkedList<LinkedList<int[]>> result = new LinkedList<>();
		int left = startX;
		for (int rightIx = 0; rightIx < graph[left].length; rightIx++) {
			int right = graph[left][rightIx][0];
			int rightValue = graph[left][rightIx][1];
			if (right == startY) {
				LinkedList<int[]> from = new LinkedList<>();
				from.add(new int[]{left, right, rightValue});
				result.addAll(findPaths(from, left, rightIx));
			}
		}
		return result;
	}

	private LinkedList<LinkedList<int[]>> findPaths(
			LinkedList<int[]> from,
			int left,
			int rightIx
	) {
		LinkedList<LinkedList<int[]>> result = new LinkedList<>();
		int[] rightArray = graph[left][rightIx];
		int right = rightArray[0];
		int rightValue = rightArray[1];
		rights:
		for (int nextIx = 0; nextIx < graph[right].length; nextIx++) {
			if (left == targetX && right == targetY) {
				LinkedList<int[]> path = new LinkedList<>();
				path.addAll(from);
				result.add(path);
				printPath(path);
				printPaths("Target reached", result);
			} else {
				int[] nextArr = buildNextArr(right, nextIx);
				for (int i = 0; i < from.size() - 1; i++) {
					int[] from1 = from.get(i);
					if (from1[0] == nextArr[0] && from1[1] == nextArr[1]) {
						continue rights;
					}
				}
				LinkedList<int[]> path = new LinkedList<>();
				path.addAll(from);
				path.add(nextArr);
				printPath(path);
				LinkedList<LinkedList<int[]>> foundPaths = findPaths(path, right, nextIx);
				if (foundPaths.size() > 0) {
					for (LinkedList<int[]> foundPath : foundPaths) {
						foundPath.addFirst(new int[]{left, right, rightValue});
					}
					result.addAll(foundPaths);
					printPaths("Intermediate result", result);
				}
			}
		}
		return result;
	}


	private int[] buildNextArr(int left, int rightIx) {
		int[] rightArr = graph[left][rightIx];
		int right = rightArr[0];
		int rightValue = rightArr[1];
		return new int[]{left, right, rightValue};
	}


	private void printPath(LinkedList<int[]> path) {
		System.out.println("Trying: ");
		for (int[] step : path) {
			System.out.print(" " + step[0] + "->" + step[1] + "(" + step[2] + ")");
		}
		System.out.println();
	}

	private void printPaths(String message, LinkedList<LinkedList<int[]>> paths) {
		System.out.println("Path (" + message + "): ");
		for (LinkedList<int[]> path : paths) {
			for (int[] right : path) {
				System.out.print(" " + right[0] + "->" + right[1] + "(" + right[2] + ")");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void printPaths(LinkedList<LinkedList<int[]>> paths) {
		printPaths("Final result", paths);
	}
}
