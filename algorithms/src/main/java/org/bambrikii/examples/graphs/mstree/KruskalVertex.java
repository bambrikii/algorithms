package org.bambrikii.examples.graphs.mstree;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class KruskalVertex<E> {
	private E val;
}
