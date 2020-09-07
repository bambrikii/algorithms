package org.bambrikii.examples.hashmap;

import static org.assertj.core.api.Assertions.assertThat;

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
	public void shouldAddAndRemove() {
		HashExtractor hash = new HashExtractor();
		HashBasedMap<Integer, String> map = new HashBasedMap<Integer, String>(hash, 0.85);

		System.out.println("Adding...");
		for (int i = 0; i < 100; i++) {
			System.out.println("\tAdded: " + map.add(i, "val=" + String.valueOf(i)));
		}
		System.out.println(map);
		assertThat(map.size()).isEqualTo(100);

		System.out.println("Searching...");
		assertThat(map.find(1)).extracting("val").contains("val=1");
		assertThat(map.find(50)).extracting("val").contains("val=50");
		assertThat(map.find(99)).extracting("val").contains("val=99");

		System.out.println("Removing...");
		for (int i = 0; i < 100; i++) {
			System.out.println("\tRemoved: " + map.remove(i));
			if (i % 25 == 0) {
				System.out.println(map);
			}
		}
		System.out.println(map);
		assertThat(map.size()).isEqualTo(0);
	}
}
