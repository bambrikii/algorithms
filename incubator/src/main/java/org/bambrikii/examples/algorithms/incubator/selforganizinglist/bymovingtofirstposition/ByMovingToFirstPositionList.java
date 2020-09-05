package org.bambrikii.examples.algorithms.incubator.selforganizinglist.bymovingtofirstposition;

import org.bambrikii.examples.algorithms.incubator.selforganizinglist.SelfOrganizingList;

public class ByMovingToFirstPositionList<K, V>
		implements SelfOrganizingList<K, V, ByFirstPositionElementElement<K, V>> {
	private ByFirstPositionElementElement<K, V> first;

	@Override
	public ByFirstPositionElementElement<K, V> find(K key) {
		if (first == null) {
			return null;
		}
		ByFirstPositionElementElement<K, V> elem = first;
		do {
			if (key.equals(elem.getKey())) {
				// move to first position
				if (first.equals(elem)) {
					return elem;
				}
				ByFirstPositionElementElement<K, V> elemNext = elem.getNext();
				ByFirstPositionElementElement<K, V> elemPrev = elem.getPrev();
				if (elemNext != null) {
					elemNext.setPrev(elemPrev);
				}
				elemPrev.setNext(elemNext);
				elem.setNext(first);
				elem.setPrev(null);
				first.setPrev(elem);
				first = elem;
				return elem;
			}
		} while (((elem = elem.getNext()) != null));
		return null;
	}

	@Override
	public ByFirstPositionElementElement<K, V> add(K key, V value) {
		ByFirstPositionElementElement<K, V> added = new ByFirstPositionElementElement<>(key, value);
		if (first == null) {
			first = added;
			return first;
		}

		ByFirstPositionElementElement<K, V> last = first;
		ByFirstPositionElementElement<K, V> nxt;
		while ((nxt = last.getNext()) != null) {
			last = nxt;
		}
		last.setNext(added);
		added.setPrev(last);
		return added;
	}

	@Override
	public ByFirstPositionElementElement<K, V> remove(K key) {
		ByFirstPositionElementElement<K, V> elem = find(key);
		if (elem == null) {
			return null;
		}
		ByFirstPositionElementElement<K, V> next = elem.getNext();
		if (elem.equals(first)) { // first
			if (next != null) {
				next.setPrev(null);
			}
			first = next;
		} else {
			ByFirstPositionElementElement<K, V> prev = elem.getPrev();
			if (next != null) { // last
				next.setPrev(prev);
			}
			prev.setNext(next);
		}
		return elem;
	}

	public String toString() {
		if (first == null) {
			return "null ->";
		}
		StringBuilder sb = new StringBuilder();
		ByFirstPositionElementElement<K, V> elem = first;
		do {
			sb.append(elem).append(" -> ");
		} while ((elem = elem.getNext()) != null);
		return sb.toString();
	}
}
