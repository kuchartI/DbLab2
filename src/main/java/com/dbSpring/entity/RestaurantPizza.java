package com.dbSpring.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "restaurant_pizza")
public class RestaurantPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant_id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "pizza_id")
    private Pizza pizza_id;

    @NotNull
    @Column(name = "available")
    private boolean available;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;


    public Restaurant getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Restaurant restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public Pizza getPizza_id() {
        return pizza_id;
    }

    public void setPizza_id(Pizza pizza_id) {
        this.pizza_id = pizza_id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
