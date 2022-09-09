package com.nowcoder.community;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
@Slf4j
public class ThreadTest {

    //JDK普通的线程池
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
//JDK可执行定时任务的线程池
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
private void sleep(long m){
    try {
        Thread.sleep(m);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}

@Test
    public void testExecutorsService(){

    Runnable task =new Runnable() {
        @Override
        public void run() {
            log.debug("hello ExecutorsService");
        }
    };
    for (int i = 0; i < 10; i++) {
        executorService.submit(task);
    }
sleep(10000);
}

}
