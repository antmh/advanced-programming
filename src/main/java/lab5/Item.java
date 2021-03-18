package lab5;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Item implements Serializable {
    protected String name;
    protected String path;

    public Item(String name, String path) throws InaccessiblePathException {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }
        if (path == null) {
            throw new NullPointerException("path cannot be null");
        }
        this.name = name;
        this.path = path;
        if (!Files.exists(Paths.get(path))) {
            throw new InaccessiblePathException(path);
        }

    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
