package lab3.travel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import lab3.locations.City;
import lab3.locations.Hotel;
import lab3.locations.Location;
import lab3.locations.Museum;
import lab3.locations.Park;

class DailyTravelPlanTest {
    @Test
    void onlyOneLocation() {
        City city = new City();
        Location hotel = new Hotel();
        city.addLocation(hotel);
        TravelPlan travelPlan = new DailyTravelPlan(city, hotel, 10, 1);
        Itinerary itinerary = travelPlan.generateItinerary();
        assertEquals(1, itinerary.getDaysNo());
        List<Location> firstDay = itinerary.getDay(0);
        assertEquals(2, firstDay.size());
        assertEquals(hotel, firstDay.get(0));
        assertEquals(hotel, firstDay.get(1));
    }

    @Test
    void tooCostly() {
        City city = new City();
        Location hotel = new Hotel();
        city.addLocation(hotel);
        Location park = new Park();
        city.addLocation(park);
        hotel.setCost(park, 2);
        park.setCost(hotel, 0);
        TravelPlan travelPlan = new DailyTravelPlan(city, hotel, 1, 1);
        Itinerary itinerary = travelPlan.generateItinerary();
        assertEquals(1, itinerary.getDaysNo());
        List<Location> firstDay = itinerary.getDay(0);
        assertEquals(2, firstDay.size());
        assertEquals(hotel, firstDay.get(0));
        assertEquals(hotel, firstDay.get(1));
    }

    @Test
    void multipleDays() {
        City city = new City();
        Location hotel = new Hotel();
        city.addLocation(hotel);
        Location park = new Park();
        city.addLocation(park);
        Location museum = new Museum();
        city.addLocation(museum);
        hotel.setCost(park, 1);
        hotel.setCost(museum, 2);
        park.setCost(hotel, 2);
        museum.setCost(hotel, 1);
        TravelPlan travelPlan = new DailyTravelPlan(city, hotel, 3, 2);
        Itinerary itinerary = travelPlan.generateItinerary();
        assertEquals(2, itinerary.getDaysNo());
        List<Location> firstDay = itinerary.getDay(0);
        assertEquals(3, firstDay.size());
        assertEquals(hotel, firstDay.get(0));
        assertEquals(hotel, firstDay.get(2));
        List<Location> secondDay = itinerary.getDay(1);
        assertEquals(3, secondDay.size());
        assertEquals(hotel, secondDay.get(0));
        assertEquals(hotel, secondDay.get(2));
        assertTrue((firstDay.get(1) == park && secondDay.get(1) == museum)
                || (firstDay.get(1) == museum && secondDay.get(1) == park));
    }

    @Test
    void equallyGoodChoices() {
        City city = new City();
        Location hotel = new Hotel();
        city.addLocation(hotel);
        Location park = new Park();
        city.addLocation(park);
        Location museum = new Museum();
        city.addLocation(museum);
        hotel.setCost(park, 1);
        hotel.setCost(museum, 2);
        park.setCost(hotel, 2);
        museum.setCost(hotel, 1);
        TravelPlan travelPlan = new DailyTravelPlan(city, hotel, 3, 1);
        Itinerary itinerary = travelPlan.generateItinerary();
        assertEquals(1, itinerary.getDaysNo());
        List<Location> firstDay = itinerary.getDay(0);
        assertEquals(3, firstDay.size());
        assertEquals(hotel, firstDay.get(0));
        assertTrue(firstDay.get(1) == park || firstDay.get(1) == museum);
        assertEquals(hotel, firstDay.get(2));
    }

    @Test
    void onlyOnePossibleCircuit() {
        City city = new City();
        Location hotel = new Hotel();
        city.addLocation(hotel);
        Location park = new Park();
        city.addLocation(park);
        Location museum = new Museum();
        city.addLocation(museum);
        hotel.setCost(park, 1);
        park.setCost(museum, 1);
        museum.setCost(hotel, 1);
        TravelPlan travelPlan = new DailyTravelPlan(city, hotel, 3, 1);
        Itinerary itinerary = travelPlan.generateItinerary();
        assertEquals(1, itinerary.getDaysNo());
        List<Location> firstDay = itinerary.getDay(0);
        assertEquals(4, firstDay.size());
        assertEquals(hotel, firstDay.get(0));
        assertEquals(park, firstDay.get(1));
        assertEquals(museum, firstDay.get(2));
        assertEquals(hotel, firstDay.get(3));
    }

    @Test
    void twoPossibleCircuits() {
        City city = new City();
        Location hotel = new Hotel();
        city.addLocation(hotel);
        Location park = new Park();
        city.addLocation(park);
        Location museum = new Museum();
        city.addLocation(museum);
        hotel.setCost(park, 1);
        hotel.setCost(museum, 1);
        park.setCost(museum, 1);
        museum.setCost(park, 1);
        park.setCost(hotel, 1);
        museum.setCost(hotel, 1);
        TravelPlan travelPlan = new DailyTravelPlan(city, hotel, 3, 1);
        Itinerary itinerary = travelPlan.generateItinerary();
        assertEquals(1, itinerary.getDaysNo());
        List<Location> firstDay = itinerary.getDay(0);
        assertEquals(4, firstDay.size());
        assertEquals(hotel, firstDay.get(0));
        assertTrue((firstDay.get(1) == park && firstDay.get(2) == museum)
                || (firstDay.get(1) == museum && firstDay.get(2) == park));
        assertEquals(hotel, firstDay.get(3));
    }
}
