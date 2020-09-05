package org.bambrikii.examples.algorithms.incubator.selforganizinglist.bycount;

import org.bambrikii.examples.algorithms.incubator.selforganizinglist.AbstractSelfOrganizingList;

public class ByCountList<K, V> extends AbstractSelfOrganizingList<K, V, ByCountElement<K, V>> {
	@Override
	public ByCountElement<K, V> find(K key) {
		if (getFirst() == null) {
			return null;
		}
		ByCountElement<K, V> elem = getFirst();
		do {
			if (key.equals(elem.getKey())) {
				elem.incCount();

				// move to first position
				if (getFirst().equals(elem)) {
					return elem;
				}

				if (elem.equals(getFirst())) {
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
					elem.setNext(getFirst());
					getFirst().setPrev(elem);
					setFirst(elem);
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
	protected ByCountElement<K, V> createElement(K key, V value) {
		return new ByCountElement<>(key, value);
	}
}
