package lab5.items;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import lab5.CatalogException;

public class SongFactory extends ItemFactory {
    @Override
    public Optional<Item> create(String name, String path, String info) throws CatalogException {
        var pattern = Pattern.compile("(?<artist>[^\\s]+)");
        var matcher = pattern.matcher(info);
        if (matcher.matches()) {
            var artist = matcher.group("artist");
            if (matcher.matches()) {
                return Optional.of(new Song(name, path, artist));
            }
        }
        return Optional.empty();
    }
    
    @Override
    public boolean typeMatches(String type) {
        return Objects.equals(type, "song");
    }
}
