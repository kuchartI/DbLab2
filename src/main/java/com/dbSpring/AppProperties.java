package com.dbSpring;

import com.dbSpring.generator.Generator;
import com.dbSpring.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "demo")
@Validated
public class AppProperties {

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

    private Integer category;
    private Integer pizza;
    private Integer restaurant;
    private Integer topping;
    private Integer position;
    private Integer employee;
    private Integer order;

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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getPizza() {
        return pizza;
    }

    public void setPizza(Integer pizza) {
        this.pizza = pizza;
    }

    public Integer getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Integer restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getTopping() {
        return topping;
    }

    public void setTopping(Integer topping) {
        this.topping = topping;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}