package lab5;

public class InvalidCatalogException extends CatalogException {
    public InvalidCatalogException() {
        super("catalog is invalid");
    }
}
