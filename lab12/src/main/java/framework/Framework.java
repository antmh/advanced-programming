package framework;

import java.io.IOException;
import java.util.Properties;

public class Framework {
	public Class<?> clazz;

	public Framework() throws IOException, ClassNotFoundException {
		var properties = new Properties();
		properties.load(getClass().getResourceAsStream("/META-INF/testconfig.properties"));
		var path = properties.getProperty("path");
		var className = properties.getProperty("class");
		var loader = new Loader(path);
		clazz = loader.loadClass(className);
		loader.close();
	}

	public void print() {
		new Printer(clazz).print();
	}

	public void test() {
		new Tester(clazz).test();
	}
}
