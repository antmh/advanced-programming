package testpackage;

import framework.Test;

public class TestClass {
	@SuppressWarnings("unused")
	private int x;

	@Test
	public static void g() {

	}

	@Test
	public static void f() throws Exception {
		throw new IllegalAccessError();
	}
}
