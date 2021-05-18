package com.dbSpring.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_name")
    private String employee_name;

    @NotNull
    @Column(name = "employee_surname")
    private String employee_surname;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant_id;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name = "schedule")
    private String schedule;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "position_id")
    private Position position_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Position getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Position position_id) {
        this.position_id = position_id;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Restaurant getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Restaurant restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getEmployee_surname() {
        return employee_surname;
    }

    public void setEmployee_surname(String employee_surname) {
        this.employee_surname = employee_surname;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }
}
