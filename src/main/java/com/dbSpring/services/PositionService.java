package com.dbSpring.services;

import com.dbSpring.entity.Pizza;
import com.dbSpring.entity.Position;
import com.dbSpring.repository.PizzaRepository;
import com.dbSpring.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public int count() {
        return (int) positionRepository.count();
    }

    public void createPosition(Position position) {
        positionRepository.save(position);
    }

    public List<Position> findAll() {
        return positionRepository.findAll();
    }
}
