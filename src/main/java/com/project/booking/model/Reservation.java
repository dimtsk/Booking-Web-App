package com.project.booking.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

@Entity
@Table(name="reservation")
@Data
public class Reservation {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long reservation_id;
    private Timestamp start_date;
    private Timestamp end_date;
    private int discount;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User users;
    
    @OneToOne
    @JoinColumn(name="payment_id")
    private Order payment;
    
    @ManyToOne
    @JoinColumn(name="room_id")
    private Room rooms;
    
}
