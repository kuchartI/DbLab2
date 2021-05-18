package com.dbSpring.services;

import com.dbSpring.entity.PizzaOrder;
import com.dbSpring.entity.Topping;
import com.dbSpring.repository.PizzaOrderRepository;
import com.dbSpring.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PizzaOrderService {

    @Autowired
    private final PizzaOrderRepository pizzaOrderRepository;

    public PizzaOrderService(PizzaOrderRepository pizzaOrderRepository) {
        this.pizzaOrderRepository = pizzaOrderRepository;
    }

    public Long count() {
        return pizzaOrderRepository.count();
    }

    public PizzaOrder findById(Long id) {
        return pizzaOrderRepository.findById(id).orElse(null);
    }

    public List<PizzaOrder> findAll() {
        return pizzaOrderRepository.findAll();
    }

    public void createPizzaOrder(PizzaOrder pizzaOrder) {
        pizzaOrderRepository.save(pizzaOrder);
    }
}