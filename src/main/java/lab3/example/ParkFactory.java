package lab3.example;

import java.time.LocalTime;

import lab3.locations.Coordinate;
import lab3.locations.Park;

class ParkFactory {
    public static Park getPark() {
        Park park = new Park();
        park.setName("park");
        park.setCoordinate(new Coordinate(-23.453, +43.231));
        park.setOpeningTime(LocalTime.of(7, 15));
        park.setClosingTime(LocalTime.of(22, 30));
        return park;
    }
}
