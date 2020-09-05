package org.bambrikii.examples.algorithms.incubator.selforganizinglist.byswapping;

import org.bambrikii.examples.algorithms.incubator.selforganizinglist.AbstractSelfOrganizingList;

public class BySwappingList<K, V> extends AbstractSelfOrganizingList<K, V, BySwappingElement<K, V>> {
	@Override
	public BySwappingElement<K, V> find(K key) {
		if (getFirst() == null) {
			return null;
		}
		BySwappingElement<K, V> elem = getFirst();
		do {
			if (key.equals(elem.getKey())) {
				// swap
				if (getFirst().equals(elem)) {
					return elem;
				}
				BySwappingElement<K, V> prev = elem.getPrev();
				BySwappingElement<K, V> next = elem.getNext();
				BySwappingElement<K, V> prevPrev = prev.getPrev();
				prev.setPrev(elem);
				prev.setNext(next);
				if (next != null) {
					next.setPrev(prev);
				}
				if (prevPrev != null) {
					prevPrev.setNext(elem);
				}

				elem.setPrev(prevPrev);
				elem.setNext(prev);

				if (prevPrev == null) {
					setFirst(elem);
				}
				return elem;
			}
		} while (((elem = elem.getNext()) != null));
		return null;
	}

	@Override
	protected BySwappingElement<K, V> createElement(K key, V value) {
		return new BySwappingElement<>(key, value);
	}
}
