package lab3.example;

import java.time.LocalTime;

import lab3.locations.Church;
import lab3.locations.Coordinate;

class ChurchFactory {
    public static Church getChurch() {
        Church church = new Church();
        church.setName("church");
        church.setCoordinate(new Coordinate(-24.235, +43.123));
        church.setDescription("abc");
        church.setOpeningTime(LocalTime.of(12, 0));
        church.setClosingTime(LocalTime.of(21, 30));
        return church;
    }
}
