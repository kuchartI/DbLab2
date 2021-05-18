package com.dbSpring.repository;

import com.dbSpring.entity.PizzaToppings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaToppingsRepository extends JpaRepository<PizzaToppings, Long> {
}
