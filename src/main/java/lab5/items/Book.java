package lab5.items;

import lab5.InaccessiblePathException;

public class Book extends Item {
    private String author;
    private String genre;

    public Book(String name, String path, String author, String genre) throws InaccessiblePathException {
        super(name, path);
        if (author == null) {
            throw new IllegalArgumentException("author cannot be null");
        }
        this.author = author;
        this.genre = genre;
    }

    public final String getAuthor() {
        return author;
    }

    public final String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Book [author=" + author + ", genre=" + genre + ", name=" + name + ", path=" + path + "]";
    }
}
