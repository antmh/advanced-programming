package lab2;

/**
 * @author Antonio Mihăeș
 */
public abstract class Source {

    protected String name;
    protected int supply;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSupply() {
        return supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public Source(String name, int supply) {
        this.name = name;
        this.supply = supply;
    }

}
