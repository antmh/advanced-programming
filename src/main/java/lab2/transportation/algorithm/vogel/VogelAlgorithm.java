package lab2.transportation.algorithm.vogel;

import java.util.ArrayList;

import lab2.transportation.Problem;
import lab2.transportation.Solution;
import lab2.transportation.algorithm.Algorithm;
import lab2.transportation.model.Destination;
import lab2.transportation.model.Source;

/**
 * @author Antonio Mihăeș
 */
public class VogelAlgorithm extends Algorithm {
    /**
     * This method generates a solution by using the Vogel approximation algorithm.
     */
    @Override
    public Solution generateSolution(Problem problem) {
        Solution solution = new Solution();

        ArrayList<ArrayList<Integer>> costMatrix = problem.getCostMatrix();
        VogelMatrix vogelMatrix = new VogelMatrix(costMatrix);

        ArrayList<Integer> supplyLeft = new ArrayList<Integer>();
        for (Source source : problem.getSources()) {
            supplyLeft.add(source.getSupply());
        }

        ArrayList<Integer> demandLeft = new ArrayList<Integer>();
        for (Destination destination : problem.getDestinations()) {
            demandLeft.add(destination.getDemand());
        }

        while (existsDemand(demandLeft)) {
            MatrixCell cell;
            int demandValue;
            int supplyValue;
            int unitsDelivered;
            do {
                cell = vogelMatrix.getNextCell();
                demandValue = demandLeft.get(cell.getColumn());
                supplyValue = supplyLeft.get(cell.getRow());
                unitsDelivered = Math.min(demandValue, supplyValue);
            } while (unitsDelivered == 0);
            demandLeft.set(cell.getColumn(), demandValue - unitsDelivered);
            if (demandLeft.get(cell.getColumn()) == 0) {
                vogelMatrix.cancelColumn(cell.getColumn());
            }
            supplyLeft.set(cell.getRow(), supplyValue - unitsDelivered);
            if (supplyLeft.get(cell.getRow()) == 0) {
                vogelMatrix.cancelRow(cell.getRow());
            }

            Source source = problem.getSources().get(cell.getRow());
            Destination destination = problem.getDestinations().get(cell.getColumn());
            solution.addDelivery(source, destination, unitsDelivered);
        }

        return solution;
    }

    private boolean existsDemand(ArrayList<Integer> demand) {
        for (int i : demand) {
            if (i > 0) {
                return true;
            }
        }
        return false;
    }
}
