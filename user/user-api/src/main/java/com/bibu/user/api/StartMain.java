package com.bibu.user.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.bibu.user.dal.mapper")
@SpringBootApplication(scanBasePackages = {"com.bibu.user", "com.x"})
public class StartMain {

	public static void main(String[] args) {
		SpringApplication.run(StartMain.class, args);
	}

}
