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
        int testsPassed = 0;
        int testsTotal = 0;
        for (var clazz : loader.getClasses()) {
            if (clazz.isAnnotationPresent(Test.class) && Modifier.isPublic(clazz.getModifiers())) {
                new Printer(clazz).print();
                System.out.flush();
                var tester = new Tester(clazz);
                tester.test();
                testsPassed += tester.getTestsPassed();
                testsTotal += tester.getTestsFailed() + tester.getTestsPassed();
                System.out.flush();
                System.err.flush();
            }
        }
        printStatistic(testsPassed, testsTotal);
    }

    private void printStatistic(int testsPassed, int testsTotal) {
        System.out.print(testsPassed + "/" + testsTotal + " passed");
        System.out.println(" (" + (Double.valueOf(testsPassed) / Double.valueOf(testsTotal) * 100) + "%)");
    }
}
