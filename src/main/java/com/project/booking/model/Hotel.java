
package com.project.booking.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

@Entity
@Table(name="hotel")
@Getter
@Setter
@ToString
public class Hotel {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique = true)
    private Long hotel_id;
    @Column(nullable=false, length=30)
    private String name;
    @Column(nullable=false)
    private double user_rating;
    @Column(nullable=false, length=30)
    private int stars;
    @Column(nullable=false, length=300)
    private String description;
    @Column(nullable=false, length=40)
    private String location;
    @Column(nullable=false, length=255)
    private String thumbnail;
    private int availableRooms;
    private int totalRooms;
        
    
    @OneToMany(mappedBy="hotel")
    //@JsonBackReference
    private List<Room> rooms;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    
}
