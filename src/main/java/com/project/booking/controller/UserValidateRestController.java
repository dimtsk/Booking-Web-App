package com.project.booking.controller;

import com.project.booking.dto.UserDto;
import com.project.booking.dto.UserInput;
import com.project.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

@RestController
public class UserValidateRestController {

    @Autowired
    private UserService userService;
    
    /**
     * RESPONDS TO EMAIL AVAILABILITY AJAX CALL IF USER IS ALREADY IN DB
     * @param email
     * @return
     */
    @PostMapping("/email")
	public ResponseEntity<UserDto> getUserByUserName(@RequestBody UserInput email) {
            
            UserDto userDto = userService.getUserByEmail(email.getEmail());
                
            return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
    
}
