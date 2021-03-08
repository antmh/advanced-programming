package lab3.locations;

public class Coordinate {
    private double latitude;
    private double longitude;

    public Coordinate(double latitude, double longitude) {
        validateComponent(latitude);
        validateComponent(longitude);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordinate() {
    }

    public final double getLatitude() {
        return latitude;
    }

    public final void setLatitude(double latitude) {
        validateComponent(latitude);
        this.latitude = latitude;
    }

    public final double getLongitude() {
        return longitude;
    }

    public final void setLongitude(double longitude) {
        validateComponent(longitude);
        this.longitude = longitude;
    }

    private void validateComponent(double component) {
        if (component < -90 || component > 90) {
            throw new IllegalArgumentException("Coordinate must be between -90 and 90");
        }
    }
}
