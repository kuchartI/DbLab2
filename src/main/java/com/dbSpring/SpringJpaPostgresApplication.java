package com.dbSpring;

import com.dbSpring.generator.Generator;
import com.dbSpring.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties({AppProperties.class})
public class SpringJpaPostgresApplication {

    @Autowired
    private static PizzaService pizzaService;

    @Autowired
    private static CategoryService categoryService;

    @Autowired
    private static RestaurantService restaurantService;

    @Autowired
    private static ToppingService toppingService;

    @Autowired
    private static PositionService positionService;

    @Autowired
    private static EmployeeService employeeService;

    @Autowired
    private static RestaurantPizzaService restaurantPizzaService;

    @Autowired
    private static OrderService orderService;

    @Autowired
    private static PizzaOrderService pizzaOrderService;

    @Autowired
    private static PizzaToppingsService pizzaToppingsService;


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringJpaPostgresApplication.class, args);
        AppProperties properties = context.getBean(AppProperties.class);
        generateData(properties.getCategory(), properties.getPizza(), properties.getRestaurant(),
                properties.getTopping(), properties.getPosition(), properties.getEmployee(), properties.getOrder());
    }

    public static void generateData(int category, int pizza, int restaurant,
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