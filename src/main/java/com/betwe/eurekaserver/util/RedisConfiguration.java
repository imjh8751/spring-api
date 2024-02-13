package com.betwe.eurekaserver.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {
	@Value("${spring.data.redis.host}")
	private String redisHost;
	@Value("${spring.data.redis.port}")
	private int redisPort;
	@Value("${spring.data.redis.password}")
	private String redisPassword;
	@Value("${spring.data.redis.cluster.nodes}")
	private String clusterNodes;
	
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		List<String> clusterNodeList = Arrays.asList(StringUtils.split(clusterNodes, ','))
                .stream()
                .map(m -> m.trim())
                .collect(Collectors.toList());
		RedisClusterConfiguration redisStandaloneConfiguration = new RedisClusterConfiguration(clusterNodeList);
		redisStandaloneConfiguration.setPassword(redisPassword);
		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
		return lettuceConnectionFactory;
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		return redisTemplate;
	}
	
}
