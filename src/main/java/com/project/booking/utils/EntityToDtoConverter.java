package com.project.booking.utils;

import com.project.booking.dto.UserDto;
import com.project.booking.model.User;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

public class EntityToDtoConverter {

    public EntityToDtoConverter() {
    }

    public static UserDto convertUserEntityToDto(final User user){
        return new UserDto(user.getUser_id(), user.getFirstname(), user.getEmail());
    }
    
}
