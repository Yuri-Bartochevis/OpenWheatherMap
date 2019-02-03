package com.weather.finleap.util;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class KelvinToCelsius implements Function<Double,Double> {
    @Override
    public Double apply(Double kelvin) {
        return kelvin - 273.15;
    }
}
