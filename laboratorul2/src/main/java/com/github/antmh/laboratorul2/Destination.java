package com.github.antmh.laboratorul2;

/**
 * @author Antonio Mihăeș
 */
public class Destination {

    private String name;
    private int demand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    @Override
    public String toString() {
        return "Destination [name=" + name + ", demand=" + demand + "]";
    }

    public Destination(String name, int demand) {
        this.name = name;
        this.demand = demand;
    }
}
