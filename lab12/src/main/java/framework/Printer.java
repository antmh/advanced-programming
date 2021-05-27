package framework;

import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

class Printer {
    private Class<?> clazz;

    public Printer(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void print() {
        System.out.print(Modifier.toString(clazz.getModifiers()) + " class " + clazz.getName());
        System.out.print(" extends " + clazz.getSuperclass().getName());
        for (int index = 0; index < clazz.getInterfaces().length; ++index) {
            if (index == 0) {
                System.out.print(" implements ");
            } else {
                System.out.print(", ");
            }
            System.out.print(clazz.getInterfaces()[index].getName());
        }
        System.out.println(" {");
        printConstructors();
        printMethods();
        printFields();
        System.out.println("}");
    }

    private void printConstructors() {
        var constructors = clazz.getConstructors();
        for (var constructor : constructors) {
            System.out.print("  ");
            System.out.print(Modifier.toString(constructor.getModifiers()) + " ");
            System.out.print(constructor.getName());
            printParameters(constructor.getParameters());
            printExceptions(constructor.getExceptionTypes());
            System.out.println(";");
        }
    }

    private void printMethods() {
        var methods = clazz.getDeclaredMethods();
        for (var method : methods) {
            System.out.print("  " + Modifier.toString(method.getModifiers()) + " ");
            System.out.print(method.getReturnType().getName() + " " + method.getName());
            printParameters(method.getParameters());
            printExceptions(method.getExceptionTypes());
            System.out.println(";");
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
        var fields = clazz.getDeclaredFields();
        for (var field : fields) {
            System.out.print("  " + Modifier.toString(field.getModifiers()) + " ");
            System.out.print(field.getType().getName() + " ");
            System.out.print(field.getName());
            System.out.println(";");
        }
    }
}
