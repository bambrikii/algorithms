package org.bambrikii.examples.graphs.paths.matrix;

/**
 * Created by Alexander Arakelyan on 03/06/18 11:51.
 */
class PathMatrixNode {
	private final int x;
	private final int y;

	public PathMatrixNode(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (obj.getClass() != PathMatrixNode.class) {
			return false;
		}
		PathMatrixNode oth = (PathMatrixNode) obj;
		return oth.getX() == x && oth.getY() == y;
	}

	@Override
	public int hashCode() {
		return (x << 31) + y;
	}
}
