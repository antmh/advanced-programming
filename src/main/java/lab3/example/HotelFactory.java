package lab3.example;

import lab3.locations.Hotel;
import lab3.locations.Rank;

class HotelFactory {
    public static Hotel getHotel() {
        Hotel hotel = new Hotel();
        hotel.setName("hotel");
        hotel.setPrice(120.23);
        hotel.setRank(Rank.FIVE_STARS);
        return hotel;
    }
}
