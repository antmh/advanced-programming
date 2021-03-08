package lab3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lab3.locations.Location;

public class Itinerary {
    private List<Location> locations;

    public Itinerary() {
        locations = new ArrayList<>();
    }

    public Itinerary(List<Location> locations) {
        this.locations = locations;
    }

    public void addLocation(Location location) {
        locations.add(location);
    }

    public final List<Location> getLocations() {
        return locations;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Itinerary [");
        Iterator<Location> iterator = locations.iterator();
        stringBuilder.append(iterator.next());
        while (iterator.hasNext()) {
            stringBuilder.append(", ");
            stringBuilder.append(iterator.next());
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
