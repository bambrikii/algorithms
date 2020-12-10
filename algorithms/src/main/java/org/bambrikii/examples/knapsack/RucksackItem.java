package org.bambrikii.examples.knapsack;

/**
 * Created by Alexander Arakelyan on 02/04/17 19:04.
 */
public class RucksackItem {
	private int value;
	private int weight;

	public RucksackItem(int value, int weight) {
		this.value = value;
		this.weight = weight;
	}

	public int getValue() {
		return value;
	}

	public int getWeight() {
		return weight;
	}
}
