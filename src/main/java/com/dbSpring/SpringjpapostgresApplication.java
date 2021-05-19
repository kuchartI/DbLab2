package com.dbSpring;

import com.dbSpring.generator.Generator;
import com.dbSpring.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringjpapostgresApplication {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ToppingService toppingService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RestaurantPizzaService restaurantPizzaService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PizzaOrderService pizzaOrderService;
    @Autowired
    private PizzaToppingsService pizzaToppingsService;


    public static void main(String[] args) {
        SpringApplication.run(SpringjpapostgresApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void generate() {
        generateData(1, 1, 1,
                1, 1, 1, 1);

    }

    public void generateData(int category, int pizza, int restaurant,
                             int topping, int position, int employee, int orderCount) {
        Generator generator = new Generator(category, pizza, restaurant,
                topping, position, employee, orderCount);
        generator.generateCategory(categoryService);
        generator.generatePizza(pizzaService, categoryService, restaurantPizzaService, restaurantService);
        generator.generateRestaurant(restaurantService, restaurantPizzaService, pizzaService);
        generator.generateToppings(toppingService);
        generator.generatePosition(positionService);
        generator.generateEmployee(employeeService, restaurantService, positionService);
        generator.generateOrder(orderService, restaurantService,
                pizzaService, pizzaOrderService, toppingService, pizzaToppingsService);
    }
}