package com.betwe.eurekaserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/")
    public String getMessage() {
        // 디버그 로그 출력
        log.info("Debug log message");
        return "good request";
    }

    @PostMapping(value = "/send")
    public String sendMessage(@RequestParam("message") String message) {
        log.debug("message : " + message);
        this.producer.sendMessage("test", message);
        return "success";
    }

    @GetMapping(value = "/send2/{message}")
    public String sendMessage2(@PathVariable String message) {
        log.debug("message : " + message);
        this.producer.sendMessage("test", message);
        return "success";
    }
    
    @GetMapping("/send3")
    public String sendMessage3(@RequestBody String message) {
    	log.debug("message topic ::: " + message);
        this.producer.sendMessage("test", message);
        return "success";
    }
    
    @GetMapping("/send4")
    public String sendMessage3() {
    	log.debug("message topic ::: testteset");
        this.producer.sendMessage("test", "testteset!!!!!!!!!");
        return "success";
    }
}
