package lab5;

public class InaccessiblePathException extends CatalogException {
    public InaccessiblePathException(String path) {
        super("Cannot access " + path);
    }
}
