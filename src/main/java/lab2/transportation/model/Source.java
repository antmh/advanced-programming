package lab2.transportation.model;

/**
 * @author Antonio Mihăeș
 */
public abstract class Source {
    protected String name;
    protected int supply;

    public Source(String name, int supply) {
        validateName(name);
        validateSupply(supply);
        this.name = name;
        this.supply = supply;
    }

    public String getName() {
        return name;
    }

    public int getSupply() {
        return supply;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setSupply(int supply) {
        validateSupply(supply);
        this.supply = supply;
    }

    private void validateName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("name must not be null or empty");
        }
    }

    private void validateSupply(int supply) {
        if (supply < 0) {
            throw new IllegalArgumentException("supply must not be negative");
        }
    }
}
