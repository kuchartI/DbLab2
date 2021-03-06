package com.dbSpring.services;

import com.dbSpring.entity.RestaurantPizza;
import com.dbSpring.repository.RestaurantPizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantPizzaService {

    @Autowired
    private final RestaurantPizzaRepository restaurantPizzaRepository;

    public RestaurantPizzaService(RestaurantPizzaRepository restaurantPizzaRepository) {
        this.restaurantPizzaRepository = restaurantPizzaRepository;
    }

    public int count() {
        return (int) restaurantPizzaRepository.count();
    }

    public RestaurantPizza findById(Long id) {
        return restaurantPizzaRepository.findById(id).orElse(null);
    }

    public List<RestaurantPizza> findAll() {
        return restaurantPizzaRepository.findAll();
    }

    public void createRestaurantPizzaService(RestaurantPizza restaurantPizza) {
        restaurantPizzaRepository.save(restaurantPizza);
    }

}
