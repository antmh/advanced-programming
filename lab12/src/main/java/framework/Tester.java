package framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class Tester {
	private Class<?> clazz;

	public Tester(Class<?> clazz) {
		this.clazz = clazz;
	}

	public void test() {
		System.out.println("Testing " + clazz.getName());
		var methods = clazz.getMethods();
		for (var method : methods) {
			if (isTestMethod(method)) {
				try {
					method.invoke(null);
				} catch (InvocationTargetException e) {
					System.err.println(method.getName() + " test failed:");
					e.getCause().printStackTrace();
					continue;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				System.out.println(method.getName() + " test passed");
			}
		}
	}

	private boolean isTestMethod(Method method) {
		if (!method.isAnnotationPresent(Test.class)) {
			return false;
		}
		if (!Modifier.isStatic(method.getModifiers())) {
			System.err.println("Cannot annotate with Test non-static methods");
			return false;
		}
		if (method.getParameters().length > 0) {
			System.err.println("Cannot annotate with Test methods with parameters");
			return false;
		}
		return true;
	}
}
