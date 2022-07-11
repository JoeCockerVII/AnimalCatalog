package com.example.animalcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AnimalCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimalCatalogApplication.class, args);
    }

}
