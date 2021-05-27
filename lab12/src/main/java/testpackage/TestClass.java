package testpackage;

import framework.Test;

@Test
public class TestClass {
	@SuppressWarnings("unused")
	private int x;

	@Test
	public static void g(int x, String y) {
		System.out.println(x);
		System.out.println(y);
	}

	@Test
	public static void f(float x) throws Exception {
		throw new Exception(Float.toString(x));
	}
}
