package lab5.commands;

import java.util.Objects;

import lab5.Catalog;

public class ListCommand implements Command {
    @Override
    public boolean execute(Catalog catalog, String input) {
        if (Objects.equals(input, "list")) {
            catalog.list();
            return true;
        }
        return false;
    }
}
