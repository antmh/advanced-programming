package com.github.antmh.laboratorul2;

import java.util.ArrayList;

/**
 * @author Antonio Mihăeș
 */
public class Problem {
    private ArrayList<ArrayList<Integer>> cost;
    private ArrayList<Source> sources;
    private ArrayList<Destination> destinations;

    public ArrayList<ArrayList<Integer>> getCost() {
        return cost;
    }

    public void setCost(ArrayList<ArrayList<Integer>> cost) {
        this.cost = cost;
    }

    public ArrayList<Source> getSources() {
        return sources;
    }

    public void addSource(Source source, int... destinationCosts) {
        if (destinationCosts.length != destinations.size()) {
            throw new IllegalArgumentException("Incorrect number of destination costs");
        }
        sources.add(source);
        for (int i = 0; i < destinationCosts.length; ++i) {
            cost.get(i).add(destinationCosts[i]);
        }
    }

    public ArrayList<Destination> getDestinations() {
        return destinations;
    }

    public void addDestination(Destination destination, int... sourceCosts) {
        if (sourceCosts.length != sources.size()) {
            throw new IllegalArgumentException("Incorrect number of source costs");
        }
        destinations.add(destination);
        
        ArrayList<Integer> row = new ArrayList<Integer>();
        for (int i : sourceCosts) {
            row.add(i);
        }
        cost.add(row);
    }
    
    public Source getSource(int index) {
        return sources.get(index);
    }
    
    public Destination getDestination(int index) {
        return destinations.get(index);
    }

    @Override
    public String toString() {
        return "Problem [cost=" + cost + ", sources=" + sources + ", destinations=" + destinations + "]";
    }

    public Problem() {
        cost = new ArrayList<>();
        sources = new ArrayList<>();
        destinations = new ArrayList<>();
    }
}
