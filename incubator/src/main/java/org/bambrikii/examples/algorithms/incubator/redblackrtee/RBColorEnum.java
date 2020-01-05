/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bambrikii.examples.algorithms.incubator.redblackrtee;

/**
 * @author asd
 */
public enum RBColorEnum {
    RED, BLACK;

    public static String toString(RBColorEnum color) {
        return RED.equals(color) ? "R" : BLACK.equals(color) ? "B" : "?";
    }
}
