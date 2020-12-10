package org.bambrikii.examples.knapsack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 02/04/17 19:10.
 */
public class Rucksack {
	private List<RucksackItem> items = new ArrayList<>();

	private int capacity;
	private int currentWeight;
	private List<List<RucksackItem>> results = new ArrayList<>();

	public Rucksack(int capacity) {
		this.capacity = capacity;
		this.currentWeight = 0;
	}

	public boolean put(RucksackItem item) {
		if (currentWeight + item.getWeight() <= capacity) {
			items.add(item);
			return true;
		}
		return false;
	}

	public List<List<RucksackItem>> results() {
		return results;
	}
}
