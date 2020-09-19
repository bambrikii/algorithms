package org.bambrikii.examples.mstree;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class KruskalEdge<E extends Comparable<E>, V> implements Comparable<KruskalEdge<E, V>> {
	private E val;
	private KruskalVertex<V> vertex1;
	private KruskalVertex<V> vertex2;

	@Override
	public int compareTo(KruskalEdge<E, V> arg0) {
		return this.getVal().compareTo(arg0.getVal());
	}
}
