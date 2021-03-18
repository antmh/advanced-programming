package lab5;

public class NameAlreadyExistsException extends Exception {
    public NameAlreadyExistsException(String name) {
        super(name + " already exists in the catalog");
    }
}
