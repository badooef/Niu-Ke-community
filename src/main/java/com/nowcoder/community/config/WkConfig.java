package com.nowcoder.community.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

@Slf4j
@Configuration
public class WkConfig {

    @Value("${wk.image.storage}")
private String wkImageStorage;

@PostConstruct
    public void init(){
//创建wk图片目录
    File file = new File(wkImageStorage);
    if(!file.exists()){
        file.mkdir();
        log.info("创建wk图片目录："+wkImageStorage);
    }

}


}
