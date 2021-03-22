package lab5.commands;

import java.util.regex.Pattern;

import lab5.Catalog;
import lab5.InexistentItemException;
import lab5.UnableToPlayException;

public class PlayCommand implements Command {
    @Override
    public boolean execute(Catalog catalog, String input) throws UnableToPlayException, InexistentItemException {
        var pattern = Pattern.compile("play (?<name>[^\\s]+)");
        var matcher = pattern.matcher(input);
        if (matcher.matches()) {
            var name = matcher.group("name");
            catalog.play(name);
            return true;
        }
        return false;
    }
}
