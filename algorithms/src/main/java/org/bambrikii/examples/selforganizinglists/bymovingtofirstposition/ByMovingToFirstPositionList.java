package org.bambrikii.examples.selforganizinglists.bymovingtofirstposition;

import org.bambrikii.examples.selforganizinglists.AbstractSelfOrganizingList;

public class ByMovingToFirstPositionList<K, V>
		extends AbstractSelfOrganizingList<K, V, ByFirstPositionElementElement<K, V>> {

	@Override
	public ByFirstPositionElementElement<K, V> find(K key) {
		if (getFirst() == null) {
			return null;
		}
		ByFirstPositionElementElement<K, V> elem = getFirst();
		do {
			if (key.equals(elem.getKey())) {
				// move to first position
				if (getFirst().equals(elem)) {
					return elem;
				}
				ByFirstPositionElementElement<K, V> elemNext = elem.getNext();
				ByFirstPositionElementElement<K, V> elemPrev = elem.getPrev();
				if (elemNext != null) {
					elemNext.setPrev(elemPrev);
				}
				elemPrev.setNext(elemNext);
				elem.setNext(getFirst());
				elem.setPrev(null);
				getFirst().setPrev(elem);
				setFirst(elem);
				return elem;
			}
		} while (((elem = elem.getNext()) != null));
		return null;
	}

	@Override
	protected ByFirstPositionElementElement<K, V> createElement(K key, V value) {
		return new ByFirstPositionElementElement<>(key, value);
	}
}
