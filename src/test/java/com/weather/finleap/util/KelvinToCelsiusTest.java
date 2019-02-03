package com.weather.finleap.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KelvinToCelsiusTest {

    KelvinToCelsius parser;

    @Before
    public void setUp(){
        parser = new KelvinToCelsius();
    }

    @Test
    public void verifyParser(){
        Double kelvin = 273.15;
        Double answer = 0.0;
        assertEquals(answer,parser.apply(kelvin));
    }
}