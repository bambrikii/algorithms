package org.bambrikii.examples.sorting;

import org.bambrikii.examples.sorting.bubble.BubbleSort;
import org.bambrikii.examples.sorting.count.CountSort;
import org.bambrikii.examples.sorting.heap.HeapSort;
import org.bambrikii.examples.sorting.insertion.InsertionSort;
import org.bambrikii.examples.sorting.merge.MergeSort;
import org.bambrikii.examples.sorting.my.MyBubbleSort;
import org.bambrikii.examples.sorting.my.MyMergeSort;
import org.bambrikii.examples.sorting.my.MyMinMaxSort;
import org.bambrikii.examples.sorting.my.MySort;
import org.bambrikii.examples.sorting.quick.QuickSort;
import org.bambrikii.examples.sorting.selection.SelectionSort;
import org.bambrikii.examples.sorting.three_pyramids_sort.ThreePyramidsSort;
import org.junit.Test;

import java.util.Random;

public class SortAlgosTest {
    private int[] array = new int[]{5, 8, 1, 0, 9, 4, 3, 2, 7, 6};
    private int[] reversed = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

    private void check(int[] array) throws SortingException {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                throw new SortingException(array, i);
            }
        }
    }

    private void runAlgo(Sortable algo, int[] arr) throws SortingException {
        System.out.println("Algo: " + algo.getClass().getCanonicalName());
        check(algo.sort(arr));
    }

    @Test
    public void testBubbleSort() throws SortingException {
        runAlgo(new BubbleSort(), array);
    }

    @Test
    public void testBubbleSortReversed() throws SortingException {
        runAlgo(new BubbleSort(), reversed);
    }

    @Test
    public void testQuickSort() throws SortingException {
        runAlgo(new QuickSort(), array);
    }

    @Test
    public void testQuickSortReversed() throws SortingException {
        runAlgo(new QuickSort(), reversed);
    }

    @Test
    public void testQuickRandomSort() throws SortingException {
        System.out.println(new Random().nextInt(10));
    }

    @Test
    public void testMySort() throws SortingException {
        runAlgo(new MySort(), array);
    }

    @Test
    public void testMySortReversed() throws SortingException {
        runAlgo(new MySort(), reversed);
    }

    @Test
    public void testMyBubbleSort() throws SortingException {
        runAlgo(new MyBubbleSort(), array);
    }

    @Test
    public void testMyBubbleSortReversed() throws SortingException {
        runAlgo(new MyBubbleSort(), reversed);
    }

    @Test
    public void testMyMergeSort() throws SortingException {
        ArrayAsStringFactory.DEBUG = true;
        runAlgo(new MyMergeSort(), array);
    }

    @Test
    public void testMergeSort() throws SortingException {
        ArrayAsStringFactory.DEBUG = true;
        runAlgo(new MergeSort(), array);
    }

    @Test
    public void testInsertionSort() throws SortingException {
        runAlgo(new InsertionSort(), array);
    }

    @Test
    public void testMyMinMaxSort() throws SortingException {
        runAlgo(new MyMinMaxSort(), array);
    }

    @Test
    public void testThreePyramidsSort() throws SortingException {
        runAlgo(new ThreePyramidsSort(), array);
    }

    @Test
    public void testSelectionSort() throws SortingException {
        ArrayAsStringFactory.DEBUG = true;
        runAlgo(new SelectionSort(), array);
    }

    @Test
    public void testHeapSort() throws SortingException {
        ArrayAsStringFactory.DEBUG = true;
        runAlgo(new HeapSort(), array);
    }

    @Test
    public void testCountSort() throws SortingException {
        runAlgo(new CountSort(), array);
    }
}
