package com.dbSpring.generator;

import com.dbSpring.entity.*;
import com.dbSpring.services.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import static java.math.BigDecimal.ROUND_DOWN;

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
            pizza.setPrice(DataGenerator.generatePrice(random, 200, 600));
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
        for (int i = 0; i < restaurantService.count(); i++) {
            RestaurantPizza restaurantPizza = new RestaurantPizza();
            restaurantPizza.setRestaurant_id(restaurantService.findAll().get(i));
            restaurantPizza.setPizza_id(pizza);
            restaurantPizza.setAvailable(DataGenerator.generateAvailable(random));
            restaurantPizzaService.createRestaurantPizzaService(restaurantPizza);
        }
    }

    public void generateRestaurantPizza(RestaurantPizzaService restaurantPizzaService, PizzaService pizzaService,
                                        Restaurant restaurant) {
        for (int i = 0; i < pizzaService.count(); i++) {
            RestaurantPizza restaurantPizza = new RestaurantPizza();
            restaurantPizza.setRestaurant_id(restaurant);
            restaurantPizza.setPizza_id(pizzaService.findAll().get(i));
            restaurantPizza.setAvailable(DataGenerator.generateAvailable(random));
            restaurantPizzaService.createRestaurantPizzaService(restaurantPizza);
        }
    }

    //Т.к. для того чтобы создать связи нам треубется уже созданный Order поэтмоу мы перезаписываем значение
    //на подсчитанный нами price с таблицы pizza_order и pizza_toppings.
    public void generateOrder(OrderService orderService, RestaurantService restaurantService,
                              PizzaService pizzaService, PizzaOrderService pizzaOrderService
            , ToppingService toppingService, PizzaToppingsService pizzaToppingsService) {
        for (long i = 0; i < orderCount; i++) {
            Order order = new Order();
            order.setRestaurant_id(restaurantService.findAll().
                    get(random.nextInt(restaurantService.findAll().size())));
            order.setDate(new Date());
            order.setPrice(DataGenerator.generatePrice(random, 10, 20));
            orderService.createOrder(order);
            order.setPrice(generatePizzaOrder(order, pizzaService,
                    pizzaOrderService, toppingService, pizzaToppingsService));
            orderService.createOrder(order);
        }
    }

    //Количество пицц в заказе от 1 до 10(можно менять максимальное значение)
    public BigDecimal generatePizzaOrder(Order order, PizzaService pizzaService, PizzaOrderService pizzaOrderService,
                                         ToppingService toppingService, PizzaToppingsService pizzaToppingsService) {
        double pricePizza = 0;
        for (int i = 0; i < random.nextInt(10) + 1; i++) {
            PizzaOrder pizzaOrder = new PizzaOrder();
            pizzaOrder.setOrder_id(order);
            Pizza pizza = pizzaService.findAll().get(random.nextInt(pizzaService.findAll().size()));
            pizzaOrder.setPizza_id(pizza);
            pizzaOrderService.createPizzaOrder(pizzaOrder);
            pricePizza += generatePizzaToppings(pizzaOrder, toppingService, pizzaToppingsService) +
                    Double.parseDouble("" + pizza.getPrice());
        }
        return new BigDecimal(pricePizza).setScale(2, ROUND_DOWN);
    }

    //Генерируем топпинг на пиццу тоже по умолчанияю , но от 0 до 9(т.к. пицца может быть без топпинга)
    public double generatePizzaToppings(PizzaOrder pizzaOrder,
                                        ToppingService toppingService, PizzaToppingsService pizzaToppingsService) {
        double price = 0;
        for (int i = 0; i < random.nextInt(10); i++) {
            PizzaToppings pizzaToppings = new PizzaToppings();
            Topping notNullTopping =
                    toppingService.findAll().get(random.nextInt(toppingService.findAll().size()));
            price += Double.parseDouble("" + notNullTopping.getPrice());
            pizzaToppings.setTopping_id(notNullTopping);
            pizzaToppings.setPizza_order_id(pizzaOrder);
            pizzaToppingsService.createPizzaToppings(pizzaToppings);
        }
        return price;
    }
}
