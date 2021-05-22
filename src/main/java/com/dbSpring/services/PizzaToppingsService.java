package com.dbSpring.services;

import com.dbSpring.entity.PizzaToppings;
import com.dbSpring.repository.PizzaToppingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaToppingsService {

    @Autowired
    private final PizzaToppingsRepository pizzaToppingsRepository;

    public PizzaToppingsService(PizzaToppingsRepository pizzaToppingsRepository) {
        this.pizzaToppingsRepository = pizzaToppingsRepository;
    }

    public int count() {
        return (int) pizzaToppingsRepository.count();
    }

    public PizzaToppings findById(Long id) {
        return pizzaToppingsRepository.findById(id).orElse(null);
    }

    public List<PizzaToppings> findAll() {
        return pizzaToppingsRepository.findAll();
    }

    public void createPizzaToppings(PizzaToppings pizzaToppings) {
        pizzaToppingsRepository.save(pizzaToppings);
    }
}