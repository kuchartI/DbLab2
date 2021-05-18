package com.dbSpring.repository;

import com.dbSpring.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  PizzaRepository extends JpaRepository<Pizza, Long> {
}
