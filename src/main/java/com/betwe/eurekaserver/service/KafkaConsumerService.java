package com.betwe.eurekaserver.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    
	private final Logger log = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "test", groupId = "itapi")
    public void consume(String message) throws IOException {
        log.debug(String.format("Consumed message : %s", message));
    }
}
