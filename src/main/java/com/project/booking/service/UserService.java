package com.project.booking.service;

import com.project.booking.dto.UserDto;
import com.project.booking.model.User;
import com.project.booking.repository.UserRepository;
import com.project.booking.utils.EntityToDtoConverter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public void createUserAccount(User user){
        userRepository.save(user);
    }
    
    public List<User> getListOfUsers(){
        return userRepository.findAll();
    }
    
    public UserDto getUserByEmail(final String email) {
        Optional<User> user = userRepository.findByEmail(email);
        
        if (user.isPresent()){
            return EntityToDtoConverter.convertUserEntityToDto(user.get());
        }
        
        throw new RuntimeException("No user available for the given user name");
    }
    
}
