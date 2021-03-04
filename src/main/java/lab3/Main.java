package lab3;

import java.time.LocalTime;

public class Main {
	public static void main(String args[]) {
		City city = new City("Nume Oraș");

		Church church = new Church();
		church.setName("a");
		church.setCoordinate(new Coordinate(-24.235, +43.123));
		church.setDescription("abc");
		church.setOpeningTime(LocalTime.of(12, 0));
		church.setClosingTime(LocalTime.of(21, 30));
		city.addLocation(church);

		Hotel hotel = new Hotel();
		hotel.setName("b");
		hotel.setPrice(120.23);
		hotel.setRank(5);
		city.addLocation(hotel);

		Museum museum = new Museum();
		museum.setName("c");
		museum.setPrice(10.25);
		museum.setOpeningTime(LocalTime.of(12, 15));
		museum.setClosingTime(LocalTime.of(20, 30));
		city.addLocation(museum);

		Park park = new Park();
		park.setName("d");
		park.setCoordinate(new Coordinate(-23.453, +43.231));
		park.setOpeningTime(LocalTime.of(7, 15));
		park.setClosingTime(LocalTime.of(22, 30));
		city.addLocation(park);

		Restaurant restaurant = new Restaurant();
		restaurant.setName("e");
		restaurant.setPrice(30);
		restaurant.setRank(4);
		restaurant.setOpeningTime(LocalTime.of(9, 0));
		restaurant.setClosingTime(LocalTime.of(20, 30));
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

		park.setCost(church, 1);
		park.setCost(hotel, 2);
		park.setCost(museum, 4);
		park.setCost(restaurant, 3);

		restaurant.setCost(church, 1);
		restaurant.setCost(hotel, 2);
		restaurant.setCost(museum, 4);
		restaurant.setCost(park, 3);

		System.out.println(city);
		
		city.displayVisitableNotPayable();
	}
}
