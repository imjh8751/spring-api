package com.betwe.eurekaserver.controller;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betwe.eurekaserver.service.KafkaProducerService;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    
	private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final KafkaProducerService producer;

    @Autowired
    KafkaController(KafkaProducerService producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/send")
    public String sendMessage(@RequestParam("message") String message) {
        log.debug("message : " + message);
        this.producer.sendMessage("test", message);
        return "success";
    }

    @GetMapping(value = "/send")
    public String sendMessage2(@RequestParam("message") String message) {
        log.debug("message : " + message);
        this.producer.sendMessage("test", message);
        return "success";
    }
}
