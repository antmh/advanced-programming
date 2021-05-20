package framework;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

class Loader extends URLClassLoader {
	public Loader(String path) throws MalformedURLException {
		super(new URL[] { new URL("file://" + path) }, ClassLoader.getSystemClassLoader());
	}
}
