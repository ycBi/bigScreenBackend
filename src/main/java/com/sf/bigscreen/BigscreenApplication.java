package com.sf.bigscreen;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BigscreenApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigscreenApplication.class, args);
    }

}
