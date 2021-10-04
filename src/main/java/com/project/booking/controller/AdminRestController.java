package com.project.booking.controller;

import com.project.booking.model.Hotel;
import com.project.booking.model.User;
import com.project.booking.service.HotelService;
import com.project.booking.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

@RestController
@RequestMapping("/api")
public class AdminRestController {
    
    @Autowired
    private HotelService hotelService;
    
    @Autowired
    private UserService userService;
    
    /**
     * RETURNS ALL HOTELS
     * @return
     */
    @GetMapping("/hotels")
    public List<Hotel> getAllHotels(){
        return hotelService.getHotelList();
    }
    
    /**
     * RETURNS ALL USERS
     * @return
     */
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getListOfUsers();
    }
}
