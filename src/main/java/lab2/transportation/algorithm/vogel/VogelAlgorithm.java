package lab2.transportation.algorithm.vogel;

import java.util.ArrayList;

import lab2.transportation.Problem;
import lab2.transportation.Solution;
import lab2.transportation.algorithm.Algorithm;
import lab2.transportation.model.Destination;
import lab2.transportation.model.Source;

public class VogelAlgorithm extends Algorithm {
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
        
        System.out.println("Supply: " + supplyLeft);
        System.out.println("Demand: " + demandLeft);
        
        int i = 0;
        while (existsDemand(demandLeft)) {
            System.out.println("Iter: " + i);
            ++i;
            MatrixCell cell;
            int demandValue;
            int supplyValue;
            int unitsDelivered;
            do {
                cell = vogelMatrix.getNextCell();
                System.out.println("Cell: " + cell.getRow() + ", " + cell.getColumn());
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
            System.out.println();
        }
        
        return solution;
    }
    
    private boolean existsDemand(ArrayList<Integer> demand) {
        System.out.println("Exits demand: " + demand);
        for (int i : demand) {
            if (i > 0) {
                return true;
            }
        }
        return false;
    }
}
