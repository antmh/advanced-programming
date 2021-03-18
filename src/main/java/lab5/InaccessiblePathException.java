package lab5;

public class InaccessiblePathException extends Exception {
    public InaccessiblePathException(String path) {
        super("Cannot access " + path);
    }
}
