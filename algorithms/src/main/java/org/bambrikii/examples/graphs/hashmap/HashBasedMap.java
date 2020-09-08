package org.bambrikii.examples.graphs.hashmap;

import java.math.BigDecimal;
import java.text.MessageFormat;

public class HashBasedMap<K, V> {
	private final int ratioPrecision;
	private final HashExtractable<K> hash;
	private final double ratio;
	private final double threshold;
	private int size;
	private HashBasedElem<K, V>[] buckets = new HashBasedElem[1];

	public HashBasedMap(HashExtractable<K> hash) {
		this(hash, 0.9, 2.0);
	}

	public HashBasedMap(HashExtractable<K> hash, double ratio) {
		this(hash, ratio, 2.0);
	}

	public HashBasedMap(HashExtractable<K> hash, double ratio, double threshold) {
		this.hash = hash;
		this.ratio = ratio;
		this.threshold = threshold;
		ratioPrecision = (int) Math.pow(10, BigDecimal.valueOf(ratio).precision());
	}

	private int index(K key, HashBasedElem<K, V>[] buckets) {
		int hashCode = hash.hash(key);
		int i = hashCode % buckets.length;
		return i;
	}

	private HashBasedElem<K, V> add0(K key, V val, HashBasedElem<K, V>[] buckets) {
		HashBasedElem<K, V> node = new HashBasedElem(key, val);

		int i = index(key, buckets);

		HashBasedElem<K, V> next = buckets[i];
		if (next == null) {
			buckets[i] = node;
			return node;
		}
		HashBasedElem<K, V> nextPrev = null;
		while (next != null) {
			int cmp = hash.compare(next.getKey(), key);
			if (cmp < 0) {
				nextPrev = next;
				next = next.getNext();
				if (next == null) {
					nextPrev.setNext(node);
					return node;
				}
			} else if (cmp > 0) { // adding
				if (nextPrev != null) {
					nextPrev.setNext(node);
				} else {
					buckets[i] = node;
				}
				node.setNext(next);
				return node;
			} else { // replacing
				if (nextPrev != null) {
					nextPrev.setNext(node);
				} else {
					buckets[i] = node;
				}
				node.setNext(next.getNext());
				return null;
			}
		}
		return null;
	}

	public HashBasedElem<K, V> add(K key, V val) {
		HashBasedElem<K, V> elem = add0(key, val, this.buckets);
		if (elem != null) {
			rebalance(1);
		}
		return elem;
	}

	private HashBasedElem<K, V> remove0(K key, HashBasedElem<K, V>[] buckets) {
		HashBasedElem<K, V> node = null;
		int i = index(key, buckets);
		HashBasedElem<K, V> next = buckets[i];
		if (next == null) {
			return null;
		}
		HashBasedElem<K, V> nextPrev = null;
		while (next != null) {
			int cmp = hash.compare(next.getKey(), key);
			HashBasedElem<K, V> nextNext = next.getNext();
			if (cmp == 0) {
				if (nextPrev == null) {
					buckets[i] = nextNext;
				} else {
					nextPrev.setNext(nextNext);
				}
				node = next;
				break;
			}
			nextPrev = next;
			next = nextNext;
		}
		if (node == null) {
			return null;
		}
		return node;
	}

	public HashBasedElem<K, V> remove(K key) {
		HashBasedElem<K, V> elem = remove0(key, this.buckets);
		if (elem != null) {
			rebalance(-1);
		}
		return elem;
	}

	public HashBasedElem<K, V> find(K key) {
		HashBasedElem<K, V>[] buckets = this.buckets;
		int i = index(key, buckets);
		HashBasedElem<K, V> next = buckets[i];
		if (next == null) {
			return null;
		}
		while (next != null) {
			int cmp = hash.compare(next.getKey(), key);
			if (cmp == 0) {
				return next;
			}
			next = next.getNext();
		}
		return null;
	}

	private void rebalance(int n) {
		if (n == 0) {
			return;
		}
		size = size + ((n > 0) ? 1 : -1);
		int nextBucketsCount = (int) (((ratio * ratioPrecision * size) / ratioPrecision));
		HashBasedElem<K, V>[] oldBuckets = this.buckets;
		if (Math.abs(((nextBucketsCount - oldBuckets.length) / (oldBuckets.length + 1.0))) < threshold) {
			return;
		}
		HashBasedElem<K, V>[] newBuckets = new HashBasedElem[nextBucketsCount];
		int nextSize = 0;
		for (HashBasedElem<K, V> bucket : oldBuckets) {
			while (bucket != null) {
				add0(bucket.getKey(), bucket.getVal(), newBuckets);
				bucket = bucket.getNext();
				nextSize++;
			}
		}
		this.buckets = newBuckets;
		if (nextSize != size) {
			throw new IllegalStateException(
					MessageFormat.format("Expected size {0} != actual size {1} !", size, nextSize));
		}
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < buckets.length; i++) {
			HashBasedElem<K, V> bucket = buckets[i];
			sb.append(System.lineSeparator()).append("\t[").append(i).append(": ");
			while (bucket != null) {
				sb.append("key=").append(bucket.getKey()).append(":").append(hash.hash(bucket.getKey()))
						.append(", val=").append(bucket.getVal()).append("\t");
				bucket = bucket.getNext();
			}
			sb.append("]");
		}
		sb.append(System.lineSeparator());
		sb.append("]");
		return sb.toString();
	}
}
