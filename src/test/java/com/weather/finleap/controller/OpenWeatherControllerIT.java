package com.weather.finleap.controller;

import com.weather.finleap.model.ResponseWeather;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

    @IfProfileValue(name = "integrationTest", values = {"true"})
    @ActiveProfiles("it")
    @RunWith(SpringRunner.class)
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    public class OpenWeatherControllerIT {

        @Autowired
        private WeatherController controller;

        @Before
        public void setUp() throws Exception {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        public void testingFlow() throws Exception {
            ResponseWeather r = controller.getWeather("london");
            Assert.assertNotNull(r);
        }
    }

