package lab5.items;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import lab5.CatalogException;

public class MovieFactory extends ItemFactory {
    @Override
    public Optional<Item> create(String name, String path, String info) throws CatalogException {
        var pattern = Pattern.compile("(?<director>[^\\s]+)");
        var matcher = pattern.matcher(info);
        if (matcher.matches()) {
            var director = matcher.group("director");
            if (matcher.matches()) {
                return Optional.of(new Movie(name, path, director));
            }
        }
        return Optional.empty();
    }
    
    @Override
    public boolean typeMatches(String type) {
        return Objects.equals(type, "movie");
    }
}
