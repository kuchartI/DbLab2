package com.dbSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties({AppProperties.class})
public class SpringJpaPostgresApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringJpaPostgresApplication.class, args);
        AppProperties properties = context.getBean(AppProperties.class);
        properties.generateData(properties.getCategory(), properties.getPizza(), properties.getPizza(),
                properties.getTopping(), properties.getPosition(), properties.getEmployee(), properties.getOrder());
    }
}