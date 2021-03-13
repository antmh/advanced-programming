package lab4;

public class School implements Comparable<School> {
    private String name;

    School() {
        name = "";
    }

    School(String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
    }

    @Override
    public int compareTo(School other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot compare to null");
        }
        return name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "School [name=" + name + "]";
    }
}
