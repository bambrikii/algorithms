package org.bambrikii.examples.graphs.hashmap;

public class HashBasedElem<K, V> {
	private final K key;
	private final V val;

	private HashBasedElem<K, V> next;

	public HashBasedElem(K key, V val) {
		this.key = key;
		this.val = val;
	}

	public K getKey() {
		return key;
	}

	public V getVal() {
		return val;
	}

	protected HashBasedElem<K, V> getNext() {
		return next;
	}

	protected void setNext(HashBasedElem<K, V> next) {
		this.next = next;
	}
}
