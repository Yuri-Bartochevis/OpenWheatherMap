package com.weather.finleap.util;

import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.Charset;

public class TestUtils {
    public String readResource(final String fileName, Charset charset) throws IOException {
        return Resources.toString(Resources.getResource(fileName), charset);
    }
}