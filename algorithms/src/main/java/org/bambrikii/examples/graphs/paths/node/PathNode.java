package org.bambrikii.examples.graphs.paths.node;

import java.util.LinkedList;

/**
 * Created by Alexander Arakelyan on 03/06/18 14:03.
 */
public class PathNode {
	private LinkedList<PathEdge> edges = new LinkedList<>();

	public LinkedList<PathEdge> getEdges() {
		return edges;
	}

	public PathNode addEdge(PathNode next, int val) {
		edges.add(new PathEdge(this, next, val));
		return next;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (edges.size() > 0) {
			sb.append("(").append(edges.get(0).toString());
			for (int i = 1; i < edges.size(); i++) {
				sb.append(",").append(edges.get(i).toString());
			}
			sb.append(")");
		}
		return sb.toString();
	}
}
