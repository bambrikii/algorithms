package org.bambrikii.examples.graphs.hashmap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HashBasedBucketNode<K, V> {
	private HashBasedElem<K, V> elem;
	private HashBasedBucketNode<K, V> next;
}
