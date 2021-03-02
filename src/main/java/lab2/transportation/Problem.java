package lab2.transportation;

import java.util.ArrayList;
import java.util.List;

import lab2.transportation.model.Destination;
import lab2.transportation.model.Source;

/**
 * @author Antonio Mihăeș
 */
public class Problem {
    private List<List<Integer>> costMatrix;
    private List<Source> sources;
    private List<Destination> destinations;

    public Problem() {
        costMatrix = new ArrayList<>();
        sources = new ArrayList<>();
        destinations = new ArrayList<>();
    }

    public final List<List<Integer>> getCostMatrix() {
        return costMatrix;
    }

    public final List<Source> getSources() {
        return sources;
    }

    public final List<Destination> getDestinations() {
        return destinations;
    }

    public void setCost(List<List<Integer>> cost) {
        this.costMatrix = cost;
    }

    /**
     * This method adds a new source. The number of destination costs must be equal
     * to the number of destinations added previously, otherwise an exception is
     * thrown.
     * 
     * @param source           source to be added
     * @param destinationCosts destination costs to be used in the cost matrix
     *                         corresponding to the source
     */
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

        List<Integer> row = new ArrayList<>();
        for (int cost : destinationCosts) {
            row.add(cost);
        }
        costMatrix.add(row);
    }

    /**
     * This method adds a new destination. The number of source costs must be equal
     * to the number of sources added previously, otherwise an exception is thrown.
     * 
     * @param destination destination to be added
     * @param sourceCosts source costs to be used in the cost matrix corresponding
     *                    to the destination
     */
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

    @Override
    public String toString() {
        return "Problem [cost=" + costMatrix + ", sources=" + sources + ", destinations=" + destinations + "]";
    }
}
