package com.imooc.springdatademo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class SpringDataDemoApplicationTests {
    @Resource
    private RedisTemplate redisTemplate;
    
    @Resource
    private ObjectMapper objectMapper;
    
    @Test
    void testInit(){
        //PING PONG 心跳机制是否连接成功
        String pong = redisTemplate.getConnectionFactory().getConnection().ping();
        System.out.println("pong = " + pong);
    }
    @Test
    public void testString(){
        //插入一条数据
        redisTemplate.opsForValue().set("username","zhangsan");
        //获取一条数据
        Object username = redisTemplate.opsForValue().get("username");
        System.out.println("username = " + username);
    }
    
    @Test
    void contextLoads() throws JsonProcessingException {
        Person person = new Person("john",23);
        String string = objectMapper.writeValueAsString(person);
        redisTemplate.opsForValue().set("name",string);
        Object o = redisTemplate.opsForValue().get("name");
        System.out.println(o);
    }

}
