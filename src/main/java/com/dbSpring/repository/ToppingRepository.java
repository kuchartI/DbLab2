package com.dbSpring.repository;

import com.dbSpring.entity.Topping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToppingRepository extends JpaRepository<Topping, Long> {
}
