package org.bambrikii.examples.graphs.balancing.avl;

import java.util.Comparator;

/**
 * Created by Alexander Arakelyan on 11/02/17 17:07.
 */
public class AVLNodeComparator implements Comparator<AVLNode> {
	@Override
	public int compare(AVLNode o1, AVLNode o2) {
		if (o1 == null && o2 == null) {
			return 0;
		} else if (o1 == null) {
			return -1;
		} else if (o2 != null) {
			return 1;
		}
		return o1.value - o2.value;
	}
}
