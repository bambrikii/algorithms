package org.bambrikii.examples.hashmap;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.bambrikii.examples.graphs.hashmap.HashBasedMap;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HashBasedMapPerformanceTest {
	private HashExtractor hash = new HashExtractor();

	@Parameters(name = "case {index}: size={0}, ratio={1}")
	public static List<Object> data() {
		return Arrays.asList( //
				new Object[] { //
						new Object[] { 10_000, 0.0005 }, //
						new Object[] { 10_000, 0.005 }, //
						new Object[] { 10_000, 0.05 }, //
						new Object[] { 10_000, 0.5 }, //
						new Object[] { 10_000, 1.5 }, //
				} //
		);
	}

	private HashBasedMap<Integer, String> map;

	@Parameter(0)
	public static int size;

	@Parameter(1)
	public static double ratio;

	@Before
	public void before() {
		map = new HashBasedMap<Integer, String>(hash, ratio);
		for (int i = 0; i < size; i++) {
			map.add(i, "val=" + String.valueOf(i));
		}
	}

	@After
	public void after() {
	}

	@Test
	public void should1Add() {
		for (int i = 0; i < size; i++) {
			map.add(i, "val=" + String.valueOf(i));
		}
	}

	@Test
	public void should2Find() {
		for (int i = 0; i < size; i++) {
			map.find(i);
		}
	}

	@Test
	public void should3Remove() {
		for (int i = 0; i < size; i++) {
			map.remove(i);
		}
	}
}
