package com.dbSpring.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "category_name")
    private String category_name;

    @NotNull
    @Column(name = "pizza_side")
    private String pizza_side;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getPizza_side() {
        return pizza_side;
    }

    public void setPizza_side(String pizza_side) {
        this.pizza_side = pizza_side;
    }

    @Override
    public String toString() {
        return getId() + " " + getCategory_name() + " " + getPizza_side() ;
    }
}