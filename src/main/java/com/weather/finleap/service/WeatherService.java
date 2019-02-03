package com.weather.finleap.service;

import com.google.gson.Gson;
import com.weather.finleap.client.RestClient;
import com.weather.finleap.model.ResponseWeather;
import com.weather.finleap.model.openweather.ResponseApi;
import com.weather.finleap.util.KelvinToCelsius;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    public static final int DAYS_TO_ADD_CASE_RULE = 3;
    public static final LocalTime START_DAY_SHIFT = LocalTime.of(5,59,59);
    public static final LocalTime END_DAY_SHIFT = LocalTime.of(18,0,1);

    @Value("${open.weather.api.uri}")
    private String fiveDaysForecast;

    RestClient restClient;
    KelvinToCelsius converter;

    @Autowired
    public WeatherService(RestClient restClient, KelvinToCelsius converter) {
        this.converter = converter;
        this.restClient = restClient;
    }


    @Cacheable(value = "WEATHER_CACHE",key="#city", unless="#result == null")
    public ResponseWeather getWeather(String city) {
        ResponseApi responseApi = restClient.get(fiveDaysForecast, city);
        LocalDate newdate = LocalDate.now().plusDays(DAYS_TO_ADD_CASE_RULE);

        Map<LocalDate, List<com.weather.finleap.model.openweather.List>> groupedByDays = responseApi
                .getList()
                .stream()
                .collect(Collectors.groupingBy(this::getLocalDateFromTimestamp));


        List<com.weather.finleap.model.openweather.List> listToBeProcessed = groupedByDays.entrySet()
                .stream()
                .filter(element -> element.getKey().isBefore(newdate))
                .map(Map.Entry::getValue)
                .flatMap(List::stream)
                .collect(Collectors.toList());


        return process(listToBeProcessed);

    }

    protected ResponseWeather process(List<com.weather.finleap.model.openweather.List> listToBeProcessed) {
        double pressureAverage = 0,dayAverage = 0,nightAverage = 0;
        int dayCount = 0, nightCount = 0;

        for (com.weather.finleap.model.openweather.List weather : listToBeProcessed) {
            if (isDay(weather.getDt())) {
                dayAverage += weather.getMain().getTemp();
                dayCount ++;
            }else {
                nightAverage += weather.getMain().getTemp();
                nightCount ++;
            }
            pressureAverage += weather.getMain().getPressure();
        }

        pressureAverage /= listToBeProcessed.size();
        dayAverage /= dayCount;
        nightAverage /= nightCount;

        return new ResponseWeather(pressureAverage, converter.apply(dayAverage), converter.apply(nightAverage));
    }

    protected boolean isDay(Integer dt) {
        LocalTime currentTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(dt), ZoneId.of("GMT")).toLocalTime();
        return currentTime.isAfter(START_DAY_SHIFT) && currentTime.isBefore(END_DAY_SHIFT);
    }


    protected LocalDate getLocalDateFromTimestamp(com.weather.finleap.model.openweather.List element) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(element.getDt()), ZoneId.of("GMT")).toLocalDate();
    }


}
