package lab8.model;

import java.util.Optional;

public class Genre {
    private String name;
    private Optional<Integer> id;

    public Genre() {
        id = Optional.empty();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Integer> getId() {
        return id;
    }

    public void setId(Optional<Integer> id) {
        this.id = id;
    }
}
