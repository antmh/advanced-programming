package lab2.transportation.model;

/**
 * @author Antonio Mihăeș
 */
public class Destination {
    private String name;
    private int demand;

    public Destination(String name, int demand) {
        validateDemand(demand);
        validateName(name);
        this.name = name;
        this.demand = demand;
    }

    public String getName() {
        return name;
    }

    public int getDemand() {
        return demand;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setDemand(int demand) {
        validateDemand(demand);
        this.demand = demand;
    }

    @Override
    public String toString() {
        return "Destination [name=" + name + ", demand=" + demand + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        return ((Destination) obj).name.equals(name);
    }

    private void validateName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("name must not be null or empty");
        }
    }

    private void validateDemand(int demand) {
        if (demand < 0) {
            throw new IllegalArgumentException("demand must not be negative");
        }
    }
}
