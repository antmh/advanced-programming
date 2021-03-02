package lab2.transportation.algorithm.vogel;

import java.util.ArrayList;
import java.util.List;

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

        List<List<Integer>> costMatrix = problem.getCostMatrix();
        VogelMatrix vogelMatrix = new VogelMatrix(costMatrix);

        List<Integer> supplyLeft = new ArrayList<>();
        for (Source source : problem.getSources()) {
            supplyLeft.add(source.getSupply());
        }

        List<Integer> demandLeft = new ArrayList<>();
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
                if (cell.getColumn() < 0 || cell.getRow() < 0) {
                    return null;
                }
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

    private boolean existsDemand(List<Integer> demand) {
        for (int i : demand) {
            if (i > 0) {
                return true;
            }
        }
        return false;
    }
}
