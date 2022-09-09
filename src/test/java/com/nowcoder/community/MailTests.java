package com.nowcoder.community;

import com.nowcoder.community.util.MailClient;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
public class MailTests {

@Autowired
private TemplateEngine templateEngine;

    @Autowired
    private MailClient mailClient;

    @Test
    public  void das(){
        mailClient.sendMail("554396780@qq.com","TEST","nihao 你好你好");
    }
@Test
    public  void  asda(){
    Context context = new Context();
    context.setVariable("username","sunday");
    String process = templateEngine.process("/mail/demo", context);
    System.out.println(process);

}
}
