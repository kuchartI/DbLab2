package com.dbSpring.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "pizza_order")

public class PizzaOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "pizza_id")
    private Pizza pizza_id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_id")
    private Order order_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Pizza getPizza_id() {
        return pizza_id;
    }

    public void setPizza_id(Pizza pizza_id) {
        this.pizza_id = pizza_id;
    }
    public Order getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Order order_id) {
        this.order_id = order_id;
    }
}
