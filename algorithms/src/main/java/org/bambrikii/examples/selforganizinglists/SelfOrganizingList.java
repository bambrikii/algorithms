package org.bambrikii.examples.selforganizinglists;

public interface SelfOrganizingList<K, V, E extends SelfOrganizingListElement<E, K, V>> {
	E find(K key);

	E add(K key, V value);

	E remove(K key);
}
