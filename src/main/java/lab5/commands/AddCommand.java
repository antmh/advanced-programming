package lab5.commands;

import java.util.regex.Pattern;

import lab5.Catalog;
import lab5.CatalogException;
import lab5.items.ItemFactory;

public class AddCommand implements Command {
    private ItemFactory[] itemFactories;

    public AddCommand(ItemFactory... itemFactories) {
        this.itemFactories = itemFactories;
    }

    @Override
    public boolean execute(Catalog catalog, String input) throws CatalogException {
        var pattern = Pattern.compile("add (?<path>[^\\s]+) (?<name>[^\\s]+) (?<type>[^\\s]+) (?<info>.+)");
        var matcher = pattern.matcher(input);
        if (matcher.matches()) {
            var path = matcher.group("path");
            var name = matcher.group("name");
            var type = matcher.group("type");
            var info = matcher.group("info");
            for (var itemFactory : itemFactories) {
                if (itemFactory.typeMatches(type)) {
                    var item = itemFactory.create(name, path, info);
                    if (item.isEmpty()) {
                        System.err.println("Wrong syntax for adding " + type);
                    } else {
                        catalog.add(item.get());
                    }
                    return true;
                }
            }
            System.err.println("Unknown item");
            return true;
        }
        return false;
    }
}
