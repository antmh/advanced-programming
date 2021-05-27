package framework;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

class Loader extends URLClassLoader implements FileVisitor<Path> {
	private static Pattern classFilePattern = Pattern.compile(".*/classes/(.*).class");
	private List<Class<?>> classes = new ArrayList<>();

	public Loader(String path) throws MalformedURLException {
		super(new URL[0], ClassLoader.getSystemClassLoader());
		try {
			Files.walkFileTree(Paths.get(new URI("file://" + path)), this);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Override
	public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attributes) {
		try {
			addURL(path.toUri().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path path, IOException exception) {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path path, BasicFileAttributes attributes) throws IOException {
		var type = Files.probeContentType(path);
		if (type != null && (type.equals("application/java-archive") || type.equals("application/x-java-archive"))) {
			var jarFile = new JarFile(path.toFile());
			addURL(path.toUri().toURL());
			var entries = jarFile.entries();
			while (entries.hasMoreElements()) {
				var entry = entries.nextElement();
				if (entry.isDirectory()) {
					continue;
				}
				loadClassFromPath("/classes/" + entry.getName());
			}
			jarFile.close();
		} else {
			loadClassFromPath(path.toString());
		}
		return FileVisitResult.CONTINUE;
	}

	private void loadClassFromPath(String path) {
		var matcher = classFilePattern.matcher(path.replace('\\', '/'));
		if (matcher.matches()) {
			try {
				classes.add(loadClass(matcher.group(1).replace('/', '.')));
			} catch (ClassNotFoundException | UnsupportedClassVersionError | NoClassDefFoundError e) {
			}
		}
	}

	@Override
	public FileVisitResult visitFileFailed(Path path, IOException exception) {
		return FileVisitResult.CONTINUE;
	}

	public List<Class<?>> getClasses() {
		return classes;
	}
}
