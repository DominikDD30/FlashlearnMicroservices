package com.dominik.pexelservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {

    @Value("${api.pexel.url}")
    private String PEXEL_URL;

    @Value("${api.pexel.apikey}")
    private  String PEXEL_API_KEY;

    private static final int CONNECTION_TIMEOUT=5000;
    private static final int REQUEST_TIMEOUT=5000;

    @Bean
    RestClient restClient(){
        return  RestClient.builder()
                .baseUrl(PEXEL_URL)
                .requestFactory(getClientHttpRequestFactory())
                .defaultHeader("Authorization", PEXEL_API_KEY)
                .build();
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(CONNECTION_TIMEOUT);
        factory.setReadTimeout(REQUEST_TIMEOUT);
        return factory;
    }

}
