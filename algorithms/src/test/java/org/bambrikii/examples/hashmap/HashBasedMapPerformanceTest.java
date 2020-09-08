package org.bambrikii.examples.hashmap;

import java.util.ArrayList;
import java.util.List;

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

	@Parameters(name = "case {index}: size={0}, ratio={1}, threshold={2}")
	public static List<Object> data() {
		List<Object> data = new ArrayList<>();
		for (int size = 100_000; size <= 100_000; size += 10_000) {
			for (double ratio = 0.75; ratio < 0.95; ratio += 0.05) {
				for (int threshold = 6; threshold <= 12; threshold += 1) {
					data.add(new Object[] { size, ratio, threshold });
				}
			}
		}
		return data;
	}

	private HashBasedMap<Integer, String> map;

	@Parameter(0)
	public static int size;

	@Parameter(1)
	public static double ratio;

	@Parameter(2)
	public static int threshold;

	@Before
	public void before() {
		map = new HashBasedMap<Integer, String>(hash, ratio, threshold);
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
