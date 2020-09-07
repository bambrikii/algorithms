package org.bambrikii.examples.graphs.hashmap;

public interface HashExtractable<K> {
	int hash(K key);

	int compare(K left, K right);
}
