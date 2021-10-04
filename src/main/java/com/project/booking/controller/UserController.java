package com.project.booking.controller;

import com.project.booking.model.Hotel;
import com.project.booking.model.User;
import com.project.booking.service.HotelService;
import com.project.booking.service.UserService;
import com.project.booking.utils.HotelUtils;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

@Controller
public class UserController {

    public static final String DEFAULT_ROLE = "ROLE_USER";
    
    HotelUtils filterUtils=new HotelUtils();

    @Autowired
    private HotelService hotelService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * HOME PAGE
     * DISPLAY THE LIST OF ALL REGISTRATED HOTELS
     * @param mm
     * @return 
     */
    @GetMapping("/")
    public String hello(ModelMap mm) {
        List<Hotel> list=hotelService.getHotelList();        
        mm.addAttribute("hotels", list);      
        return "index";
    }
    
    /**
     * LOGIN-REGISTRATION FORM
     * @param mm
     * @return 
     */
    @GetMapping("/register")
    public String registerForm(ModelMap mm) {
        mm.addAttribute("user", new User());
        return "loginForm";
    }

    /**
     * POST NEW USER
     * @param user
     * @param mm
     * @return 
     */
    @PostMapping("/process/registration")
    public String registerUser(User user, ModelMap mm) {
        List<User> userList=userService.getListOfUsers();
        for(User u:userList){
            if(user.getEmail().equals(u.getEmail())){
                mm.addAttribute("messageoutput", "This Email already exists");
                return "loginForm";
            }
        }
        user.setRole(DEFAULT_ROLE);
        String encryptPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptPassword);
        userService.createUserAccount(user);
        return user.getFirstname() + " " + user.getLastname() + " Welcome!";
    }
    
    
   
    
    /**
     * 
     * @param mm
     * @param location
     * @param price
     * @param stars
     * @param check_in
     * @param check_out
     * @param rating
     * @return 
     * SEARCH ENGINE
     */
    @GetMapping("/search")
    public String searchEngine(ModelMap mm, @Param("location") String location, 
            @Param("price") Integer price,
            @Param("stars") Integer stars,
            @Param("check_in") Date check_in,
            @Param("check_out") Date check_out,
            @Param("rating") Integer rating){
        
        List<Hotel> hotels=hotelService.getSearchResult(location);         
        List<Hotel> results=filterUtils.filters(hotels,stars,rating);
        mm.addAttribute("hotels", results);
        
        return "index";
    }
    
    /**
     * REDIRECT BASED ON ROLE
     * @param request
     * @param response
     * @param authResult
     * @throws IOException 
     */
    @GetMapping("/successlogin")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException{
        String role = authResult.getAuthorities().toString();
        
        if(role.contains("ROLE_ADMIN")){
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/admin/myhotels"));
        }else if(role.contains("ROLE_USER")){
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/"));
        }        
    }
    
    /**
     * RESERVATION DETAILS PAGE AFTER SELECTED
     *
     * @param hotel_id
     * @param mm
     * @return
     */
    @GetMapping("reservation/{hotel_id}")
    public String getReservation(@PathVariable("hotel_id") Long hotel_id, ModelMap mm) {
        mm.addAttribute("hotel", hotelService.getHotelById(hotel_id));
        return "reservationDetails";
    }
    
}

    