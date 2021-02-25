package lab2.transportation;

import java.util.ArrayList;
import lab2.transportation.model.Destination;
import lab2.transportation.model.Source;

/**
 * @author Antonio Mihăeș
 */
public class Problem {
    private ArrayList<ArrayList<Integer>> costMatrix;
    private ArrayList<Source> sources;
    private ArrayList<Destination> destinations;

    public Problem() {
        costMatrix = new ArrayList<>();
        sources = new ArrayList<>();
        destinations = new ArrayList<>();
    }

    public final ArrayList<ArrayList<Integer>> getCostMatrix() {
        return costMatrix;
    }

    public final ArrayList<Source> getSources() {
        return sources;
    }

    public final ArrayList<Destination> getDestinations() {
        return destinations;
    }

    public void setCost(ArrayList<ArrayList<Integer>> cost) {
        this.costMatrix = cost;
    }

    public void addSource(Source source, int... destinationCosts) {
        for (Source addedSource : sources) {
            if (source.equals(addedSource)) {
                throw new IllegalArgumentException("Source already added");
            }
        }

        if (destinationCosts.length != destinations.size()) {
            throw new IllegalArgumentException("Incorrect number of destination costs");
        }
        sources.add(source);

        ArrayList<Integer> row = new ArrayList<Integer>();
        for (int cost : destinationCosts) {
            row.add(cost);
        }
        costMatrix.add(row);
    }

    public void addDestination(Destination destination, int... sourceCosts) {
        for (Destination addedDestination : destinations) {
            if (destination.equals(addedDestination)) {
                throw new IllegalArgumentException("Destination already added");
            }
        }

        if (sourceCosts.length != sources.size()) {
            throw new IllegalArgumentException("Incorrect number of source costs");
        }
        destinations.add(destination);

        for (int i = 0; i < sourceCosts.length; ++i) {
            costMatrix.get(i).add(sourceCosts[i]);
        }
    }

    public void solveSimple() {

    }

    @Override
    public String toString() {
        return "Problem [cost=" + costMatrix + ", sources=" + sources + ", destinations=" + destinations + "]";
    }
}
