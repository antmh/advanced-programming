package lab5;

import java.awt.Desktop;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import lab5.items.Item;

public class Catalog {
    private List<Item> items;

    public Catalog() {
        items = new ArrayList<>();
    }

    public void add(Item item) throws NameAlreadyExistsException {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        for (var addedItem : items) {
            if (item.getName().equals(addedItem.getName())) {
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
            throw new IllegalArgumentException("Name cannot be null");
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
            throw new IllegalArgumentException("Name cannot be null");
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
            throw new IllegalArgumentException("Name cannot be null");
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

    public void report() throws InaccessiblePathException, UnableToPlayException {
        Velocity.init();
        var context = new VelocityContext();
        context.put("items", items);
        var path = Paths.get("report.html");
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            try (var fileStream = Files.newOutputStream(path)) {
                var writer = new FileWriter(path.toString());
                Velocity.evaluate(context, writer, "string", """
                        <html>
                            <body>
                                <ol>
                                #foreach( $item in $items )
                                    <li><a href="file://$item.getPath()">$item.getName()</a></li>
                                #end
                                </ol>
                            </body>
                        </html>
                        """);
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            throw new InaccessiblePathException(path.toString());
        }
        try {
            Desktop.getDesktop().open(path.toFile());
        } catch (IOException e) {
            throw new UnableToPlayException("report");
        }
    }

    public void info(String name)throws InexistentItemException, InaccessiblePathException, ParseException {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        for (var item : items) {
            if (item.getName().equals(name)) {
                var metadata = item.getMetadata();
                for (var prop : metadata.names()) {
                    System.out.println(prop + ": " + metadata.get(prop));
                }
                return;
            }
        }
    }
}
