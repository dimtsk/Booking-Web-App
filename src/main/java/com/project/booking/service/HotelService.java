package com.project.booking.service;

import com.project.booking.model.Hotel;
import com.project.booking.repository.HotelRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

@Service
public class HotelService {
    
    @Autowired
    private HotelRepository hotelRepository;
    
    public List<Hotel> getHotelList(){
        return hotelRepository.findAll();
    }
    
    public List<Hotel> getSearchResult(String location){
        return hotelRepository.findByKeyword(location);
    }
    
    public Hotel getHotelById(Long id){
        return hotelRepository.findById(id).get();
    }
    
    public List<Hotel> getOwnersHotels(Long id){
        return hotelRepository.getHotelByUserId(id);
    }
    
    public void deleteHotel(Long id){
        hotelRepository.deleteById(id);
    }
    
    public void saveHotel(Hotel hotel){
        hotelRepository.save(hotel);
    }
    
    public Long roomsMinPrice(Long hotelId){
        return hotelRepository.roomsMinPrice(hotelId);
    }
    
}
