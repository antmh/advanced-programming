package lab3.locations;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public abstract class Location implements Comparable<Location> {
    private String name;
    private String description;
    private Map<Location, Integer> cost;
    private Coordinate coordinate;

    public Location() {
        name = "";
        description = "";
        cost = new HashMap<>();
        coordinate = new Coordinate();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCost(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("location must not be null");
        }
        return cost.getOrDefault(location, Integer.MAX_VALUE);
    }

    public Set<Location> getNeighbours() {
        return cost.keySet();
    }

    public final void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("description must not be null");
        }
        this.description = description;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name must not be null");
        }
        this.name = name;
    }

    public void setCost(Location node, int value) {
        cost.put(node, value);
    }

    public int getMaximumCost() {
        int result = -1;
        for (int locationCost : cost.values()) {
            if (result == -1 || locationCost > result) {
                result = locationCost;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Location [name=" + name + "]";
    }

    @Override
    public int compareTo(Location other) {
        if (other == null) {
            throw new IllegalArgumentException("other must not be null");
        }
        return this.name.compareTo(other.name);
    }
}
