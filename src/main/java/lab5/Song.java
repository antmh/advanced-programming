package lab5;

import java.io.Serializable;

public class Song extends Item implements Serializable {
    private String artist;

    public Song(String name, String path, String artist) throws InaccessiblePathException {
        super(name, path);
        if (artist == null) {
            throw new NullPointerException("director cannot be null");
        }
        this.artist = artist;
    }

    public final String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return "Song [artist=" + artist + ", name=" + name + ", path=" + path + "]";
    }
}
