package lab3;

import java.util.Arrays;
import java.util.List;

import lab3.example.CityFactory;
import lab3.locations.City;
import lab3.locations.Location;
import lab3.travel.DailyTravelPlan;
import lab3.travel.PreferencesTravelPlan;
import lab3.travel.TravelPlan;

public class Main {
    public static void main(String args[]) {
        City city = CityFactory.getCity();
        System.out.println(city);
        city.displayVisitableNotPayable();

        List<Location> preferences = Arrays.asList(city.getLocation("hotel"), city.getLocation("park"),
                city.getLocation("church"), city.getLocation("restaurant"));
        TravelPlan preferencesTravelPlan = new PreferencesTravelPlan(city, preferences);
        System.out.println(preferencesTravelPlan.generateItinerary());

        TravelPlan dailyDravelPlan = new DailyTravelPlan(city, city.getLocation("hotel"), 5, 3);
        System.out.println(dailyDravelPlan.generateItinerary());
    }
}
