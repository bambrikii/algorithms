package org.bambrikii.examples.sorting;

public class SortingException extends Exception {

	private static final long serialVersionUID = 1498225732703185782L;

	public SortingException(int[] array, int ix) {
		super(asString(array, ix));
	}

	private static String asString(int[] array, int ix) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i : array) {
			sb.append(i).append(",");
		}
		sb.append("]");
		return sb.toString() + ", " + array[ix] + "~" + array[ix + 1];
	}
}
