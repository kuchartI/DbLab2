package com.dbSpring.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "restaurant_pizza")
public class RestaurantPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant_id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "pizza_id")
    private Pizza pizza_id;

    @NotNull
    @Column(name = "available")
    private boolean available;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
