package lab5;

public class UnableToWriteToPathException extends Exception {
    public UnableToWriteToPathException(String name) {
        super("Unable to write to " + name);
    }
}
