package org.bambrikii.examples.selforganizinglists.byswapping;

import org.bambrikii.examples.selforganizinglists.SelfOrganizingListElement;

public class BySwappingElement<K, V> extends SelfOrganizingListElement<BySwappingElement<K, V>, K, V> {
	public BySwappingElement(K key, V value) {
		super(key, value);
	}
}