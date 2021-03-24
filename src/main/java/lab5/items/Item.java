package lab5.items;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import lab5.InaccessiblePathException;
import lab5.ParseException;

public abstract class Item implements Serializable {
    protected String name;
    protected String path;

    public Item(String name, String path) throws InaccessiblePathException {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (path == null) {
            throw new IllegalArgumentException("path cannot be null");
        }
        this.name = name;
        this.path = path;
        if (!Files.exists(Paths.get(path))) {
            throw new InaccessiblePathException(path);
        }

    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
    
    public Metadata getMetadata() throws InaccessiblePathException, ParseException {
        var parser = new AutoDetectParser();
        var handler = new BodyContentHandler();
        var metadata = new Metadata();
        var context = new ParseContext();
        var tika = new Tika();
        try (var stream = new FileInputStream(path)) {
            metadata.set(Metadata.CONTENT_TYPE, tika.detect(stream));
            parser.parse(stream, handler, metadata, context);
        } catch (IOException e) {
            throw new InaccessiblePathException(path);
        } catch (TikaException | SAXException e) {
            throw new ParseException(path);
        }
        return metadata;
    }
}
