package com.dbSpring.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="pizza_toppings")
public class PizzaToppings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "topping_id")
    private Topping topping_id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "pizza_order_id")
    private PizzaOrder pizza_order_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Topping getTopping_id() {
        return topping_id;
    }

    public void setTopping_id(Topping topping_id) {
        this.topping_id = topping_id;
    }

    public PizzaOrder getPizza_order_id() {
        return pizza_order_id;
    }

    public void setPizza_order_id(PizzaOrder pizza_order_id) {
        this.pizza_order_id = pizza_order_id;
    }

}
