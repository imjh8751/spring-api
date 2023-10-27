package com.betwe.eurekaserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betwe.eurekaserver.api.GitlabAPI;
import com.betwe.eurekaserver.util.BaseAPI;

/**
 * @author imjh8751
 *
 */
@RestController
@RequestMapping("/gitlab")
public class GitlabController extends BaseAPI {


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
     * 
     */
    @PostMapping("/getUsers")
    public void getUsers() {
        try {
        	GitlabAPI.getUsers();
        	//GitlabAPI.getUsers2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
