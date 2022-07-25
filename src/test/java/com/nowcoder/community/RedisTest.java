package com.nowcoder.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

@SpringBootTest
public class RedisTest {

@Autowired
    private RedisTemplate redisTemplate;

@Test
    public void asdasd(){
    String key = "asas";
    redisTemplate.opsForValue().set(key,1);

}
//编程式事务
    @Test
    public void testTransactional(){
    redisTemplate.execute(new SessionCallback() {
        @Override
        public Object execute(RedisOperations operations) throws DataAccessException {
            return null;
        }
    });
    }

}
