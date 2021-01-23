package org.bambrikii.examples.towersofhanoi;

import java.util.LinkedList;

/**
 * https://www.youtube.com/watch?v=rf6uf3jNjbo
 *
 */
public class TowersOfHanoir {
	public static void main(String[] args) {
		new TowersOfHanoir().build(5).print().order().print();
	}

	private int size;
	private LinkedList<Integer> rod1 = new LinkedList<>();
	private LinkedList<Integer> rod2 = new LinkedList<>();
	private LinkedList<Integer> rod3 = new LinkedList<>();

	TowersOfHanoir build(int size) {
		System.out.println("Building with size " + size + " ...");
		this.size = size;
		for (int i = size; i > 0; i--) {
			rod1.add(i);
		}
		return this;
	}

	TowersOfHanoir print() {
		printRod("Rod1: ", rod1);
		printRod("Rod2: ", rod2);
		printRod("Rod3: ", rod3);
		System.out.println();
		return this;
	}

	private void printRod(String s, LinkedList<Integer> rod) {
		System.out.print(s);
		rod.stream().forEach(elem -> System.out.print(elem + " "));
		System.out.println();
	}

	TowersOfHanoir order() {
		System.out.println("Ordering...");
		orderChildren(rod1, rod3, rod2, size);
		return this;
	}

	private void orderChildren(LinkedList<Integer> from, LinkedList<Integer> to, LinkedList<Integer> mediator, int n) {
		if (n == 3) {
			orderTwoElements(from, mediator, to);
			move(from, to);
			orderTwoElements(mediator, to, from);
		} else {
			orderChildren(from, mediator, to, n - 1);
			move(from, to);
			orderChildren(mediator, to, from, n - 1);
		}
	}

	private void move(LinkedList<Integer> from, LinkedList<Integer> to) {
		to.addLast(from.removeLast());
	}

	private void orderTwoElements(LinkedList<Integer> from, LinkedList<Integer> to, LinkedList<Integer> mediator) {
		move(from, mediator);
		move(from, to);
		move(mediator, to);
	}
}
