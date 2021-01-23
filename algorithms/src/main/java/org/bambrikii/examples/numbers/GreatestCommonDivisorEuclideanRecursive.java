package org.bambrikii.examples.numbers;

public class GreatestCommonDivisorEuclideanRecursive {
	public static void main(String[] args) {
		System.out.println(gcd(12, 8));
		System.out.println(gcd(15, 3));
		System.out.println(gcd(15, 9));
		System.out.println(gcd(24, 36));
		System.out.println(gcd(2, 3));
	}

	static int gcd(int a, int b) {
		if (a == 0) {
			return b;
		}
		if (b == 0) {
			return a;
		}
		int a1;
		int b1;
		if (a > b) {
			a1 = a;
			b1 = b;
		} else {
			a1 = b;
			b1 = a;
		}
		return gcd2(a1, b1);
	}

	static int gcd2(int a, int b) {
		int r = a % b;
		if (r == 0) {
			return b;
		}
		a = b;
		b = r;
		return gcd2(a, b);
	}
}
