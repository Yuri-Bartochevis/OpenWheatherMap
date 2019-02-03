package com.weather.finleap;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

    @EnableCaching
    @SpringBootApplication
    @EntityScan("com.weather.finleap")
    public class Boot {
        public static void main(String[] args) {
            SpringApplication.run(Boot.class, args);
        }
}
