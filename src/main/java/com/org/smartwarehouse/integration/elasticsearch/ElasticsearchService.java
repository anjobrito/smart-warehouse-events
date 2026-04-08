package com.org.smartwarehouse.integration.elasticsearch;

import com.org.smartwarehouse.dto.event.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ElasticsearchService {

    private final RestTemplate restTemplate;

    @Value("${elasticsearch.url}")
    private String elasticsearchUrl;

    public ElasticsearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void indexOrderCreatedEvent(OrderCreatedEvent event) {
        String url = elasticsearchUrl + "/orders-events/_doc";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OrderCreatedEvent> request = new HttpEntity<>(event, headers);

        String response = restTemplate.postForObject(url, request, String.class);

        System.out.println("📦 EVENT INDEXED IN ELASTICSEARCH:");
        System.out.println(response);
    }
}