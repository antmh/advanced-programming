package lab3.example;

import java.time.LocalTime;

import lab3.locations.Rank;
import lab3.locations.Restaurant;

class RestaurantFactory {
    public static Restaurant getRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("restaurant");
        restaurant.setPrice(30);
        restaurant.setRank(Rank.FOUR_STARS);
        restaurant.setOpeningTime(LocalTime.of(9, 0));
        restaurant.setClosingTime(LocalTime.of(20, 30));
        return restaurant;
    }
}
