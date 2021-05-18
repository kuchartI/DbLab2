package com.dbSpring.services;

import com.dbSpring.entity.Category;
import com.dbSpring.entity.Pizza;
import com.dbSpring.entity.Restaurant;
import com.dbSpring.repository.CategoryRepository;
import com.dbSpring.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    @Autowired
    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public Long count() {
        return pizzaRepository.count();
    }

    public Pizza findById(Long id) {
        return pizzaRepository.findById(id).orElse(null);
    }
    public void createPizza(Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }
}
