package com.nowcoder.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
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
@Test
    public void testHyperLogLog(){
    String key = "test:hll:01";
    for(int i = 0; i <= 10000; i++){
        redisTemplate.opsForHyperLogLog().add(key,i);
    }
    System.out.println(redisTemplate.opsForHyperLogLog().size(key));
}
}
