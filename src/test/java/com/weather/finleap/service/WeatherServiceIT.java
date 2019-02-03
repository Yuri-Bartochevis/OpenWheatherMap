package com.weather.finleap.service;

import com.google.gson.Gson;
import com.weather.finleap.client.RestClient;
import com.weather.finleap.model.ResponseWeather;
import com.weather.finleap.model.openweather.ResponseApi;
import com.weather.finleap.util.KelvinToCelsius;
import com.weather.finleap.util.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.nio.charset.Charset.defaultCharset;
import static org.mockito.Mockito.*;

@IfProfileValue(name = "integrationTest", values = {"true"})
@ActiveProfiles("it")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherServiceIT {


    @MockBean
    private RestClient client;

    @MockBean
    private KelvinToCelsius converter;

    @Autowired
    private WeatherService service;

    Gson gson;
    TestUtils utils;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        gson = new Gson();
        utils = new TestUtils();
    }

    @Test
    public void testingCache() throws Exception {
        ResponseApi responseApi = gson.fromJson(utils.readResource("ResponseApi.txt", defaultCharset()),ResponseApi.class);
        when(client.get("/data/2.5/forecast","london")).thenReturn(responseApi);

        ResponseWeather responseLondon = service.getWeather("london");
        ResponseWeather responseLondonAgain = service.getWeather("london");
        verify(client, times(1)).get("/data/2.5/forecast","london");
    }
}

