package org.bambrikii.examples.algorithms.incubator.selforganizinglist;

public interface SelfOrganizingList<K, V> {
	SelfOrganizingListElement<K, V> find(K key);

	SelfOrganizingListElement<K, V> add(K key, V value);

	SelfOrganizingListElement<K, V> remove(K key);
}
