package org.bambrikii.examples.graphs.balancing.avl.core;

import java.util.Comparator;

/**
 * Created by Alexander Arakelyan on 11/02/17 17:07.
 */
public class AVLNodeComparator<T extends AbstractAVLNode> implements Comparator<T> {
	@Override
	public int compare(T o1, T o2) {
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
