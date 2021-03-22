package lab5.items;

import java.util.Optional;

import lab5.CatalogException;

public abstract class ItemFactory {
    public abstract Optional<Item> create(String name, String path, String info) throws CatalogException;

    public abstract boolean typeMatches(String type);
}
