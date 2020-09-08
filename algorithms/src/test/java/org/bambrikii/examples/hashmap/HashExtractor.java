package org.bambrikii.examples.hashmap;

import org.bambrikii.examples.graphs.hashmap.HashExtractable;

class HashExtractor implements HashExtractable<Integer> {
	@Override
	public int hash(Integer key) {
		return key.hashCode();
	}

	@Override
	public int compare(Integer left, Integer right) {
		if (left == null && right == null) {
			return 0;
		}
		if (left == null) {
			return -1;
		}
		if (right == null) {
			return 1;
		}
		int leftInt = left.intValue();
		int rightInt = right.intValue();
		if (leftInt > rightInt) {
			return -1;
		}
		if (leftInt < rightInt) {
			return 1;
		}
		return 0;
	}
}