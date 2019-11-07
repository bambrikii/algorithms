package org.bambrikii.examples.graphs.paths.node;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 03/06/18 13:24.
 */
public class PathNodeGraphFinder {
	private final PathNode startingNode;
	private final PathNode endingNode;

	public PathNodeGraphFinder(PathNode startingNode, PathNode endingNode) {
		this.startingNode = startingNode;
		this.endingNode = endingNode;
	}

	public List<List<PathEdge>> findPaths() {
		List<List<PathEdge>> edges = new LinkedList<>();
		edges.addAll(findEdges(new LinkedList<>(startingNode.getEdges()), endingNode));
		return edges;
	}

	private LinkedList<LinkedList<PathEdge>> findEdges(LinkedList<PathEdge> startingEdges, PathNode endingNode) {
		LinkedList<LinkedList<PathEdge>> result = new LinkedList<>();
		for (PathEdge edge : startingEdges) {
			PathNode rightNode = edge.getRightNode();
			if (rightNode == null) {
				continue;
			}
			if (rightNode.equals(endingNode)) {
				LinkedList<PathEdge> edges = new LinkedList<>();
				edges.add(edge);
				result.add(edges);
			} else {
				LinkedList<LinkedList<PathEdge>> edgesFound = findEdges(rightNode.getEdges(), endingNode);
				if (edgesFound.size() > 0) {
					edgesFound.stream().forEach(edges2 -> edges2.addFirst(edge));
					result.addAll(edgesFound);
				}
			}
		}
		return result;
	}

	public void printPaths(List<List<PathEdge>> paths) {
		System.out.println(" ");
		for (List<PathEdge> path : paths) {
			for (PathEdge edge : path) {
				System.out.println(edge.getLeftNode() + " -> " + edge.getRightNode() + " (" + edge.getVal() + ")");
			}
			System.out.println();
		}
	}
}
