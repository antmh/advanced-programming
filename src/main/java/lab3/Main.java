package lab3;

import java.time.LocalTime;
import java.util.Arrays;

import lab3.locations.Church;
import lab3.locations.City;
import lab3.locations.Coordinate;
import lab3.locations.Hotel;
import lab3.locations.Museum;
import lab3.locations.Park;
import lab3.locations.Rank;
import lab3.locations.Restaurant;
import lab3.travel.DailyTravelPlan;
import lab3.travel.PreferencesTravelPlan;
import lab3.travel.TravelPlan;

public class Main {
    public static void main(String args[]) {
        City city = new City("Nume Oraș");

        Church church = new Church();
        church.setName("church");
        church.setCoordinate(new Coordinate(-24.235, +43.123));
        church.setDescription("abc");
        church.setOpeningTime(LocalTime.of(12, 0));
        church.setClosingTime(LocalTime.of(21, 30));
        city.addLocation(church);

        Hotel hotel = new Hotel();
        hotel.setName("hotel");
        hotel.setPrice(120.23);
        hotel.setRank(Rank.FIVE_STARS);
        city.addLocation(hotel);

        Museum louvreMuseum = new Museum();
        louvreMuseum.setName("Louvre");
        louvreMuseum.setPrice(10.25);
        louvreMuseum.setOpeningTime(LocalTime.of(12, 15));
        louvreMuseum.setClosingTime(LocalTime.of(20, 30));
        city.addLocation(louvreMuseum);

        Park park = new Park();
        park.setName("park");
        park.setCoordinate(new Coordinate(-23.453, +43.231));
        park.setOpeningTime(LocalTime.of(7, 15));
        park.setClosingTime(LocalTime.of(22, 30));
        city.addLocation(park);

        Restaurant restaurant = new Restaurant();
        restaurant.setName("restaurant");
        restaurant.setPrice(30);
        restaurant.setRank(Rank.FOUR_STARS);
        restaurant.setOpeningTime(LocalTime.of(9, 0));
        restaurant.setClosingTime(LocalTime.of(20, 30));
        city.addLocation(restaurant);

        church.setCost(hotel, 2);
        church.setCost(louvreMuseum, 3);
        church.setCost(park, 4);
        church.setCost(restaurant, 2);

        hotel.setCost(church, 1);
        hotel.setCost(louvreMuseum, 2);
        hotel.setCost(park, 2);
        hotel.setCost(restaurant, 3);

        louvreMuseum.setCost(church, 1);
        louvreMuseum.setCost(hotel, 2);
        louvreMuseum.setCost(park, 2);
        louvreMuseum.setCost(restaurant, 3);

        park.setCost(church, 10);
        park.setCost(hotel, 2);
        park.setCost(louvreMuseum, 4);
        park.setCost(restaurant, 3);

        restaurant.setCost(church, 1);
        restaurant.setCost(hotel, 2);
        restaurant.setCost(louvreMuseum, 4);
        restaurant.setCost(park, 3);

        System.out.println(city);

        city.displayVisitableNotPayable();

        TravelPlan preferencesTravelPlan = new PreferencesTravelPlan(city,
                Arrays.asList(hotel, park, church, restaurant));
        System.out.println(preferencesTravelPlan.generateItinerary());

        TravelPlan dailyDravelPlan = new DailyTravelPlan(city, hotel, 5, 3);
        System.out.println(dailyDravelPlan.generateItinerary());
    }
}
