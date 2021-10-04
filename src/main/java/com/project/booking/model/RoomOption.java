package com.project.booking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

@Entity
@Table(name="room_option")
public class RoomOption {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long option_id;
    private String option;
    
}
