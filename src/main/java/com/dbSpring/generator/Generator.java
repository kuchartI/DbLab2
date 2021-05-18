package com.dbSpring.generator;

import com.dbSpring.entity.*;
import com.dbSpring.services.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

public class Generator {

    private final int categoryCount;
    private final int pizzaCount;
    private final int restaurantCount;
    private final int toppingCount;
    private final int positionCount;
    private final int employeeCount;
    private final int orderCount;
    Random random = new Random();

    public Generator(int categoryCount, int pizzaCount, int restaurantCount,
                     int toppingCount, int positionCount, int employeeCount
            , int orderCount) {
        this.categoryCount = categoryCount;
        this.pizzaCount = pizzaCount;
        this.restaurantCount = restaurantCount;
        this.toppingCount = toppingCount;
        this.positionCount = positionCount;
        this.employeeCount = employeeCount;
        this.orderCount = orderCount;
    }

    public void generateCategory(CategoryService categoryService) {
        for (int i = 0; i < this.categoryCount; i++) {
            Category category = new Category();
            category.setCategory_name(DataGenerator.generateText(30, random));
            category.setPizza_side(DataGenerator.generateText(30, random));
            categoryService.createCategory(category);
        }
    }

    public void generatePizza(PizzaService pizzaService, CategoryService categoryService,
                              RestaurantPizzaService restaurantPizzaService,
                              RestaurantService restaurantService) {
        for (int i = 0; i < this.pizzaCount; i++) {
            Pizza pizza = new Pizza();
            pizza.setId(pizzaService.count() + 1);
            pizza.setName(DataGenerator.generateText(30, random));
            pizza.setDescription(DataGenerator.generateText(100, random));
            pizza.setWeight(random.nextInt(999) + 1);
            pizza.setSize(random.nextInt(30) + 20);
            pizza.setCategory_id(categoryService.findAll()
                    .get(random.nextInt(categoryService.findAll().size())));
            pizzaService.createPizza(pizza);
            generateRestaurantPizza(restaurantPizzaService, restaurantService, pizza);
        }
    }

    public void generateRestaurant(RestaurantService restaurantService, RestaurantPizzaService restaurantPizzaService,
                                   PizzaService pizzaService) {
        for (int i = 0; i < this.restaurantCount; i++) {
            Restaurant restaurant = new Restaurant();
            restaurant.setAddress(DataGenerator.generateText(100, random));
            restaurant.setStart_of_work(DataGenerator.generateTime(random)[0]);
            restaurant.setEnd_of_work(DataGenerator.generateTime(random)[1]);
            restaurant.setPhone(DataGenerator.generatePhone(random));
            restaurantService.createRestaurant(restaurant);
            generateRestaurantPizza(restaurantPizzaService, pizzaService, restaurant);
        }
    }

    public void generateToppings(ToppingService toppingService) {
        for (int i = 0; i < this.toppingCount; i++) {
            Topping topping = new Topping();
            topping.setTopping_name(DataGenerator.generateText(30, random));
            topping.setPrice(DataGenerator.generatePrice(random, 9, 90));
            toppingService.createTopping(topping);
        }
    }

    public void generatePosition(PositionService positionService) {
        for (int i = 0; i < this.positionCount; i++) {
            Position position = new Position();
            position.setPosition_name(DataGenerator.generateText(20, random));
            position.setSalary(random.nextInt(200000) + 30000);
            position.setAmount_of_premium(random.nextInt(50000) + 5000);
            positionService.createPosition(position);
        }
    }

    public void generateEmployee(EmployeeService employeeService, RestaurantService restaurantService,
                                 PositionService positionService) {
        for (int i = 0; i < this.employeeCount; i++) {
            Employee employee = new Employee();
            employee.setEmployee_name(DataGenerator.generateText(30, random));
            employee.setEmployee_surname(DataGenerator.generateText(30, random));
            employee.setRestaurant_id(restaurantService.findAll().
                    get(random.nextInt(restaurantService.findAll().size())));
            employee.setPhone(DataGenerator.generatePhone(random));
            employee.setSchedule(DataGenerator.generateSchedule(random));
            employee.setPosition_id(positionService.findAll().
                    get(random.nextInt(positionService.findAll().size())));
            employeeService.createEmployee(employee);
        }
    }

