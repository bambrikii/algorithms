package org.bambrikii.examples.rucksack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 02/04/17 18:20.
 */
public class RucksackProblemUnbounded {
	public static void main(String[] args) {
		RucksackProblemUnbounded problem01 = new RucksackProblemUnbounded();
		List<RucksackItem> items = new ArrayList<>();
		items.add(new RucksackItem(5, 3));
		items.add(new RucksackItem(3, 2));
		items.add(new RucksackItem(2, 1));
		problem01.print(problem01.solve(items, 5));
	}

	public List<List<RucksackItem>> solve(List<RucksackItem> items, int capacityLimit) {
		Collections.sort(items, (o1, o2) -> {
			if (o1.getValue() < o2.getValue()) {
				return 2;
			} else if (o1.getValue() > o2.getValue()) {
				return -2;
			}
			return 0;
		});
		Rucksack rucksack = new Rucksack(capacityLimit);
		for (RucksackItem item : items) {
			boolean succeeded = rucksack.put(item);
			while (succeeded) {
				succeeded = rucksack.put(item);
			}
		}
		return rucksack.results();
	}

	public void print(List<List<RucksackItem>> options) {
		for (List<RucksackItem> items : options) {
			int totalValue = 0;
			int totalWeight = 0;
			for (RucksackItem item : items) {
				totalValue += item.getValue();
				totalWeight += item.getWeight();
				System.out.print(" " + item.getValue() + "[" + item.getWeight() + "] ");
			}
			System.out.println("Total " + totalValue + "[" + totalWeight + "]");
		}
	}

}
