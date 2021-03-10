package lab3.travel;

import lab3.locations.City;
import lab3.locations.Location;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.extension.Tuples;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.tools.ArrayUtils;

/**
 * This travel plan generates an itinerary that maximises the number of
 * locations visited, given a start location that has to be returned to each
 * day, the available time available each day and the number of days available.
 */
public class DailyTravelPlan implements TravelPlan {
    private City city;
    private Location start;
    private int availableTime;
    private int daysNo;

    /**
     * Create a daily travel plan.
     * 
     * @param city          the city to travel
     * @param start         the location where to start and to return to each day
     * @param availableTime the time available each day
     * @param daysNo        the number of days available
     */
    public DailyTravelPlan(City city, Location start, int availableTime, int daysNo) {
        this.city = city;
        this.start = start;
        this.availableTime = availableTime;
        this.daysNo = daysNo;
    }

    @Override
    public Itinerary generateItinerary() {
        Model model = new Model();
        int locationsNo = city.getLocations().size();
        int maximumCost = city.getMaximumCost();
        IntVar[][] next = model.intVarMatrix(daysNo, locationsNo, 0, locationsNo - 1);
        IntVar[][] cost = model.intVarMatrix(daysNo, locationsNo, 0, maximumCost);
        IntVar[] dayCost = model.intVarArray(daysNo, 0, maximumCost * locationsNo);
        IntVar[] dayLocationsNo = model.intVarArray(daysNo, 0, locationsNo);
        BoolVar[][] dayLocationVisited = model.boolVarMatrix(daysNo, locationsNo);
        BoolVar[] locationVisited = model.boolVarArray(locationsNo);
        int startIndex = city.getLocations().indexOf(start);

        for (int day = 0; day < daysNo; ++day) {
            Constraint isCircuitValid = model.arithm(next[day][startIndex], "!=", startIndex);
            for (int i = 0; i < locationsNo; ++i) {
                Tuples tuples = new Tuples(true);
                for (int j = 0; j < locationsNo; ++j) {
                    Location firstLocation = city.getLocations().get(i);
                    Location secondLocation = city.getLocations().get(j);
                    if (firstLocation == secondLocation) {
                        tuples.add(j, 0);
                    } else {
                        tuples.add(j, firstLocation.getCost(secondLocation));
                    }
                }
                model.table(next[day][i], cost[day][i], tuples).post();
                Constraint isPartOfTheCircuit = model.arithm(next[day][i], "!=", i);
                model.reification(dayLocationVisited[day][i], model.and(isCircuitValid, isPartOfTheCircuit));
            }
            model.sum(cost[day], "=", dayCost[day]).post();
            model.arithm(dayCost[day], "<=", availableTime).post();
            model.subCircuit(next[day], 0, dayLocationsNo[day]).post();
        }

        for (int i = 0; i < locationsNo; ++i) {
            IntVar timesVisited = model.intVar(0, daysNo);
            model.sum(ArrayUtils.getColumn(dayLocationVisited, i), "=", timesVisited).post();
            model.reification(locationVisited[i], model.arithm(timesVisited, ">", 0));
        }
        IntVar visitedLocationsNo = model.intVar(0, locationsNo);
        model.sum(locationVisited, "=", visitedLocationsNo).post();
        model.setObjective(Model.MAXIMIZE, visitedLocationsNo);
        Solver solver = model.getSolver();

        Itinerary result = null;
        while (solver.solve()) {
            result = new Itinerary();
            for (int day = 0; day < daysNo; ++day) {
                int current = startIndex;
                do {
                    result.addLocation(day, city.getLocations().get(current));
                    current = next[day][current].getValue();
                } while (current != startIndex);
                result.addLocation(day, city.getLocations().get(current));
            }
        }
        return result;
    }

}
