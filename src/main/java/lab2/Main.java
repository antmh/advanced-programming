package lab2;

import lab2.transportation.Problem;
import lab2.transportation.Solution;
import lab2.transportation.algorithm.SimpleAlgorithm;
import lab2.transportation.model.Destination;
import lab2.transportation.model.Factory;
import lab2.transportation.model.Warehouse;

/**
 * @author Antonio Mihăeș
 */
public class Main {

    public static void main(String[] args) {
        Problem problem = new Problem();
        initializeProblem(problem);
        System.out.println(problem);

        SimpleAlgorithm simpleAlgorithm = new SimpleAlgorithm();
        Solution solution = simpleAlgorithm.generateSolution(problem);
        System.out.println(solution);
    }

    private static void initializeProblem(Problem problem) {
        problem.addDestination(new Destination("D1", 20));
        problem.addDestination(new Destination("D2", 25));
        problem.addDestination(new Destination("D3", 25));

        problem.addSource(new Factory("S1", 10), 2, 3, 1);
        problem.addSource(new Warehouse("S2", 35), 5, 4, 8);
        problem.addSource(new Warehouse("S3", 25), 5, 6, 8);
    }
}
