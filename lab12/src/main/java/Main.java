import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import framework.Framework;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, IOException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		var framework = new Framework();
		framework.print();
		framework.test();
	}
}
