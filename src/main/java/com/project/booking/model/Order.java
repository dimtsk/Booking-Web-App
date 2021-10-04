package com.project.booking.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="payment")
public class Order {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long payment_id;
    private Timestamp date;
    private double price;
    private String method;
    private String coupon;
    private String currency;
    private String intent;
    private String description;

    public Order(double price, String method, String currency, String intent, String description) {
        this.price = price;
        this.method = method;
        this.currency = currency;
        this.intent = intent;
        this.description = description;
    }
    
}
