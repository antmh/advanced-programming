package lab5;

public class UnableToWriteToPathException extends CatalogException {
    public UnableToWriteToPathException(String name) {
        super("Unable to write to " + name);
    }
}
