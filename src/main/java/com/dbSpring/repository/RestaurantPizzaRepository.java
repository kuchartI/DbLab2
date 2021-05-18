package com.dbSpring.repository;

import com.dbSpring.entity.RestaurantPizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantPizzaRepository  extends JpaRepository<RestaurantPizza, Long> {
}
