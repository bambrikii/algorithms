package org.bambrikii.examples.selforganizinglists.bymovingtofirstposition;

import org.bambrikii.examples.selforganizinglists.SelfOrganizingListElement;

public class ByFirstPositionElementElement<K, V> extends SelfOrganizingListElement<ByFirstPositionElementElement<K, V>, K, V> {
	public ByFirstPositionElementElement(K key, V value) {
		super(key, value);
	}
}