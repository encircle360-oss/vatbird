package com.encircle360.oss.vatbird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableFeignClients
@EnableScheduling
@SpringBootApplication
public class VatbirdApplication {

	public static void main(String[] args) {
		SpringApplication.run(VatbirdApplication.class, args);
	}
}
