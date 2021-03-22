package lab5;

public class InexistentItemException extends CatalogException {
    public InexistentItemException(String name) {
        super(name + " is not an item in the catalog");
    }
}
