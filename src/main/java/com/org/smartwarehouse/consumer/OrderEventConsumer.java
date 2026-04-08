package com.org.smartwarehouse.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.org.smartwarehouse.config.RabbitMQConfig;
import com.org.smartwarehouse.dto.event.OrderCreatedEvent;
import com.org.smartwarehouse.integration.elasticsearch.ElasticsearchService;

@Component
public class OrderEventConsumer {
	
	  private final ElasticsearchService elasticsearchService;

	    public OrderEventConsumer(ElasticsearchService elasticsearchService) {
	        this.elasticsearchService = elasticsearchService;
	    }

	    @RabbitListener(queues = RabbitMQConfig.ORDER_CREATED_QUEUE)
	    public void handleOrderCreated(OrderCreatedEvent event) {
	        System.out.println("📥 EVENT RECEIVED:");
	        System.out.println(event);

	        elasticsearchService.indexOrderCreatedEvent(event);
	    }
    
    
}