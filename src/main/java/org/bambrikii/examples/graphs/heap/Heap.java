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
	private int width;

	public Heap(int width) {
		if (width <= 0) {
			throw new IllegalArgumentException("div should be greater than zero");
		}
		this.width = width;
	}

	public void insert(T elem) {
		list.add(0, elem);
		percolateDown();
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
				percolateUp();
			}
		}
		return elem;
	}

	private void percolateDown() {
		int i = 0;
		T elem = list.get(i);
		int size = list.size();
		while (i < size) {
			int j = i * width + 1;
			int smallestIndex = -1;
			while ((j < (i + 1) * (width) + 1) && (j < size)) {
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
		int index = list.size() - 1;
		T elem = list.get(index);
		while (index >= 0) {
			int index2 = index / width;
			if (index2 >= 0) {
				if (elem.compareTo(list.get(index2)) < 0) {
					T temp = list.get(index);
					list.set(index, elem);
					list.set(index2, temp);
					index = index2;
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

	public int getWidth() {
		return width;
	}

	public T getAt(int pos) {
		return pos < list.size() - 1 ? list.get(pos) : null;
	}
}
