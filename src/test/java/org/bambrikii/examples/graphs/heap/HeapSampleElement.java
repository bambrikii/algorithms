package org.bambrikii.examples.graphs.heap;

/**
 * Created by Alexander Arakelyan on 26/03/17 18:03.
 */
public class HeapSampleElement implements Comparable {
	private final int val1;

	HeapSampleElement(int val1) {
		this.val1 = val1;
	}

	public int getVal1() {
		return val1;
	}

	@Override
	public int compareTo(Object obj) {
		if (obj == null) {
			return 1;
		}
		if (!obj.getClass().equals(HeapSampleElement.class)) {
			return 1;
		}
		HeapSampleElement other = (HeapSampleElement) obj;
		return val1 - other.val1;
	}

	@Override
	public String toString() {
		return String.valueOf(val1);
	}
}
