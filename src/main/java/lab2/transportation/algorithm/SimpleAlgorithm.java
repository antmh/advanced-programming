package lab2.transportation.algorithm;

import java.util.ArrayList;
import java.util.List;

import lab2.transportation.Problem;
import lab2.transportation.Solution;
import lab2.transportation.model.Destination;
import lab2.transportation.model.Source;

/**
 * @author Antonio Mihăeș
 */
public class SimpleAlgorithm extends Algorithm {
    /**
     * This generates a new solution by checking for each destination the first
     * source that still has some supply left. Note that this solution is not
     * efficient at all, it just generates a feasible solution and does not take
     * into account the cost matrix.
     */
    @Override
    public Solution generateSolution(Problem problem) {
        Solution solution = new Solution();

        List<Source> sources = problem.getSources();
        List<Destination> destinations = problem.getDestinations();

        List<Integer> supplyLeft = new ArrayList<>();

        for (Source source : sources) {
            supplyLeft.add(source.getSupply());
        }

        for (int destinationIndex = 0; destinationIndex < destinations.size(); ++destinationIndex) {
            int demandLeft = destinations.get(destinationIndex).getDemand();

            for (int sourceIndex = 0; demandLeft > 0 && sourceIndex < sources.size(); ++sourceIndex) {
                if (supplyLeft.get(sourceIndex) > 0) {
                    Source source = sources.get(sourceIndex);
                    Destination destination = destinations.get(destinationIndex);
                    int unitsDelivered = Math.min(demandLeft, supplyLeft.get(sourceIndex));

                    solution.addDelivery(source, destination, unitsDelivered);
                    demandLeft -= unitsDelivered;
                    supplyLeft.set(sourceIndex, supplyLeft.get(sourceIndex) - unitsDelivered);
                }
            }
        }

        return solution;
    }
}
