package lab3.example;

import lab3.locations.City;
import lab3.locations.Location;

public class CityFactory {
    public static City getCity() {
        City city = new City("Nume Oraș");
        Location church = ChurchFactory.getChurch();
        Location hotel = HotelFactory.getHotel();
        Location museum = MuseumFactory.getMuseum();
        Location park = ParkFactory.getPark();
        Location restaurant = RestaurantFactory.getRestaurant();
        
        city.addLocation(church);
        city.addLocation(hotel);
        city.addLocation(museum);
        city.addLocation(park);
        city.addLocation(restaurant);

        church.setCost(hotel, 2);
        church.setCost(museum, 3);
        church.setCost(park, 4);
        church.setCost(restaurant, 2);

        hotel.setCost(church, 1);
        hotel.setCost(museum, 2);
        hotel.setCost(park, 2);
        hotel.setCost(restaurant, 3);

        museum.setCost(church, 1);
        museum.setCost(hotel, 2);
        museum.setCost(park, 2);
        museum.setCost(restaurant, 3);

        park.setCost(church, 10);
        park.setCost(hotel, 2);
        park.setCost(museum, 4);
        park.setCost(restaurant, 3);

        restaurant.setCost(church, 1);
        restaurant.setCost(hotel, 2);
        restaurant.setCost(museum, 4);
        restaurant.setCost(park, 3);

        return city;
    }
}
