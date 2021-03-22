package lab5;

public class NameAlreadyExistsException extends CatalogException {
    public NameAlreadyExistsException(String name) {
        super(name + " already exists in the catalog");
    }
}
