package lab3.travel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lab3.locations.City;
import lab3.locations.Location;

public class PreferencesTravelPlan implements TravelPlan {
    private City city;
    private List<Location> preferences;

    public PreferencesTravelPlan(City city, List<Location> preferences) {
        for (Location location : preferences) {
            if (!city.existsLocation(location)) {
                throw new IllegalArgumentException(
                        "Location '" + location.getName() + "' in the itinerary must be in the city");
            }
        }

        this.city = city;
        this.preferences = preferences;
    }

    @Override
    public Itinerary generateItinerary() {
        Itinerary result = new Itinerary();
        Iterator<Location> preferencesIterator = preferences.iterator();
        Location first = preferencesIterator.next();
        result.addLocation(0, first);
        Location second;
        while (preferencesIterator.hasNext()) {
            second = preferencesIterator.next();
            Iterator<Location> pathIterator = generateSolution(first, second).iterator();
            pathIterator.next();
            while (pathIterator.hasNext()) {
                result.addLocation(0, pathIterator.next());
            }
            first = second;
        }
        return result;
    }

    private List<Location> generateSolution(Location start, Location end) {
        Map<Location, Integer> cost = new HashMap<>();
        Map<Location, Location> before = new HashMap<>();
        Set<Location> toVisit = new HashSet<>();
        for (Location location : city.getLocations()) {
            toVisit.add(location);
        }
        cost.put(start, 0);
        while (!toVisit.isEmpty()) {
            Location closestLocation = null;
            for (Location location : toVisit) {
                int locationCost = cost.getOrDefault(location, -1);
                if (locationCost != -1 && (closestLocation == null || locationCost < cost.get(closestLocation))) {
                    closestLocation = location;
                }
            }
            if (closestLocation == end || closestLocation == null) {
                break;
            }
            toVisit.remove(closestLocation);
            for (Location neighbour : closestLocation.getNeighbours()) {
                int newCost = cost.get(closestLocation) + closestLocation.getCost(neighbour);
                if (cost.get(neighbour) == null || newCost < cost.get(neighbour)) {
                    cost.put(neighbour, newCost);
                    before.put(neighbour, closestLocation);
                }
            }
        }
        List<Location> result = new ArrayList<>();
        Location location = end;
        do {
            result.add(0, location);
            location = before.get(location);
        } while (location != null);
        return result;
    }
}
