package lab3.example;

import java.time.LocalTime;

import lab3.locations.Museum;

class MuseumFactory {
    public static Museum getMuseum() {
        Museum museum = new Museum();
        museum.setName("Louvre");
        museum.setPrice(10.25);
        museum.setOpeningTime(LocalTime.of(12, 15));
        museum.setClosingTime(LocalTime.of(20, 30));
        return museum;
    }
}
