package com.betwe.eurekaserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betwe.eurekaserver.api.TelegramAPI;
import com.betwe.eurekaserver.constant.TELEGRAM_CONST;
import com.betwe.eurekaserver.util.BaseAPI;

/**
 * @author imjh8751
 *
 */
@RestController
@RequestMapping("/telegram")
public class TelegramController extends BaseAPI {

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
        logger.info("Telegram getMessage Start>>>>>>>> " + id);
    }
    
    /**
     * 
     */
    @GetMapping("/getChatInfo")
    public void getChatInfo() {
        logger.info("Telegram getMessage Start>>>>>>>> ");
        
        try {
        	TelegramAPI.getChatInfo(TELEGRAM_CONST.CHAT_ID);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
    /**
     * @param message
     */
    @PostMapping("/sendMessage")
    public void sendMessage(String message) {
        logger.info("Telegram sendMessage Start>>>>>>>> " + message);
        
        try {
        	TelegramAPI.sendMessage(message);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
}
