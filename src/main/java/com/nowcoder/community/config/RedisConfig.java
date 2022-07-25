package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

@Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
    //连接数据库，连接是由连接工厂创立的，要引入
RedisTemplate<String,Object> template = new RedisTemplate<>();
template.setConnectionFactory(factory);//有了访问数据库的能力

    //设置key的序列化方式 能序列化字符串的序列化器（同时自带反序列化）
template.setKeySerializer(RedisSerializer.string());
    //设置value的序列化方式
template.setValueSerializer(RedisSerializer.json());
    //设置hash的key的序列化方式
template.setHashKeySerializer(RedisSerializer.string());
    //设置hash的value的序列化方式
template.setHashValueSerializer(RedisSerializer.json());
//为了使得配置好之后的配置生效
template.afterPropertiesSet();
return template;
}

}
