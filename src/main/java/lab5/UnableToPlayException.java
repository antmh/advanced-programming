package lab5;

public class UnableToPlayException extends CatalogException {
    public UnableToPlayException(String name) {
        super(name + " cannot be launched");
    }
}
