package framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

class Tester {
	private Class<?> clazz;
	private Optional<Object> instance = Optional.empty();
	private Random random = new Random();

	public Tester(Class<?> clazz) {
		this.clazz = clazz;
	}

	public void test() {
		System.out.println("Testing...");
		var methods = clazz.getMethods();
		for (var method : methods) {
			if (isTestMethod(method)) {
				var passed = callMethod(method);
				System.out.println();
				System.err.flush();
				if (passed) {
					System.out.println(method.getName() + " test passed");
				} else {
					System.out.println(method.getName() + " test failed");
				}
			}
		}
	}

	private boolean callMethod(Method method) {
		Object[] arguments = new Object[method.getParameterCount()];
		var parameters = method.getParameters();
		for (int index = 0; index < method.getParameterCount(); ++index) {
			if (parameters[index].getType().equals(int.class)) {
				arguments[index] = random.nextInt();
			} else if (parameters[index].getType().equals(long.class)) {
				arguments[index] = random.nextLong();
			} else if (parameters[index].getType().equals(float.class)) {
				arguments[index] = random.nextFloat();
			} else if (parameters[index].getType().equals(double.class)) {
				arguments[index] = random.nextDouble();
			} else if (parameters[index].getType().equals(boolean.class)) {
				arguments[index] = random.nextBoolean();
			} else if (parameters[index].getType().equals(String.class)) {
				arguments[index] = UUID.randomUUID().toString();
			} else {
				System.err.println("Parameter can either be int, long, float, double, boolean or String");
				return false;
			}
		}
		try {
			if (Modifier.isStatic(method.getModifiers())) {
				method.invoke(null, arguments);
			} else {
				if (!instance.isPresent()) {
					try {
						instance = Optional.of(clazz.getConstructor().newInstance());
					} catch (NoSuchMethodException e) {
						System.err.println(
								"No call a non-static method the class must have a constructor with no arguments");
						return false;
					} catch (InstantiationException | SecurityException e) {
						e.printStackTrace();
					}
				}
				method.invoke(arguments, instance.get());
			}
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return true;
	}

	private boolean isTestMethod(Method method) {
		if (!method.isAnnotationPresent(Test.class)) {
			return false;
		}
		return true;
	}
}
