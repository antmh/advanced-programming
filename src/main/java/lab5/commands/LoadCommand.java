package lab5.commands;

import java.util.regex.Pattern;

import lab5.Catalog;
import lab5.CatalogException;

public class LoadCommand implements Command {
    @Override
    public boolean execute(Catalog catalog, String input) throws CatalogException {
        var pattern = Pattern.compile("load (?<name>[^\\s]+)");
        var matcher = pattern.matcher(input);
        if (matcher.matches()) {
            var name = matcher.group("name");
            catalog.load(name);
            return true;
        }
        return false;
    }
}
