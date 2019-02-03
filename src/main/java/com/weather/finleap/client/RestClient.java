package com.weather.finleap.client;

import com.weather.finleap.model.openweather.ResponseApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestClient {

    @Value("${open.weather.api.url}")
    private String server;


    @Value("${open.weather.api.appid}")
    private String appid;

    private RestTemplate rest;
    private HttpHeaders headers;
    private HttpStatus status;

    public RestClient() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        //headers.add("Accept", "*/*");
    }

    public ResponseApi get(String uri, String param) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(server + uri)
                .queryParam("q", param)
                .queryParam("appid", appid);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<ResponseApi> response = rest.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                ResponseApi.class);

        return response.getBody();
    }

}
