package org.bambrikii.examples.algorithms.incubator.selforganizinglist;

import lombok.Getter;
import lombok.Setter;

public class OrganizeByMovingToFirstPosition<K, V> implements SelfOrganizingList<K, V> {
	private Element first;

	@Override
	public Element find(K key) {
		if (first == null) {
			return null;
		}
		Element elem = first;
		do {
			if (key.equals(elem.getKey())) {
				// move to first position
				if (first.equals(elem)) {
					return elem;
				}
				Element elemNext = elem.getNext();
				Element elemPrev = elem.getPrevious();
				if (elemNext != null) {
					elemNext.setPrevious(elemPrev);
				}
				elemPrev.setNext(elemNext);
				elem.setNext(first);
				elem.setPrevious(null);
				first.setPrevious(elem);
				first = elem;
				return elem;
			}
		} while (((elem = elem.getNext()) != null));
		return null;
	}

	@Override
	public Element add(K key, V value) {
		Element added = new Element(key, value);
		if (first == null) {
			first = added;
			return first;
		}

		Element last = first;
		Element nxt;
		while ((nxt = last.getNext()) != null) {
			last = nxt;
		}
		last.setNext(added);
		added.setPrevious(last);
		return added;
	}

	@Override
	public Element remove(K key) {
		Element elem = find(key);
		if (elem == null) {
			return null;
		}
		Element next = elem.getNext();
		if (elem.equals(first)) { // first
			if (next != null) {
				next.setPrevious(null);
			}
			first = next;
		} else {
			Element prev = elem.getPrevious();
			if (next != null) { // last
				next.setPrevious(prev);
			}
			prev.setNext(next);
		}
		return elem;
	}

	@Getter
	@Setter
	private class Element extends SelfOrganizingListElement<K, V> {
		private Element previous;
		private Element next;

		public Element(K key, V value) {
			super(key, value);
		}

		public String toString() {
			return "[" + getKey() + ":" + getValue() + "]";
		}
	}

	public String toString() {
		if (first == null) {
			return "null ->";
		}
		StringBuilder sb = new StringBuilder();
		Element elem = first;
		do {
			sb.append(elem).append(" -> ");
		} while ((elem = elem.getNext()) != null);
		return sb.toString();
	}
}
