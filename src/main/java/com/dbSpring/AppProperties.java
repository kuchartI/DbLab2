package com.dbSpring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "demo")
@Validated
public class AppProperties {
    private Integer category;
    private Integer pizza;
    private Integer restaurant;
    private Integer topping;
    private Integer position;
    private Integer employee;
    private Integer order;


    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getPizza() {
        return pizza;
    }

    public void setPizza(Integer pizza) {
        this.pizza = pizza;
    }

    public Integer getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Integer restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getTopping() {
        return topping;
    }

    public void setTopping(Integer topping) {
        this.topping = topping;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}