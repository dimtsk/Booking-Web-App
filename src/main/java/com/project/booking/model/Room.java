package com.project.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

@Entity
@Getter
@Setter
@Table(name="room")
public class Room {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long room_id;
    private int price;
    private String room_type;
    @Temporal(TemporalType.DATE)
    private Date arrival;
    @Temporal(TemporalType.DATE)
    private Date departure;
    
    @OneToMany
    private List<Reservation> reservation;
    
    @ManyToOne
    @JoinColumn(name="hotel_hotel_id")
    @JsonIgnore
    private Hotel hotel;
    
    @OneToMany
    @JoinColumn(name="option_id")
    private List<RoomOption> roomOptions;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Room{room_id=").append(room_id);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }

}
