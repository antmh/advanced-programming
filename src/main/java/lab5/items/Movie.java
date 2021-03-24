package lab5.items;

import lab5.InaccessiblePathException;

public class Movie extends Item {
    private String director;

    public Movie(String name, String path, String director) throws InaccessiblePathException {
        super(name, path);
        if (director == null) {
            throw new IllegalArgumentException("director cannot be null");
        }
        this.director = director;
    }

    public final String getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return "Movie [director=" + director + ", name=" + name + ", path=" + path + "]";
    }
}
