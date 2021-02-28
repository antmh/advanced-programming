package lab2.transportation.model;

/**
 * @author Antonio Mihăeș
 */
public class Delivery {
    private Source source;
    private Destination destination;
    private int units;

    public Delivery(Source source, Destination destination, int units) {
        validateSource(source);
        validateDestination(destination);
        validateUnits(units);
        this.source = source;
        this.destination = destination;
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
        validateSource(source);
        this.source = source;
    }

    public void setDestination(Destination destination) {
        validateDestination(destination);
        this.destination = destination;
    }

    public void setUnits(int units) {
        validateUnits(units);
        this.units = units;
    }

    @Override
    public String toString() {
        return source + " -> " + destination + ": " + units;
    }

    private void validateSource(Source source) {
        if (source == null) {
            throw new IllegalArgumentException("source must not be null");
        }
    }

    private void validateDestination(Destination destination) {
        if (destination == null) {
            throw new IllegalArgumentException("destination must not be null");
        }
    }

    private void validateUnits(int units) {
        if (units < 0) {
            throw new IllegalArgumentException("units must not be negative");
        }
    }
}
