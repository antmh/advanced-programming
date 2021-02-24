package com.github.antmh.laboratorul2;

/**
 * @author Antonio Mihăeș
 */
public class Main {

    public static void main(String[] args) {
        Problem problem = new Problem();

        problem.addDestination(new Destination("D1", 20));
        problem.addDestination(new Destination("D2", 35));
        problem.addDestination(new Destination("D3", 25));

        problem.addSource(new Factory("S1", 20), 2, 3, 1);
        problem.addSource(new Warehouse("S2", 25), 5, 4, 8);
        problem.addSource(new Warehouse("S3", 25), 5, 6, 8);

        System.out.println(problem);
    }
}
