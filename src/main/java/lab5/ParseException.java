package lab5;

public class ParseException extends CatalogException {
    public ParseException(String path) {
        super("Could not parse " + path);
    }
}
