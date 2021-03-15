package lab4;

public class School implements Comparable<School> {
    private String name;
    private int capacity;

    public School() {
        name = "";
        capacity = 0;
    }

    public School(String name, int capacity) {
        validateName(name);
        validateCapacity(capacity);
        this.name = name;
        this.capacity = capacity;
    }

    public final int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    public final void setCapacity(int capacity) {
        validateCapacity(capacity);
        this.capacity = capacity;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
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
        return "School [name=" + name + ", capacity=" + capacity + "]";
    }
}
