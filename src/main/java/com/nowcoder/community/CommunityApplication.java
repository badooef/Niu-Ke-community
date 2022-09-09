package com.nowcoder.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@MapperScan("com.nowcoder.community.dao")
@SpringBootApplication
public class CommunityApplication {
@PostConstruct
public void init(){
    //是解决netty启动冲突的问题
    //see Netty4Utils
    System.setProperty("es.set.netty.runtime.available.processors","false");
}

    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }

}
