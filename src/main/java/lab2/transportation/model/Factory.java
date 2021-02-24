package lab2.transportation.model;

/**
 * @author Antonio Mihăeș
 */
public class Factory extends Source {
    public Factory(String name, int supply) {
        super(name, supply);
    }

    @Override
    public String toString() {
        return "Factory [name=" + name + ", supply=" + supply + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        return ((Factory) obj).name.equals(name);
    }
}
