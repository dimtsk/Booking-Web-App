package com.project.booking.repository;

import com.project.booking.model.Hotel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

public interface HotelRepository extends JpaRepository<Hotel, Long>{
    
    @Query(value="select * from hotel h where h.location like %:location%", nativeQuery=true)
    List<Hotel> findByKeyword(String location);
    
    @Query(value="select * from hotel h where h.user_id=?", nativeQuery=true)
    List<Hotel> getHotelByUserId(Long user_id);
    
    @Query(value="select MIN(price) from room where room.hotel_hotel_id=?", nativeQuery=true)
    Long roomsMinPrice(Long hotel_hotel_id);
   
}
