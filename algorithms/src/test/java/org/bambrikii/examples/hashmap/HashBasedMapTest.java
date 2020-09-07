package org.bambrikii.examples.hashmap;

import org.bambrikii.examples.graphs.hashmap.HashBasedMap;
import org.bambrikii.examples.graphs.hashmap.HashExtractable;
import org.junit.Test;

public class HashBasedMapTest {
	static class HashExtractor implements HashExtractable<Integer> {
		@Override
		public int hash(Integer key) {
			return key.hashCode();
		}

		@Override
		public int compare(Integer left, Integer right) {
			if (left == null && right == null) {
				return 0;
			}
			if (left == null) {
				return -1;
			}
			if (right == null) {
				return 1;
			}
			return left.compareTo(right);
		}
	}

	@Test
	public void shouldAdd() {
		HashExtractor hash = new HashExtractor();
		HashBasedMap<Integer, String> map = new HashBasedMap<Integer, String>(hash, 0.85);

		System.out.println("Adding...");
		for (int i = 0; i < 100; i++) {
			System.out.println("\tAdded: " + map.add(i, "val=" + String.valueOf(i)));
		}
		System.out.println(map);

		System.out.println("Removing...");
		for (int i = 0; i < 100; i++) {
			System.out.println("\tRemoved: " + map.remove(i));
			if (i % 25 == 0) {
				System.out.println(map);
			}
		}
		System.out.println(map);
	}
}
