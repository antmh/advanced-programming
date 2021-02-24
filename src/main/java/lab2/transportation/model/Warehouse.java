package lab2.transportation.model;

/**
 * @author Antonio Mihăeș
 */
public class Warehouse extends Source {
    public Warehouse(String name, int supply) {
        super(name, supply);
    }

    @Override
    public String toString() {
        return "Warehouse [name=" + name + ", supply=" + supply + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        return ((Warehouse) obj).name.equals(name);
    }
}
