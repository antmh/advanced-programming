package lab5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var catalog = new Catalog();
        var scanner = new Scanner(System.in);
        while (true) {
            var command = scanner.nextLine().trim();
            try {
                var itemToAdd = Commands.addCommand(command);
                if (itemToAdd.isPresent()) {
                    catalog.add(itemToAdd.get());
                    continue;
                }
                if (Commands.listCommand(command)) {
                    catalog.list();
                    continue;
                }
                var itemToPlay = Commands.playCommand(command);
                if (itemToPlay.isPresent()) {
                    catalog.play(itemToPlay.get());
                    continue;
                }
                var saveLocation = Commands.saveCommand(command);
                if (saveLocation.isPresent()) {
                    catalog.save(saveLocation.get());
                    continue;
                }
                var loadLocation = Commands.loadCommand(command);
                if (loadLocation.isPresent()) {
                    catalog.load(loadLocation.get());
                    continue;
                }
                if (Commands.quitCommand(command)) {
                    break;
                }
            } catch (InexistentItemException | InvalidCatalogException | UnableToPlayException
                    | NameAlreadyExistsException | InaccessiblePathException e) {
                System.err.println(e.getMessage());
            }
            System.err.println("Unknown command");
        }
        scanner.close();
    }
}
