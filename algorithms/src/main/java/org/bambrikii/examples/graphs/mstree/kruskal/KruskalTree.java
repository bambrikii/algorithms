package org.bambrikii.examples.graphs.mstree.kruskal;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KruskalTree<V, E extends Comparable<E>> {
	private Map<V, KruskalVertex<V>> vertexes = new HashMap<>();
	private Set<KruskalEdge<E, V>> edges = new HashSet<>();

	public KruskalTree<V, E> edge(E edgeVal, V vertex1Val, V vertex2Val) {
		KruskalVertex<V> vertex1 = ensureVertex(vertex1Val);
		KruskalVertex<V> vertex2 = ensureVertex(vertex2Val);
		ensureEdge(edgeVal, vertex1, vertex2);
		return this;
	}

	private KruskalEdge<E, V> ensureEdge(E edgeVal, KruskalVertex<V> vertex1, KruskalVertex<V> vertex2) {
		KruskalEdge<E, V> edge = new KruskalEdge<>(edgeVal, vertex1, vertex2);
		edges.add(edge);
		return edge;
	}

	private KruskalVertex<V> ensureVertex(V vertexVal) {
		if (!vertexes.containsKey(vertexVal)) {
			vertexes.put(vertexVal, new KruskalVertex<V>(vertexVal));
		}
		KruskalVertex<V> vertex = vertexes.get(vertexVal);
		return vertex;
	}

	public Set<KruskalEdge<E, V>> edges() {
		return Collections.unmodifiableSet(edges);
	}
}
