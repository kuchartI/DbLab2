package com.dbSpring.services;

import com.dbSpring.entity.Employee;
import com.dbSpring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Long count() {
        return employeeRepository.count();
    }

    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
