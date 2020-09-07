package org.bambrikii.examples.graphs.hashmap;

import java.math.BigDecimal;
import java.text.MessageFormat;

public class HashBasedMap<K, V> {
	private final int ratioPrecision;
	private final HashExtractable<K> hash;
	private final double ratio;
	private int size;
	private HashBasedBucketNode<K, V>[] buckets = new HashBasedBucketNode[1];

	public HashBasedMap(HashExtractable<K> hash, double ratio) {
		this.hash = hash;
		this.ratio = ratio;
		ratioPrecision = (int) Math.pow(10, BigDecimal.valueOf(ratio).precision());
	}

	private int index(K key, HashBasedBucketNode<K, V>[] buckets) {
		int hashCode = hash.hash(key);
		int i = hashCode % buckets.length;
		return i;
	}

	private HashBasedElem<K, V> add0(K key, V val, HashBasedBucketNode<K, V>[] buckets) {
		HashBasedBucketNode<K, V> node = new HashBasedBucketNode<>();
		HashBasedElem<K, V> elem = new HashBasedElem<>(key, val);
		node.setElem(elem);

		int i = index(key, buckets);

		HashBasedBucketNode<K, V> next = buckets[i];
		if (next == null) {
			buckets[i] = node;
			return elem;
		} else {
			HashBasedBucketNode<K, V> nextPrev = null;
			while (next != null) {
				int cmp = hash.compare(next.getElem().getKey(), key);
				if (cmp < 0) {
					nextPrev = next;
					next = next.getNext();
					if (next == null) {
						nextPrev.setNext(node);
						return elem;
					}
				} else if (cmp > 0) { // adding
					if (nextPrev != null) {
						nextPrev.setNext(node);
					} else {
						buckets[i] = node;
					}
					node.setNext(next);
					return elem;
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
	}

	public HashBasedElem<K, V> add(K key, V val) {
		HashBasedElem<K, V> elem = add0(key, val, this.buckets);
		if (elem != null) {
			rebalance(1);
		}
		return elem;
	}

	private HashBasedElem<K, V> remove0(K key, HashBasedBucketNode<K, V>[] buckets) {
		HashBasedBucketNode<K, V> node = null;
		int i = index(key, buckets);
		HashBasedBucketNode<K, V> next = buckets[i];
		if (next == null) {
			return null;
		}
		HashBasedBucketNode<K, V> nextPrev = null;
		while (next != null) {
			int cmp = hash.compare(next.getElem().getKey(), key);
			HashBasedBucketNode<K, V> nextNext = next.getNext();
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
		return node != null ? node.getElem() : null;
	}

	public HashBasedElem<K, V> remove(K key) {
		HashBasedElem<K, V> elem = remove0(key, this.buckets);
		if (elem != null) {
			rebalance(-1);
		}
		return elem;
	}

	public HashBasedElem<K, V> find(K key) {
		HashBasedBucketNode<K, V>[] buckets = this.buckets;
		int i = index(key, buckets);
		HashBasedBucketNode<K, V> next = buckets[i];
		if (next == null) {
			return null;
		}
		while (next != null) {
			int cmp = hash.compare(next.getElem().getKey(), key);
			if (cmp == 0) {
				return next.getElem();
			}
			next = next.getNext();
		}
		return null;
	}

	private void rebalance(int n) {
		// TODO:
		if (n == 0) {
			return;
		}
		size = size + ((n > 0) ? 1 : -1);
		int nextBucketsCount = (int) Math.ceil(((ratio * ratioPrecision * size) / ratioPrecision));
		HashBasedBucketNode<K, V>[] oldBuckets = this.buckets;
		if (nextBucketsCount == oldBuckets.length) {
			return;
		}
		HashBasedBucketNode<K, V>[] newBuckets = new HashBasedBucketNode[nextBucketsCount];
		int nextSize = 0;
		for (HashBasedBucketNode<K, V> bucket : oldBuckets) {
			while (bucket != null) {
				HashBasedElem<K, V> elem = bucket.getElem();
				add0(elem.getKey(), elem.getVal(), newBuckets);
				bucket = bucket.getNext();
				nextSize++;
			}
		}
		this.buckets = newBuckets;
		if (nextSize != size) {
			throw new IllegalStateException(
					MessageFormat.format("Expected size {0} != actual size {1} !", size, nextSize));
		}
//		size = nextSize;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < buckets.length; i++) {
			HashBasedBucketNode<K, V> bucket = buckets[i];
			sb.append(System.lineSeparator()).append("\t[").append(i).append(": ");
			while (bucket != null) {
				HashBasedElem<K, V> elem = bucket.getElem();
				sb.append("key=").append(elem.getKey()).append(":").append(hash.hash(elem.getKey())).append(", val=")
						.append(elem.getVal()).append("\t");
				bucket = bucket.getNext();
			}
			sb.append("]");
		}
		sb.append(System.lineSeparator());
		sb.append("]");
		return sb.toString();
	}
}
