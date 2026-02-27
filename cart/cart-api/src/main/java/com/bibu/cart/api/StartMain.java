package com.bibu.cart.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.bibu.cart.api.mapper")
@EnableDiscoveryClient
public class StartMain {

    public static void main(String[] args) {
        SpringApplication.run(StartMain.class, args);
    }
}