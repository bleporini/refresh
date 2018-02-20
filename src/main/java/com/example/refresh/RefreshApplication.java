package com.example.refresh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({MyController.Features.class})
public class RefreshApplication {

	public static void main(String[] args) {
		SpringApplication.run(RefreshApplication.class, args);
	}


    @Bean(destroyMethod="shutdown")
    public ScheduledExecutorService taskExecutor() {
        return Executors.newScheduledThreadPool(5);
    }

}
