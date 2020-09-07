package org.bambrikii.examples.graphs.hashmap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class HashBasedElem<K, V> {
	private final K key;
	private final V val;

}
