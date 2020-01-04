package com.yanggle.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer //开启Eureka Server
@SpringBootApplication
public class SpringCloudEurekaServerHA3Application {

    public static void main(String[] args) {

        SpringApplication.run(SpringCloudEurekaServerHA3Application.class, args);
    }
}
