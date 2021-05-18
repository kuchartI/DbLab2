package com.dbSpring.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "start_of_work")
    private Time start_of_work;

    @NotNull
    @Column(name = "end_of_work")
    private Time end_of_work;

    @NotNull
    @Column(name = "phone")
    private String phone;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Time getStart_of_work() {
        return start_of_work;
    }

    public void setStart_of_work(Time start_of_work) {
        this.start_of_work = start_of_work;
    }

    public Time getEnd_of_work() {
        return end_of_work;
    }

    public void setEnd_of_work(Time end_of_work) {
        this.end_of_work = end_of_work;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
