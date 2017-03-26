package org.bambrikii.examples.graphs.heap;

import org.junit.Test;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alexander Arakelyan on 25/03/17 21:25.
 */
public class HeapTest {
	public static class Cls1 implements Comparable {
		private final int val1;

		private Cls1(int val1) {
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
			if (!obj.getClass().equals(Cls1.class)) {
				return 1;
			}
			Cls1 other = (Cls1) obj;
			return val1 - other.val1;
		}

		@Override
		public String toString() {
			return String.valueOf(val1);
		}
	}

	private void print(Heap<Cls1> heap) {
		System.out.print(">>> ");
		Iterator<Cls1> iterator = heap.iterator();
		while (iterator.hasNext()) {
			Cls1 next = iterator.next();
			System.out.print(next + " ");
		}
		System.out.println();
	}

	private void assertHeap(Heap heap) {
		assertHeap(heap, 0);
	}

	private void assertHeap(Heap heap, int pos) {
		int width = heap.getWidth();
		Comparable heapAt = heap.getAt(pos);
		for (int i = 0; i < width; i++) {
			int pos2 = pos * width + 1 + i;
			Comparable elem = heap.getAt(pos2);
			if (elem != null) {
				assertTrue(MessageFormat.format("{0}({1}) !< {2}({3})", heapAt, pos, elem, pos2), heapAt.compareTo(elem) <= 0);
				assertHeap(heap, pos2);
			}
		}
	}

	@Test
	public void testHeapInsert() {
		Heap<Cls1> heap = new Heap<>(2);
		heap.insert(new Cls1(21));
		print(heap);
		heap.insert(new Cls1(15));
		print(heap);
		heap.insert(new Cls1(8));
		print(heap);
		heap.insert(new Cls1(2));
		print(heap);
		heap.insert(new Cls1(5));
		print(heap);
		heap.insert(new Cls1(1));
		print(heap);
		heap.insert(new Cls1(3));
		print(heap);
		assertHeap(heap);
	}

	@Test
	public void testHeapDelete() {
		Heap<Cls1> heap = new Heap<>(2);
		randomFill(heap, 2);
		heap.insert(new Cls1(2));
		heap.insert(new Cls1(1));
		assertHeap(heap);
		heap.delete();
		print(heap);
		assertHeap(heap);
	}

	@Test
	public void testHeap() {
		Heap<Cls1> heap = new Heap<>(2);
		randomFill(heap, 15);
		System.out.print(">>> ");
		Cls1 elem = heap.delete();
		while (elem != null) {
			System.out.print(elem + " ");
			elem = heap.delete();
		}
	}

	private void randomFill(Heap<Cls1> heap, int n) {
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			Cls1 elem = new Cls1(rand.nextInt(n));
			System.out.println(elem);
			heap.insert(elem);
			print(heap);
			assertHeap(heap);
		}
	}


	@Test
	public void testHeapInsert2() {
		Heap<Cls1> heap = new Heap<>(2);
		heap.insert(new Cls1(12));
		print(heap);
		heap.insert(new Cls1(7));
		print(heap);
		heap.insert(new Cls1(10));
		print(heap);
		heap.insert(new Cls1(1));
		print(heap);
		heap.insert(new Cls1(12));
		print(heap);
		assertHeap(heap);
	}
}
