package org.bambrikii.examples.graphs.paths.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 03/06/18 13:19.
 */
public class PathMatrixFinder {
	protected final int[][] graph;
	protected final int maxX;
	protected final int maxY;

	public PathMatrixFinder(int[][] graph, int maxX, int maxY) {
		this.graph = graph;
		this.maxY = maxY;
		this.maxX = maxX;
	}

	public List<List<int[]>> findPaths() {
		List<List<PathMatrixNode>> paths = new ArrayList<>();
		List<PathMatrixNode> path = new ArrayList<>();
		paths.addAll(findPath(path, 0, 0, maxX, maxY, graph));
		return paths.stream()
				.map(m -> m.stream()
						.map(m2 -> new int[]{m2.getX(), m2.getY()})
						.collect(Collectors.toList())
				)
				.collect(Collectors.toList());
	}

	/**
	 * check next x:
	 * 1. has not reached edges
	 * 2. not yet passed
	 * goto next x
	 * <p>
	 * check next y available
	 * goto next y
	 * <p>
	 * check destination reached
	 * save path
	 *
	 * @param path
	 * @param x
	 * @param y
	 * @param maxX
	 * @param maxY
	 * @param graph
	 * @return
	 */
	private List<List<PathMatrixNode>> findPath(List<PathMatrixNode> path, int x, int y, int maxX, int maxY, int[][] graph) {
		List<List<PathMatrixNode>> paths = new ArrayList<>();
		PathMatrixNode next = new PathMatrixNode(x, y);
		if (path.contains(next) || x >= maxX || y >= maxY || x < 0 || y < 0 || graph[x][y] == 0) {
			return paths;
		} else if (x == maxX - 1 && y == maxY - 1) {
			List<PathMatrixNode> subPath = new ArrayList<>();
			subPath.addAll(path);
			subPath.add(next);
			paths.add(subPath);
			printPath("Target reached", subPath, maxX, maxY, graph);
			return paths;
		}
		List<PathMatrixNode> subPath = new ArrayList<>();
		subPath.addAll(path);
		subPath.add(next);
//		printPath("SubPath", subPath, maxX, maxY);
		paths.addAll(findPath(subPath, x + 1, y, maxX, maxY, graph));
		paths.addAll(findPath(subPath, x - 1, y, maxX, maxY, graph));
		paths.addAll(findPath(subPath, x, y + 1, maxX, maxY, graph));
		paths.addAll(findPath(subPath, x, y - 1, maxX, maxY, graph));
		return paths;
	}

	public void printPaths(List<List<int[]>> paths) {
		for (List<int[]> path : paths) {
			System.out.print("Path: ");
			for (int[] step : path) {
				System.out.print(" " + step[0] + ":" + step[1]);
			}
			System.out.println();
		}
	}

	private void printPath(String subPath, List<PathMatrixNode> path, int maxX, int maxY, int[][] graph) {
		int[][] matrix = new int[maxX][maxY];
		for (PathMatrixNode node : path) {
			matrix[node.getX()][node.getY()] = 1;
		}
		System.out.println();
		System.out.println("Matrix (" + subPath + "): ");
		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				System.out.print((matrix[x][y] != 0 ? graph[x][y] : " ") + " ");
			}
			System.out.println();
		}
	}
}
