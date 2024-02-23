package com.betwe.eurekaserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    
	private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        log.debug(String.format("Produce message : %s", message));
        this.kafkaTemplate.send(topic, message);
    }
}
