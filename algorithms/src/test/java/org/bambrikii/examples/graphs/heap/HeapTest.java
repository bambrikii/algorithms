package org.bambrikii.examples.graphs.heap;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Alexander Arakelyan on 25/03/17 21:25.
 */
public class HeapTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeapTest.class);

    private void print(Heap<HeapSampleElement> heap) {
        System.out.print(">>> ");
        Iterator<HeapSampleElement> iterator = heap.iterator();
        while (iterator.hasNext()) {
            HeapSampleElement next = iterator.next();
            System.out.print(next + " ");
        }
        System.out.println();
    }

    private void assertHeap(Heap heap) {
        assertHeap(heap, 0);
    }

    private void assertHeap(Heap heap, int pos) {
        int width = heap.getBase();
        Comparable heapAt = heap.getAt(pos);
        for (int i = 0; i < width; i++) {
            int pos2 = pos * width + 1 + i;
            Comparable elem = heap.getAt(pos2);
            if (elem != null) {
                LOGGER.debug("Assert: {}({}) - {}({})", heapAt, pos, elem, pos2);
                assertHeap(heap, pos2);
                assertTrue(heapAt.compareTo(elem) <= 0, MessageFormat.format("{0}({1}) !< {2}({3})", heapAt, pos, elem, pos2));
            }
        }
    }

    @Test
    public void testHeapInsert() {
        Heap<HeapSampleElement> heap = new Heap<>(2);
        heap.insert(new HeapSampleElement(3));
        print(heap);
        heap.insert(new HeapSampleElement(4));
        print(heap);
        heap.insert(new HeapSampleElement(12));
        print(heap);
        heap.insert(new HeapSampleElement(3));
        print(heap);
        heap.insert(new HeapSampleElement(5));
        print(heap);
        heap.insert(new HeapSampleElement(5));
        print(heap);
        heap.insert(new HeapSampleElement(4));
        print(heap);
        assertHeap(heap);
        heap.insert(new HeapSampleElement(8));
        print(heap);
        assertHeap(heap);
    }

    @Test
    public void testHeapInsert3() {
        Heap<HeapSampleElement> heap = new Heap<>(2);
        heap.insert(new HeapSampleElement(7));
        print(heap);
        heap.insert(new HeapSampleElement(6));
        print(heap);
        heap.insert(new HeapSampleElement(8));
        print(heap);
        heap.insert(new HeapSampleElement(12));
        print(heap);
        heap.insert(new HeapSampleElement(1));
        print(heap);
        assertHeap(heap);
        heap.insert(new HeapSampleElement(2));
        print(heap);
        assertHeap(heap);
    }

    @Test
    public void testHeapDelete() {
        Heap<HeapSampleElement> heap = new Heap<>(2);
        randomFill(heap, 15);
        heap.insert(new HeapSampleElement(2));
        heap.insert(new HeapSampleElement(1));
        assertHeap(heap);
        heap.delete();
        print(heap);
        assertHeap(heap);
    }

    @Test
    public void testHeap() {
        Heap<HeapSampleElement> heap = new Heap<>(2);
        randomFill(heap, 15);
        assertHeap(heap);
        System.out.print(">>> ");
        HeapSampleElement elem = heap.delete();
        while (elem != null) {
            System.out.print(elem + " ");
            HeapSampleElement elem2 = heap.delete();
            if (elem2 != null) {
                assertTrue(elem.compareTo(elem2) <= 0);
            }
            elem = elem2;
        }
    }

    private void randomFill(Heap<HeapSampleElement> heap, int n) {
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            HeapSampleElement elem = new HeapSampleElement(rand.nextInt(n));
            System.out.println(elem);
            heap.insert(elem);
            print(heap);
            assertHeap(heap);
        }
    }


    @Test
    public void testHeapInsert2() {
        Heap<HeapSampleElement> heap = new Heap<>(2);
        heap.insert(new HeapSampleElement(12));
        print(heap);
        heap.insert(new HeapSampleElement(7));
        print(heap);
        heap.insert(new HeapSampleElement(10));
        print(heap);
        heap.insert(new HeapSampleElement(1));
        print(heap);
        heap.insert(new HeapSampleElement(12));
        print(heap);
        assertHeap(heap);
    }

    @Test
    public void testHeapWithBase3() {
        Heap<HeapSampleElement> heap = new Heap<>(3);
        randomFill(heap, 15);
        print(heap);
        assertHeap(heap);
        for (int i = 0; i < 5; i++) {
            LOGGER.debug("Top of the heap: {} / {} ", heap.delete(), i);
        }
    }
}
