package lab5.commands;

import lab5.Catalog;
import lab5.CatalogException;

public interface Command {
    boolean execute(Catalog catalog, String input) throws CatalogException;
}
