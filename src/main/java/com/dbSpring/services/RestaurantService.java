package com.dbSpring.services;

import com.dbSpring.entity.Restaurant;
import com.dbSpring.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Long count() {
        return restaurantRepository.count();
    }

    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public void createRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }
}
