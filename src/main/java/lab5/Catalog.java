package lab5;

import java.awt.Desktop;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private List<Item> items;

    public Catalog() {
        items = new ArrayList<>();
    }

    public void add(Item item) throws NameAlreadyExistsException {
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }
        for (var addedItem : items) {
            if (item.getName() == addedItem.getName()) {
                throw new NameAlreadyExistsException(item.getName());
            }
        }
        items.add(item);
    }

    public void list() {
        if (items.isEmpty()) {
            System.out.println("No items");
        } else {
            for (var item : items) {
                System.out.println(item);
            }
        }
    }

    public void play(String name) throws UnableToPlayException, InexistentItemException {
        if (name == null) {
            throw new NullPointerException("Name cannot be null");
        }
        for (var item : items) {
            if (item.getName().equals(name)) {
                try {
                    var path = Paths.get(item.getPath());
                    if (!Files.exists(path)) {
                        throw new UnableToPlayException(name);
                    }
                    Desktop.getDesktop().open(path.toFile());
                } catch (IOException e) {
                    throw new UnableToPlayException(name);
                }
                return;
            }
        }
        throw new InexistentItemException(name);
    }

    public void save(String name) throws InaccessiblePathException {
        if (name == null) {
            throw new NullPointerException("Name cannot be null");
        }
        var path = Paths.get(name);
        if (Files.exists(path)) {
            throw new InaccessiblePathException(name);
        }
        try {
            Files.createFile(path);
            try (var fileStream = Files.newOutputStream(path); var objectStream = new ObjectOutputStream(fileStream)) {
                for (var item : items) {
                    objectStream.writeObject(item);
                }
            }
        } catch (IOException e) {
            throw new InaccessiblePathException(name);
        }
    }

    public void load(String name) throws InvalidCatalogException {
        if (name == null) {
            throw new NullPointerException("Name cannot be null");
        }
        var path = Paths.get(name);
        try (var fileStream = Files.newInputStream(path); var objectStream = new ObjectInputStream(fileStream)) {
            var object = objectStream.readObject();
            if (object instanceof Item) {
                items.add((Item) object);
            } else {
                items.clear();
                throw new InvalidCatalogException();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new InvalidCatalogException();
        }
    }
}
