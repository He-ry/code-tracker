package com.tracker.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = {"com.tracker"})
public class TrackerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackerServerApplication.class, args);
    }

}
