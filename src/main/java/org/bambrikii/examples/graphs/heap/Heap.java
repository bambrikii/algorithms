package org.bambrikii.examples.graphs.heap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 25/03/17 18:27.
 */
public class Heap<T extends Comparable> {
	private static final Logger LOGGER = LoggerFactory.getLogger(Heap.class);
	private List<T> list = new ArrayList<>();
	private int base;

	public Heap(int base) {
		if (base <= 0) {
			throw new IllegalArgumentException("div should be greater than zero");
		}
		this.base = base;
	}

	public void insert(T elem) {
		list.add(elem);
		percolateUp();
	}

	public T delete() {
		T elem = null;
		int size = list.size();
		if (size >= 1) {
			elem = list.get(0);
			int lastElemIndex = size - 1;
			if (size > 1) {
				list.set(0, list.get(lastElemIndex));
			}
			list.remove(lastElemIndex);
			if (list.size() > 0) {
				percolateDown();
			}
		}
		return elem;
	}

	private void percolateDown() {
		int i = 0;
		T elem = list.get(i);
		int size = list.size();
		while (i < size) {
			int j = i * base + 1;
			int smallestIndex = -1;
			while ((j < (i + 1) * (base) + 1) && (j < size)) {
				if ((smallestIndex == -1) || (list.get(smallestIndex).compareTo(list.get(j)) > 0)) {
					smallestIndex = j;
				}
				j++;
			}
			if (smallestIndex != -1 && elem.compareTo(list.get(smallestIndex)) > 0) {
				LOGGER.debug("{}({}) <-> {}({})", list.get(i), i, list.get(smallestIndex), smallestIndex);
				list.set(i, list.get(smallestIndex));
				list.set(smallestIndex, elem);
				i = smallestIndex;
			} else {
				break;
			}
		}
	}

	private void percolateUp() {
		int currentIndex = list.size() - 1;
		while (currentIndex >= 0) {
			T current = list.get(currentIndex);
			int upperIndex = (currentIndex - 1) / base;
			if (upperIndex >= 0) {
				T upper = list.get(upperIndex);
				if (current.compareTo(upper) < 0) {
					list.set(upperIndex, current);
					list.set(currentIndex, upper);
					currentIndex = upperIndex;
				} else {
					break;
				}
			} else {
				break;
			}
		}
	}

	public Iterator<T> iterator() {
		return list.iterator();
	}

	public int getBase() {
		return base;
	}

	public T getAt(int pos) {
		return pos < list.size() ? list.get(pos) : null;
	}
}
