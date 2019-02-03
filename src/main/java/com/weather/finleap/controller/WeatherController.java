package com.weather.finleap.controller;

import com.weather.finleap.model.ResponseWeather;
import com.weather.finleap.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/data")
    public ResponseWeather getWheather(@RequestParam("CITY") String city ){
        return weatherService.getWeather(city);
    }

}
