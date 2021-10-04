package com.project.booking.controller;

import com.project.booking.model.Hotel;
import com.project.booking.model.User;
import com.project.booking.repository.HotelRepository;
import com.project.booking.repository.UserRepository;
import com.project.booking.service.CustomUserDetails;
import com.project.booking.service.HotelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

@Controller//--------------------------------------------------------------------->TODO
@RequestMapping("/admin")
public class AdminController {
    
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private HotelRepository hotelRepository;
    
    @Autowired
    private HotelService hotelService;
    
    /**
     * RETURNS LIST OF ALL USERS
     * @return
     */
    @GetMapping("/list")
    public List<User> loadUsers(){
        return userRepository.findAll();
    }
    
    /**
     * GET ALL HOTELS OF THE OWNER WHICH IS LOGGED AND HAS ROLE_ADMIN
     * @param user_id
     * @param mm
     * @return 
     */
    @GetMapping("/myhotels")
    public String getMyHotels( ModelMap mm ,@AuthenticationPrincipal CustomUserDetails user){
        mm.addAttribute("hotels", hotelRepository.getHotelByUserId(user.getUserId()));
        return "adminControlPanel";
    }
    
    /**
     * DELETE SELECTED HOTEL
     * @param hotel_id
     * @return 
     */
    @GetMapping("/delete/{hotel_id}")
    public String deleteSelectedHotel(@PathVariable("hotel_id") Long hotel_id){
        System.out.println(hotel_id);
        hotelService.deleteHotel(hotel_id);
        return "redirect:/admin/myhotels";
    }
    
    /**
     * GET THE HOTEL BY ID THAT THE OWNER WANT TO UPDATE THE INFORMATION
     * @param hotel_id
     * @param mm
     * @return 
     */
    @GetMapping("update/{hotel_id}")
    public String updateHotelInfo(@PathVariable("hotel_id") Long hotel_id, ModelMap mm){
        mm.put("hotel", hotelService.getHotelById(hotel_id));
        return "editHotel";//----------------------------------------------------------------------->TODO
    }
    
    /**
     * ADD NEW HOTEL
     * @param hotel_id
     * @param user
     * @param hotel
     * @return 
     */
    @PostMapping("/saved/{hotel_id}")
    public String updateHotel(@PathVariable("hotel_id") Long hotel_id, @AuthenticationPrincipal CustomUserDetails user
            ,@ModelAttribute("hotel") Hotel hotel) {
        System.out.println(user.getEmail());
        hotel.setUser(userRepository.getById(user.getUserId()));
        hotelService.saveHotel(hotel);
        return "redirect:/admin/myhotels";
    }
    
    /**
     * FORM PAGE TO ADD A NEW HOTEL TO YOUR LIST
     * @param mm
     * @return 
     */
    @GetMapping("/add")
    public String showHotelForm(ModelMap mm) {
        Hotel hotel = new Hotel();
        mm.addAttribute("hotel", hotel);
        return "addHotel";
    }
    
    /**
     * PROCESS OF YOUR REQUEST
     * @param hotel
     * @return 
     */
    @PostMapping("/add/process")
    public String addNewHotel(@ModelAttribute("hotel") Hotel hotel ,@AuthenticationPrincipal CustomUserDetails user, RedirectAttributes a){
        a.addFlashAttribute("hotels", hotelRepository.getHotelByUserId(user.getUserId()));
        hotel.setUser(userRepository.getById(user.getUserId()));
        hotelService.saveHotel(hotel);
        return "redirect:/admin/myhotels";
        
    }
    
}
