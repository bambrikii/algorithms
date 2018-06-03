package org.bambrikii.examples.graphs.paths.node;

/**
 * Created by Alexander Arakelyan on 03/06/18 14:04.
 */
public class PathEdge {
	private PathNode leftNode;
	private PathNode rightNode;
	private int val;

	public PathEdge(PathNode leftNode, PathNode rightNode, int val) {
		this.leftNode = leftNode;
		this.rightNode = rightNode;
		this.val = val;
	}

	public PathNode getLeftNode() {
		return leftNode;
	}

	public PathNode getRightNode() {
		return rightNode;
	}

	public int getVal() {
		return val;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(val);
		if (rightNode.getEdges().size() > 0) {
			sb.append(rightNode.toString());
		}
		return sb.toString();
	}
}
