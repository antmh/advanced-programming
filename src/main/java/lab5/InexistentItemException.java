package lab5;

public class InexistentItemException extends Exception {
    public InexistentItemException(String name) {
        super(name + " is not an item in the catalog");
    }
}
