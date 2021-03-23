package lab5.items;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import lab5.CatalogException;

public class BookFactory extends ItemFactory {
    public Optional<Item> create(String name, String path, String info) throws CatalogException {
        var pattern = Pattern.compile("(?<author>[^\\s]+) (?<genre>[^\\s]+");
        var matcher = pattern.matcher(info);
        if (matcher.matches()) {
            var author = matcher.group("author");
            var genre = matcher.group("genre");
            if (matcher.matches()) {
                return Optional.of(new Book(name, path, author, genre));
            }
        }
        return Optional.empty();
    }
    
    @Override
    public boolean typeMatches(String type) {
        return Objects.equals(type, "book");
    }
}
