package org.bambrikii.examples.graphs.mstree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/tutorial/
 * 
 * @param <V>
 * @param <E>
 */
public class KruskalAlgo<V, E extends Comparable<E>> {

	public List<KruskalEdge<E, V>> minimumSpanningTree(KruskalTree<V, E> tree) {
		final Map<KruskalVertex<V>, Integer> vertexesToGroups = new HashMap<>();
		final Map<Integer, Set<KruskalVertex<V>>> groupsToVertexes = new HashMap<>();
		int maxGroups = 0;

		final List<KruskalEdge<E, V>> sortedEdges = new ArrayList<>(tree.edges());
		Collections.sort(sortedEdges);

		final List<KruskalEdge<E, V>> spanningEdges = new ArrayList<>();

		for (KruskalEdge<E, V> edge : sortedEdges) {
			KruskalVertex<V> vertex1 = edge.getVertex1();
			KruskalVertex<V> vertex2 = edge.getVertex2();
			Integer group1 = vertexesToGroups.get(vertex1);
			Integer group2 = vertexesToGroups.get(vertex2);
			if (group1 == null && group2 == null) {
				maxGroups++;
				vertexesToGroups.put(vertex1, maxGroups);
				vertexesToGroups.put(vertex2, maxGroups);
				if (!groupsToVertexes.containsKey(maxGroups)) {
					groupsToVertexes.put(maxGroups, new HashSet<>());
				}
				Set<KruskalVertex<V>> groupToVertexes = groupsToVertexes.get(maxGroups);
				groupToVertexes.add(vertex1);
				groupToVertexes.add(vertex2);
				spanningEdges.add(edge);
				continue;
			}
			if (group2 == null) {
				Integer group2Prev = vertexesToGroups.put(vertex2, group1);
				if (group2Prev != null) {
					groupsToVertexes.get(group2Prev).remove(vertex2);
				}
				groupsToVertexes.get(group1).add(vertex2);
				spanningEdges.add(edge);
				continue;
			}
			if (group1 == null) {
				Integer group1Prev = vertexesToGroups.put(vertex1, group2);
				if (group1Prev != null) {
					groupsToVertexes.get(group1Prev).remove(vertex1);
				}
				groupsToVertexes.get(group2).add(vertex1);
				spanningEdges.add(edge);
				continue;
			}
			if (!group1.equals(group2)) {
				Set<KruskalVertex<V>> groups2 = groupsToVertexes.get(group2);
				groups2.forEach(vertex -> {
					vertexesToGroups.put(vertex, group1);
				});
				groupsToVertexes.get(group1).addAll(groups2);
				groupsToVertexes.remove(group2);
				spanningEdges.add(edge);
				continue;
			}
		}
		return spanningEdges;
	}
}
