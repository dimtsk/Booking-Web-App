package com.project.booking.utils;

import com.project.booking.model.Hotel;
import com.project.booking.model.Reservation;
import com.project.booking.model.Room;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

public class HotelUtils {

    public List<Hotel> filters(List<Hotel> hotels, int stars, int rating) {
        List<Hotel> result = new ArrayList<>();
        List<Hotel> result1 = new ArrayList<>();
        for (Hotel hotel : hotels) {
            result.add(starsFilter(hotel, stars));
            result.add(ratingFilter(hotel, rating));
        }

        Set<Hotel> s = new HashSet<>();
        for (Hotel hote : result) {
            if (!s.add(hote)) {
                result1.add(hote);
            }
        }
        return result1;
    }

     public Hotel availableDateFilter(Hotel hotel, Date check_in, Date check_out) {
        Hotel result = null;
        List<Room> rooms = hotel.getRooms();
        for (Room room : rooms) {
            if ((room.getArrival().before(check_in) && room.getDeparture().before(check_in)) || (room.getArrival().after(check_out) && room.getDeparture().after(check_out))) {
                result = hotel;
            }

        }
        return result;
    }

    public Hotel priceFilter(Hotel hotel, Integer price) {
        Hotel result = null;
        List<Room> rooms = hotel.getRooms();
        for (Room room : rooms) {
            if (room.getPrice() <= price) {
                result = hotel;
            }
        }
        return result;
    }

    public Hotel availableRoomsFilter(Hotel hotel) {
        Hotel result = null;
        if (hotel.getAvailableRooms() > 0) {
            result = hotel;
        }
        return result;
    }

    public Hotel starsFilter(Hotel hotel, Integer stars) {
        Hotel result = null;
        if (hotel.getStars() >= stars) {
            result = hotel;
        }
        return result;
    }

    public Hotel ratingFilter(Hotel hotel, Integer rating) {
        Hotel result = null;
        if (hotel.getUser_rating() >= rating) {
            result = hotel;
        }
        return result;
    }

    public Integer minHotelRoomPrice(Hotel hotel) {
        Integer minPrice = 0;
        for (Room room : hotel.getRooms()) {
            if (room.getPrice() < minPrice) {
                minPrice = room.getPrice();
            }
        }
        return minPrice;
    }

    public Integer maxHotelRoomPrice(Hotel hotel) {
        Integer maxPrice = 0;
        for (Room room : hotel.getRooms()) {
            if (room.getPrice() > maxPrice) {
                maxPrice = room.getPrice();
            }
        }
        return maxPrice;
    }

}
