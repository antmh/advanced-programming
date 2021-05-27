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
	private int testsPassed = 0;
	private int testsFailed = 0;

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
					++testsPassed;
				} else {
					System.out.println(method.getName() + " test failed");
					++testsFailed;
				}
			}
		}
	}
	
	private Optional<Object[]> generateArguments(Method method) {
		Object[] arguments = new Object[method.getParameterCount()];
		var parameters = method.getParameters();
		for (int index = 0; index < method.getParameterCount(); ++index) {
			var type = parameters[index].getType();
			if (type.equals(int.class)) {
				arguments[index] = random.nextInt();
			} else if (type.equals(long.class)) {
				arguments[index] = random.nextLong();
			} else if (type.equals(float.class)) {
				arguments[index] = random.nextFloat();
			} else if (type.equals(double.class)) {
				arguments[index] = random.nextDouble();
			} else if (type.equals(boolean.class)) {
				arguments[index] = random.nextBoolean();
			} else if (type.equals(String.class)) {
				arguments[index] = UUID.randomUUID().toString();
			} else {
				return Optional.empty();
			}
		}
		return Optional.of(arguments);
	}

	private boolean callMethod(Method method) {
		var arguments = generateArguments(method);
		if (arguments.isEmpty()) {
			System.err.println("Parameter can either be int, long, float, double, boolean or String");
			return false;
		}
		try {
			if (Modifier.isStatic(method.getModifiers())) {
				method.invoke(null, arguments.get());
			} else {
				if (!instance.isPresent()) {
					instance = Optional.of(clazz.getConstructor().newInstance());
					System.err
							.println("No call a non-static method the class must have a constructor with no arguments");
					return false;
				}
				method.invoke(arguments.get(), instance.get());
			}
		} catch (InvocationTargetException e) {
			e.getCause().printStackTrace();
			return false;
		} catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException e) {
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

	public int getTestsPassed() {
		return testsPassed;
	}

	public int getTestsFailed() {
		return testsFailed;
	}
}
