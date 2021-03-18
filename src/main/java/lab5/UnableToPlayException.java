package lab5;

public class UnableToPlayException extends Exception {
    public UnableToPlayException(String name) {
        super(name + " cannot be launched");
    }
}
