/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bambrikii.examples.algorithms.incubator.redblackrtee;

import java.util.Comparator;

/**
 *
 * @author asd
 */
public class IntegerComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
	return Integer.compare(o1, o2);
    }
}
