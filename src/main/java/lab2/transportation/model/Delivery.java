package lab2.transportation.model;

public class Delivery {
    private Source source;
    private Destination destination;
    private int units;

    public Delivery(Source source, Destination destination, int units) {
        this.destination = destination;
        this.source = source;
        this.units = units;
    }

    public Source getSource() {
        return source;
    }

    public Destination getDestination() {
        return destination;
    }

    public int getUnits() {
        return units;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return source + " -> " + destination + ": " + units;
    }

}
