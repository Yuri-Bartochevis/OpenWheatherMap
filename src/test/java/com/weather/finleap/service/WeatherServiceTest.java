package com.weather.finleap.service;

import com.google.gson.Gson;
import com.weather.finleap.client.RestClient;
import com.weather.finleap.model.ResponseWeather;
import com.weather.finleap.model.openweather.List;
import com.weather.finleap.model.openweather.ResponseApi;
import com.weather.finleap.util.KelvinToCelsius;
import com.weather.finleap.util.TestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;

import static java.nio.charset.Charset.defaultCharset;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {

    @Mock
    RestClient restClient;
    KelvinToCelsius converter;
    WeatherService weatherService;
    TestUtils utils;
    Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = new Gson();
        utils = new TestUtils();
        weatherService = new WeatherService(restClient, new KelvinToCelsius());
    }

    @Test(expected = NullPointerException.class)
    public void getDataFailFromOpenWeather() {
        ResponseApi responseApi = null;
        Mockito.when(restClient.get(Mockito.any(),Mockito.eq("london"))).thenReturn(null);
        ResponseWeather response = weatherService.getWeather("london");
        }


    @Test
    public void getDataSucessFromOpenWeather() throws IOException {
        ResponseApi responseApi = gson.fromJson(utils.readResource("ResponseApi.txt", defaultCharset()),ResponseApi.class);
        Mockito.when(restClient.get(Mockito.any(),Mockito.eq("london"))).thenReturn(responseApi);
        ResponseWeather response = weatherService.getWeather("london");
        Assert.assertNotNull(response);
    }


    @Test
    public void verifyIsDayShift(){
        assertTrue(weatherService.isDay(1549173600));
    }

    @Test
    public void verifyIsNotDayShift(){
        assertFalse(weatherService.isDay(1549238400));
    }

    @Test
    public void verifyLocalDateParser() throws IOException {
        weatherService.getLocalDateFromTimestamp(gson.fromJson(utils.readResource("OpenWeatherDetails.txt", defaultCharset()), List.class));
    }

    @Test
    public void verifyProcess() throws IOException {
        List[] element = gson.fromJson(utils.readResource("OpenWeatherList.txt", defaultCharset()), List[].class);
        java.util.List<List> array = Arrays.asList(element);
        weatherService.process(array);
    }


}