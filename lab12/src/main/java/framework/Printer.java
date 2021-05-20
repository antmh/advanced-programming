package framework;

import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

class Printer {
	private Class<?> clazz;

	public Printer(Class<?> clazz) {
		this.clazz = clazz;
	}

	public void print() {
		System.out.println("Class name: " + clazz.getName());
		System.out.println("Package name: " + clazz.getPackageName());
		System.out.println("Parent: " + clazz.getSuperclass().getName());
		printConstructors();
		printMethods();
		printFields();
	}

	private void printConstructors() {
		System.out.println("Constructors:");
		var constructors = clazz.getConstructors();
		for (var constructor : constructors) {
			System.out.print(Modifier.toString(constructor.getModifiers()) + " ");
			System.out.print(constructor.getName());
			printParameters(constructor.getParameters());
			printExceptions(constructor.getExceptionTypes());
			System.out.println();
		}
	}

	private void printMethods() {
		System.out.println("Methods:");
		var methods = clazz.getMethods();
		for (var method : methods) {
			System.out.print(Modifier.toString(method.getModifiers()) + " ");
			System.out.print(method.getReturnType().getName() + " " + method.getName());
			printParameters(method.getParameters());
			printExceptions(method.getExceptionTypes());
			System.out.println();
		}
	}

	private void printParameters(Parameter[] parameters) {
		System.out.print("(");
		for (var parameterIndex = 0; parameterIndex < parameters.length; ++parameterIndex) {
			var parameter = parameters[parameterIndex];
			if (parameterIndex > 0) {
				System.out.print(", ");
			}
			System.out.print(parameter.getType().getName() + " " + parameter.getName());
		}
		System.out.print(")");
	}

	private void printExceptions(Class<?>[] exceptions) {
		for (var exceptionIndex = 0; exceptionIndex < exceptions.length; ++exceptionIndex) {
			var exception = exceptions[exceptionIndex];
			if (exceptionIndex == 0) {
				System.out.print(" throws ");
			} else {
				System.out.print(", ");
			}
			System.out.print(exception.getName());
		}
	}

	private void printFields() {
		System.out.println("Fields:");
		var fields = clazz.getDeclaredFields();
		for (var field : fields) {
			System.out.print(Modifier.toString(field.getModifiers()) + " ");
			System.out.print(field.getType().getName() + " ");
			System.out.println(field.getName());
		}
	}
}
