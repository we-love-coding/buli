package com.bibu.product.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"com.bibu"})
@EnableDiscoveryClient
@MapperScan("com.bibu.product.dal.mapper")
@SpringBootApplication(scanBasePackages = {"com.bibu.product", "com.x"})
public class StartMain {

	public static void main(String[] args) {
		SpringApplication.run(StartMain.class, args);
	}

}
