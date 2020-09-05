package org.bambrikii.examples.algorithms.incubator.selforganizinglist;

public abstract class AbstractSelfOrganizingList<K, V, E extends SelfOrganizingListElement<E, K, V>>
		implements SelfOrganizingList<K, V, E> {
	private E first;

	protected E getFirst() {
		return first;
	}

	protected void setFirst(E first) {
		this.first = first;
	}

	protected abstract E createElement(K key, V value);

	@Override
	public E add(K key, V value) {
		if (first == null) {
			first = createElement(key, value);
			return first;
		}
		E last = first;
		while (last.getNext() != null) {
			last = last.getNext();
		}
		E elem = createElement(key, value);
		last.setNext(elem);
		elem.setPrev(last);
		return elem;
	}

	@Override
	public E remove(K key) {
		if (first == null) {
			return null;
		}
		E elem = first;
		while (elem != null) {
			if (elem.getKey().equals(key)) {
				E prev = elem.getPrev();
				E next = elem.getNext();
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
		E elem = first;
		do {
			sb.append(elem).append(" -> ");
		} while ((elem = elem.getNext()) != null);
		return sb.toString();
	}
}
