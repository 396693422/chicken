package com.chicken.provider.boss.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ChickenBossUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChickenBossUserApplication.class, args);
	}

}
