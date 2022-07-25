package com.nowcoder.community.controller;

import com.nowcoder.community.util.SensitiveFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Sensitive {

    @Autowired
    private SensitiveFilter sensitiveFilter;

@Test
    public void asa(){
    String text = "这里可以赌博，可以嫖娼，哈哈哈哈！";
    String filter = sensitiveFilter.filter(text);
    System.out.println(filter);
}

}
