package org.bambrikii.examples.selforganizinglists.bycount;

import org.bambrikii.examples.selforganizinglists.SelfOrganizingListElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ByCountElement<K, V> extends SelfOrganizingListElement<ByCountElement<K, V>, K, V> {
	private int count;

	public ByCountElement(K key, V value) {
		super(key, value);
	}

	public int incCount() {
		return count += 1;
	}

	@Override
	public String toString() {
		return "[" + getKey() + ":" + getValue() + ":" + count + "]";
	}
}