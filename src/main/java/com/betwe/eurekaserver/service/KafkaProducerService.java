package com.betwe.eurekaserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    
	private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(String topic, String message) {
        log.debug(String.format("Produce message : %s", message));
        this.kafkaTemplate.send(topic, message);
    }
}
