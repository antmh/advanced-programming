package lab5.commands;

import java.util.Objects;

import lab5.Catalog;
import lab5.InaccessiblePathException;
import lab5.UnableToPlayException;

public class ReportCommand implements Command {
    @Override
    public boolean execute(Catalog catalog, String input) throws InaccessiblePathException, UnableToPlayException {
        if (Objects.equals(input, "report")) {
            catalog.report();
            return true;
        }
        return false;
    }
}
