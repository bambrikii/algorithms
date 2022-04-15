package org.bambrikii.examples.algorithms.incubator.graphs.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KosarajuAlgo {

    private Map<Integer, List<Integer>> v = new LinkedHashMap<>();

    public KosarajuAlgo edge(int from, int to) {
        if (!v.containsKey(from)) {
            v.put(from, new ArrayList<>());
        }
        v.get(from).add(to);
        return this;
    }

    private void print(List<List<Integer>> components) {
        for (List<Integer> component : components) {
            System.out.println(component);
        }
    }

    public static void main(String[] args) {
        KosarajuAlgo algo = new KosarajuAlgo()
                .edge(0, 1).edge(1, 2).edge(2, 0)
                .edge(2, 3)
                .edge(3, 4)
                .edge(4, 5).edge(5, 6).edge(6, 4)
                .edge(6, 7);

        List<List<Integer>> com = algo.find(0);
        algo.print(com);
    }

    private List<List<Integer>> find(int from) {
        LinkedList<Integer> stack = buildStack(from);
        Map<Integer, List<Integer>> reversed = reverse(from);
        List<List<Integer>> components = findComponents(stack, reversed);
        return components;
    }


    private LinkedList<Integer> buildStack(int from) {
        LinkedList<Integer> stack = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        bfs(from, stack, visited);
        visited.clear();
        return stack;
    }

    private void bfs(int from, LinkedList<Integer> stack, Set<Integer> visited) {
        if (visited.contains(from)) {
            return;
        }
        visited.add(from);
        List<Integer> next = v.get(from);
        if (next != null) {
            for (Integer elem : next) {
                bfs(elem, stack, visited);
            }
        }
        stack.add(from);
    }

    private Map<Integer, List<Integer>> reverse(int from) {
        Map<Integer, List<Integer>> reversed = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        bfs(from, reversed, visited);
        return reversed;
    }

    private void bfs(int from, Map<Integer, List<Integer>> reversed, Set<Integer> visited) {
        if (visited.contains(from)) {
            return;
        }
        visited.add(from);
        List<Integer> next = v.get(from);
        if (next != null) {
            for (Integer to : next) {
                if (!reversed.containsKey(to)) {
                    reversed.put(to, new ArrayList<>());
                }
                reversed.get(to).add(from);
                bfs(to, reversed, visited);
            }
        }
    }

    private List<List<Integer>> findComponents(LinkedList<Integer> stack, Map<Integer, List<Integer>> reversed) {
        List<List<Integer>> components = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        while (!stack.isEmpty()) {
            List<Integer> component = new ArrayList<>();
            dfs(stack.pollLast(), stack, reversed, visited, component);
            components.add(component);
        }
        return components;
    }

    private void dfs(Integer from, LinkedList<Integer> stack, Map<Integer, List<Integer>> reversed, Set<Integer> visited, List<Integer> component) {
        if (visited.contains(from)) {
            return;
        }
        visited.add(from);
        stack.remove(from);
        component.add(from);
        List<Integer> edges = reversed.get(from);
        for (Integer to : edges) {
            dfs(to, stack, reversed, visited, component);
        }
    }
}
