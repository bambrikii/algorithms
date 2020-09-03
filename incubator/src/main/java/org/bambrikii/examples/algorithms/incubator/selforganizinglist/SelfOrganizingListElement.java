package org.bambrikii.examples.algorithms.incubator.selforganizinglist;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SelfOrganizingListElement<K, V> {
	private final K key;
	private final V value;
}