    // При каждом создании пиццы для каждого уже созданного ресторана создается пицца и все данные о ней
    public void generateRestaurantPizza(RestaurantPizzaService restaurantPizzaService, RestaurantService restaurantService,
                                        Pizza pizza) {
        for (long i = 0; i < restaurantService.count(); i++) {
            RestaurantPizza restaurantPizza = new RestaurantPizza();
            restaurantPizza.setRestaurant_id(restaurantService.findById(i));
            restaurantPizza.setPizza_id(pizza);
            restaurantPizza.setPrice(DataGenerator.generatePrice(random, 200, 600));
            restaurantPizza.setAvailable(DataGenerator.generateAvailable(random));
            restaurantPizzaService.createRestaurantPizzaService(restaurantPizza);
        }
    }

    public void generateRestaurantPizza(RestaurantPizzaService restaurantPizzaService, PizzaService pizzaService,
                                        Restaurant restaurant) {
        for (long i = 0; i < pizzaService.count(); i++) {
            RestaurantPizza restaurantPizza = new RestaurantPizza();
            restaurantPizza.setRestaurant_id(restaurant);
            restaurantPizza.setPizza_id(pizzaService.findById(i));
            restaurantPizza.setPrice(DataGenerator.generatePrice(random, 200, 600));
            restaurantPizza.setAvailable(DataGenerator.generateAvailable(random));
            restaurantPizzaService.createRestaurantPizzaService(restaurantPizza);
        }
    }

    public void generateOrder(OrderService orderService, RestaurantService restaurantService,
                              PizzaService pizzaService, PizzaOrderService pizzaOrderService
            , ToppingService toppingService, PizzaToppingsService pizzaToppingsService) {
        for (long i = 0; i < orderCount; i++) {
            Order order = new Order();
            order.setRestaurant_id(restaurantService.findAll().
                    get(random.nextInt(restaurantService.findAll().size())));
            order.setDate(new Date());
            order.setPrice(DataGenerator.generatePrice(random, 200, 600));
            orderService.createOrder(order);
            generatePizzaOrder(order, pizzaService, pizzaOrderService, toppingService, pizzaToppingsService);

        }
    }

    //Количество пицц в заказе от 1 до 10(можно менять максимальное значение)
    public void generatePizzaOrder(Order order, PizzaService pizzaService, PizzaOrderService pizzaOrderService,
                                   ToppingService toppingService, PizzaToppingsService pizzaToppingsService) {
        BigDecimal price = new BigDecimal(0);
        for (int i = 0; i < random.nextInt(10) + 1; i++) {
            PizzaOrder pizzaOrder = new PizzaOrder();
            pizzaOrder.setOrder_id(order);
            pizzaOrder.setPizza_id(pizzaService.findAll().get(random.nextInt(pizzaService.findAll().size())));
            pizzaOrderService.createPizzaOrder(pizzaOrder);
            generatePizzaToppings(pizzaOrder, toppingService, pizzaToppingsService);
        }
    }

    //Генерируем топпинг на пиццу тоже по умолчанияю , но от 0 до 9(т.к. пицца может быть без топпинга)
    public void generatePizzaToppings(PizzaOrder pizzaOrder,
                                      ToppingService toppingService, PizzaToppingsService pizzaToppingsService) {
        for (int i = 0; i < random.nextInt(10); i++) {
            PizzaToppings pizzaToppings = new PizzaToppings();
            pizzaToppings.setTopping_id(toppingService.findById((long) random.nextInt(toppingService.findAll().size())));
            pizzaToppings.setPizza_order_id(pizzaOrder);
            pizzaToppingsService.createPizzaToppings(pizzaToppings);
        }
    }
}
