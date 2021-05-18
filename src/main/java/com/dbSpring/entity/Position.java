package com.dbSpring.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "position_name")
    private String position_name;

    @NotNull
    @Column(name = "salary")
    private int salary;

    @NotNull
    @Column(name = "amount_of_premium")
    private int amount_of_premium;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAmount_of_premium() {
        return amount_of_premium;
    }

    public void setAmount_of_premium(int amount_of_premium) {
        this.amount_of_premium = amount_of_premium;
    }
}
