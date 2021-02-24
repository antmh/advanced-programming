package lab2;

/**
 * @author Antonio Mihăeș
 */
public class Warehouse extends Source {
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

    public Warehouse(String name, int supply) {
        super(name, supply);
    }

}
