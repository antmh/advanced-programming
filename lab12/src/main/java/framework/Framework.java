package framework;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Properties;

public class Framework {
	public Framework() throws IOException, ClassNotFoundException {
		var properties = new Properties();
		properties.load(getClass().getResourceAsStream("/META-INF/testconfig.properties"));
		var path = properties.getProperty("path");
		var loader = new Loader(path);
		loader.close();
		for (var clazz : loader.getClasses()) {
			if (clazz.isAnnotationPresent(Test.class) && Modifier.isPublic(clazz.getModifiers())) {
				print(clazz);
				System.out.flush();
				test(clazz);
				System.out.flush();
				System.err.flush();
			}
		}
	}

	private void print(Class<?> clazz) {
		new Printer(clazz).print();
	}

	private void test(Class<?> clazz) {
		new Tester(clazz).test();
	}
}
