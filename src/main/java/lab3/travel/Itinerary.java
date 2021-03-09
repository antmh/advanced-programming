package lab3.travel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lab3.locations.Location;

public class Itinerary {
    private List<List<Location>> days;

    public Itinerary() {
        days = new ArrayList<>();
    }

    public void addLocation(int day, Location location) {
        if (day < 0 || day > days.size()) {
            throw new IllegalArgumentException("Day must be in index range");
        }
        if (day == days.size()) {
            days.add(new ArrayList<>());
        }
        days.get(day).add(location);
    }

    public final List<Location> getDay(int day) {
        return days.get(day);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Itinerary [\n");
        for (int day = 0; day < days.size(); ++day) {
            stringBuilder.append("Day ").append(day).append(": ");
            Iterator<Location> iterator = days.get(day).iterator();
            stringBuilder.append(iterator.next());
            while (iterator.hasNext()) {
                stringBuilder.append(", ").append(iterator.next());
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
