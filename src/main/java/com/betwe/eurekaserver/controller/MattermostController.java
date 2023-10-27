package com.betwe.eurekaserver.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betwe.eurekaserver.api.MattermostAPI;
import com.betwe.eurekaserver.util.BaseAPI;

/**
 * @author imjh8751
 *
 */
@RestController
@RequestMapping("/mattermost")
public class MattermostController extends BaseAPI {

    /**
     * @return
     */
    @GetMapping("/")
    public String getMessage() {
        // 디버그 로그 출력
        logger.info("Debug log message");
        return "good request";
    }
    
    /**
     * @param id
     */
    @GetMapping("/getMessage/{id}")
    public void getMessage(@PathVariable String id) {
        logger.info("Mattermost getMessage Start>>>>>>>> " + id);
    }
    
    /**
     * @param message
     */
    @PostMapping("/sendMessage")
    public void sendMessage(String message) {
        logger.info("Mattermost sendMessage Start>>>>>>>> " + message);
        
        try {
			MattermostAPI.sendMessage("jenkins", message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
