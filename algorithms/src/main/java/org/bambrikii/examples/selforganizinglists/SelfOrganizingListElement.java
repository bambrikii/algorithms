package org.bambrikii.examples.selforganizinglists;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelfOrganizingListElement<E extends SelfOrganizingListElement<?, K, V>, K, V> {
	@Setter(AccessLevel.NONE)
	private final K key;
	@Setter(AccessLevel.NONE)
	private final V value;

	private E prev;
	private E next;

	public SelfOrganizingListElement(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public String toString() {
		return "[" + getKey() + ":" + getValue() + "]";
	}
}
