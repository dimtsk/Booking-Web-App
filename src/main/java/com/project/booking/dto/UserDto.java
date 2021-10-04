package com.project.booking.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

@Getter
@Setter
@ToString
public class UserDto {

    private Long id;
    private String name;
    private String email;

    public UserDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
}
