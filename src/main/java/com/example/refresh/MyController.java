package com.example.refresh;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.identityHashCode;

@RestController
@RefreshScope
@Slf4j
public class MyController {

    private final Features features;
    private final String test;

    public MyController(Features features,
                        @Value("${features.test}") String test) {
        this.features = features;
        this.test = test;
    }

    @Scheduled(fixedDelay = 1000)
    public void trace(){
        log.error("{} / {}", features.getTest(), test);
    }

    @GetMapping("/o")
    public String hello(){
        return features.getTest();
    }

    @ConfigurationProperties("features")
    @Slf4j
    public static class Features {

        @Getter
        private String test;

        public void setTest(String test) {
            log.warn("{} Was/ will be : {} / {}", identityHashCode(this), this.test, test);
            this.test = test;
        }
    }

}
