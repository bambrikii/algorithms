package org.bambrikii.examples.algorithms.incubator.selforganizinglist.bycount;

import org.bambrikii.examples.algorithms.incubator.selforganizinglist.SelfOrganizingList;

public class ByCountList<K, V> implements SelfOrganizingList<K, V, ByCountElement<K, V>> {
	private ByCountElement<K, V> first;

	@Override
	public ByCountElement<K, V> find(K key) {
		if (first == null) {
			return null;
		}
		ByCountElement<K, V> elem = first;
		do {
			if (key.equals(elem.getKey())) {
				elem.incCount();

				// move to first position
				if (first.equals(elem)) {
					return elem;
				}

				if (elem.equals(first)) {
					return elem;
				}

				ByCountElement<K, V> greater = elem.getPrev();
				while (greater != null && greater.getCount() < elem.getCount()) {
					greater = greater.getPrev();
				}

				if (elem.getPrev() == greater) {
					return elem;
				}

				ByCountElement<K, V> elemNext = elem.getNext();
				ByCountElement<K, V> elemPrev = elem.getPrev();

				// moving to root with smaller value
				if (greater == null) {
					if (elemNext != null) {
						elemNext.setPrev(elemPrev);
					}
					elemPrev.setNext(elemNext);
					elem.setPrev(null);
					elem.setNext(first);
					first.setPrev(elem);
					first = elem;
					return elem;
				}

				// switching with element with smaller value
				if (elemNext != null) {
					elemNext.setPrev(elemPrev);
				}
				elemPrev.setNext(elemNext);
				elem.setPrev(greater);
				elem.setNext(greater.getNext());
				greater.setNext(elem);
				return elem;
			}
		} while (((elem = elem.getNext()) != null));
		return null;
	}

	@Override
	public ByCountElement<K, V> add(K key, V value) {
		if (first == null) {
			first = new ByCountElement<>(key, value);
			return first;
		}
		ByCountElement<K, V> last = first;
		while (last.getNext() != null) {
			last = last.getNext();
		}
		ByCountElement<K, V> elem = new ByCountElement<>(key, value);
		last.setNext(elem);
		elem.setPrev(last);
		return elem;
	}

	@Override
	public ByCountElement<K, V> remove(K key) {
		if (first == null) {
			return null;
		}
		ByCountElement<K, V> elem = first;
		while (elem != null) {
			if (elem.getKey().equals(key)) {
				ByCountElement<K, V> prev = elem.getPrev();
				ByCountElement<K, V> next = elem.getNext();
				if (prev != null) {
					prev.setNext(next);
				} else {
					first = next;
				}
				if (next != null) {
					next.setPrev(prev);
				}
				return elem;
			}
			elem = elem.getNext();
		}
		return null;
	}

	public String toString() {
		if (first == null) {
			return "null ->";
		}
		StringBuilder sb = new StringBuilder();
		ByCountElement<K, V> elem = first;
		do {
			sb.append(elem).append(" -> ");
		} while ((elem = elem.getNext()) != null);
		return sb.toString();
	}
}
