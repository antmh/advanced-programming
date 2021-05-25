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

class Loader extends URLClassLoader implements FileVisitor<Path> {
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
			addURL(path.toUri().toURL());
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path path, IOException exception) {
		return FileVisitResult.CONTINUE;
	}
}
