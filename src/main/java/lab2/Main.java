package lab2;

import java.util.Random;

import lab2.transportation.Problem;
import lab2.transportation.Solution;
import lab2.transportation.algorithm.Algorithm;
import lab2.transportation.algorithm.SimpleAlgorithm;
import lab2.transportation.algorithm.vogel.VogelAlgorithm;
import lab2.transportation.model.Destination;
import lab2.transportation.model.Factory;
import lab2.transportation.model.Warehouse;

/**
 * @author Antonio Mihăeș
 */
public class Main {

    public static void main(String[] args) {
        Problem problem = new Problem();
        initializeExampleProblem(problem);
        System.out.println(problem);

        Algorithm algorithm = new SimpleAlgorithm();
        Solution solution = algorithm.generateSolution(problem);
        System.out.println("Simple algorithm:\n" + solution);

        algorithm = new VogelAlgorithm();
        System.out.println("Vogel algorithm");
        solution = algorithm.generateSolution(problem);
        System.out.println(solution);
        
        problem = new Problem();
        initializeRandomProblem(problem);
        System.out.println("Vogel algorithm (random problem)\n");
        solution = algorithm.generateSolution(problem);
        if (solution != null) {
            System.out.println(solution);
        } else {
            System.out.println("The random problem was invalid");
        }
    }

    private static void initializeExampleProblem(Problem problem) {
        problem.addDestination(new Destination("D1", 20));
        problem.addDestination(new Destination("D2", 25));
        problem.addDestination(new Destination("D3", 25));

        problem.addSource(new Factory("S1", 10), 2, 3, 1);
        problem.addSource(new Warehouse("S2", 35), 5, 4, 8);
        problem.addSource(new Warehouse("S3", 25), 5, 6, 8);
    }

    private static void initializeRandomProblem(Problem problem) {
        Random random = new Random();

        final int MAXIUMN_NUMBER_OF_SOURCES = 1_000;
        int sourcesNo = random.nextInt(MAXIUMN_NUMBER_OF_SOURCES);

        final int MAXIMUM_NUMBER_OF_DESTINATIONS = 1_000;
        int destinationsNo = random.nextInt(MAXIMUM_NUMBER_OF_DESTINATIONS);

        final int MAXIMUM_COST = 1_000;
        final int MAXIMUM_SUPPLY = 1_000;
        final int MAXIMUM_DEMAND = 1_000;

        for (int index = 0; index < sourcesNo; ++index) {
            int supply = random.nextInt(MAXIMUM_SUPPLY);
            problem.addSource(new Factory("S" + index, supply));
        }

        for (int index = 0; index < destinationsNo; ++index) {
            int demand = random.nextInt(MAXIMUM_DEMAND);
            int[] cost = new int[sourcesNo];
            for (int costIndex = 0; costIndex < sourcesNo; ++costIndex) {
                cost[costIndex] = random.nextInt(MAXIMUM_COST);
            }
            problem.addDestination(new Destination("D" + index, demand), cost);
        }
    }
}
