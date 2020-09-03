package org.bambrikii.examples.algorithms.incubator.selforganizinglist;

import org.junit.Test;

public class SelfOrganizingListTest {
	private void fillAndFindAndPurge(SelfOrganizingList<Integer, String> list) {
		System.out.println("Added " + list.add(1, "one") + "\t=> " + list);
		System.out.println("Added " + list.add(2, "two") + "\t=> " + list);
		System.out.println("Added " + list.add(3, "three") + "\t=> " + list);

		System.out.println("Found " + list.find(1) + "\t=> " + list);
		System.out.println("Found " + list.find(2) + "\t=> " + list);
		System.out.println("Found " + list.find(3) + "\t=> " + list);

		System.out.println("Removed " + list.remove(1) + "\t=> " + list);
		System.out.println("Removed " + list.remove(2) + "\t=> " + list);
		System.out.println("Removed " + list.remove(3) + "\t=> " + list);
	}

	@Test
	public void shouldFillByFirstPosition() {
		fillAndFindAndPurge(new OrganizeByMovingToFirstPosition<Integer, String>());
	}

	@Test
	public void shouldFillByCount() {
		fillAndFindAndPurge(new OrganizeByCount<Integer, String>());
	}

	@Test
	public void shouldFillBySwapping() {
		fillAndFindAndPurge(new OrganizeBySwapping<Integer, String>());
	}
}
