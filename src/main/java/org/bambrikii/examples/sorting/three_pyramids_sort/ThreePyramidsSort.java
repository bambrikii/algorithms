package org.bambrikii.examples.sorting.three_pyramids_sort;

import org.bambrikii.examples.sorting.Sortable;

public class ThreePyramidsSort implements Sortable {

	private int[][] sort;
	private int[] pos;
	private boolean debug = false;

	public ThreePyramidsSort() {
	}

	public ThreePyramidsSort(boolean debug) {
		this.debug = debug;
	}

	public int[] sort(int[] sort1) {
		sort = new int[3][sort1.length];
		pos = new int[] { sort1.length - 1, -1, -1 };

		sort[0] = sort1.clone();
		if (debug) {
			print();
		}
		while (pos[2] < sort1.length - 1) {
			if (canMove(0, 2) && shouldMove(0, 2)) {
				move(0, 2);
				while (canMove(1, 2)) {
					move(1, 2);
				}
			}
			while (canMove(0, 2) && !shouldMove(0, 2) && canMove(2, 1)) {
				move(2, 1);
			}
			if (debug) {
				print();
			}
		}

		return sort[2];
	}

	void move(int pos1, int pos2) {
		int pos1_1 = pos[pos1];
		int pos2_2 = ++pos[pos2];
		sort[pos2][pos2_2] = sort[pos1][pos1_1];
		pos[pos1]--;
	}

	private void print() {
		for (int[] arr : sort) {
			for (int i : arr) {
				System.out.print(i + ", ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.print("pos: ");
		for (int i : pos) {
			System.out.print(i + ", ");
		}
		System.out.println();
		System.out.println();
	}

	boolean shouldMove(int pos1, int pos2) {
		return (pos[pos1] != -1 && (pos[pos2] == -1 || sort[pos1][pos[pos1]] >= sort[pos2][pos[pos2]]));
	}

	boolean canMove(int pos1, int pos2) {
		return (pos[pos1] != -1);
	}
}