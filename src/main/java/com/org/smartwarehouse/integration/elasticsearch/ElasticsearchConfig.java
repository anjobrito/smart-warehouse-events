package com.org.smartwarehouse.integration.elasticsearch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ElasticsearchConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}