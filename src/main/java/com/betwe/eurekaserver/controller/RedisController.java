package com.betwe.eurekaserver.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.betwe.eurekaserver.service.RedisService;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private static final String CLIENT_INFO_KEY = "client_info";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    RedisService redisService;

    @GetMapping("/save")
    public void saveClientInfo(jakarta.servlet.http.HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        String jsession = request.getSession().getId();
        String country = clientIp;
        long currentTime = Instant.now().toEpochMilli();

    	System.out.println("saveClientInfo ::: " + clientIp + " / jsession ::: " + jsession);
    	
        String key = "client:" + clientIp;
        Map<String, Object> data = new HashMap<>();
        data.put("clientIp", clientIp);
        data.put("jsession", jsession);
        data.put("firstAccessTime", currentTime);
        data.put("lastAccessTime", currentTime);
        data.put("country", country);

        redisTemplate.opsForHash().putAll(key, data);
        redisTemplate.expire(key, 600, TimeUnit.SECONDS); // 10 minutes TTL
    }

    @GetMapping("/get")
    public Map<String, Object> getClientInfo(String clientIp, String jSession) {
        System.out.println("test get ::: ");
        String key = getClientKey(clientIp, jSession);
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(key);
    }

    private String getClientKey(String clientIp, String jSession) {
        return CLIENT_INFO_KEY + ":" + clientIp + ":" + jSession;
    }
    
	@GetMapping("/getSessionId")
	public String getSessionId(HttpSession session) {
		return session.getId();
	}
    
    @RequestMapping(value = "/setString")
    @ResponseBody
    public void setValue(String testkey, String testvalue){
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        redisService.setValues(testkey,testvalue);
    }

    @RequestMapping(value = "/getString")
    @ResponseBody
    public Object getValue(String testkey){
        return redisService.getValues(testkey);
    }


    @RequestMapping(value = "/setSets")
    @ResponseBody
    public void setSets(String testkey,String... testvalues){
        redisService.setSets(testkey,testvalues);
    }

    @RequestMapping(value = "/getSets")
    @ResponseBody
    public Set getSets(String key){
        return redisService.getSets(key);
    }
}
