package lab2.transportation.algorithm;

import java.util.ArrayList;

import lab2.transportation.Problem;
import lab2.transportation.Solution;
import lab2.transportation.model.Destination;
import lab2.transportation.model.Source;

public class SimpleAlgorithm extends Algorithm {
    @Override
    public Solution generateSolution(Problem problem) {
        Solution solution = new Solution();

        ArrayList<Source> sources = problem.getSources();
        ArrayList<Destination> destinations = problem.getDestinations();

        ArrayList<Integer> supplyLeft = new ArrayList<Integer>();

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
