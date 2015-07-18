package org.bambrikii.examples.sorting;

import java.util.Calendar;
import java.util.Random;

import org.bambrikii.examples.sorting.bubble.BubbleSort;
import org.bambrikii.examples.sorting.my.MyBubbleSort;
import org.bambrikii.examples.sorting.my.MySort;
import org.bambrikii.examples.sorting.quick.QuickSort;
import org.junit.BeforeClass;
import org.junit.Test;

public class SortAlgosPerformanceTest {

	private static int[] array;

	@BeforeClass
	public static void before() {
		int n = 6800;
		array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = new Random().nextInt(n);
		}
	}

	private void check(int[] array) throws SortingException {
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > array[i + 1]) {
				throw new SortingException(array, i);
			}
		}
	}

	private void runAlgo(Sortable algo, int[] arr) throws SortingException {
		System.out.println("Algo: " + algo.getClass().getCanonicalName());
		long start = Calendar.getInstance().getTimeInMillis();
		int[] arr2 = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			arr2[i] = arr[i];
		}
		check(algo.sort(arr2));
		long end = Calendar.getInstance().getTimeInMillis();
		System.out.println("time: " + (end - start));
	}

	@Test
	public void testBubbleSort() throws SortingException {
		runAlgo(new BubbleSort(), array);
	}

	@Test
	public void testQuickSort() throws SortingException {
		runAlgo(new QuickSort(), array);
	}

	@Test
	public void testMySort() throws SortingException {
		runAlgo(new MySort(), array);
	}

	@Test
	public void testMyBubbleSort() throws SortingException {
		runAlgo(new MyBubbleSort(), array);
	}
}
