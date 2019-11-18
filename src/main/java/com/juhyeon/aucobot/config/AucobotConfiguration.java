package com.juhyeon.aucobot.config;


import com.juhyeon.aucobot.bot.event.IEventQueue;
import com.juhyeon.aucobot.service.CustomIssueEventSensor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableScheduling
public class AucobotConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public IEventQueue eventQueue() {
        return new IEventQueue();
    }

    @Bean(name = "aucobotThreadPoolManager")
    public ThreadPoolTaskExecutor aucobotThreadPoolManager() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(100);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }

}
