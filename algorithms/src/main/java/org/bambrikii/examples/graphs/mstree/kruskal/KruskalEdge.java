package org.bambrikii.examples.graphs.mstree.kruskal;

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

	public E getVal() {
		return val;
	}

	public KruskalVertex<V> getVertex1() {
		return vertex1;
	}

	public KruskalVertex<V> getVertex2() {
		return vertex2;
	}

	@Override
	public int compareTo(KruskalEdge<E, V> arg0) {
		return this.getVal().compareTo(arg0.getVal());
	}
}
