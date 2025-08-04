package com.stayfinder.sf_usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;


@SpringBootApplication
@EnableDiscoveryClient
@EnableMethodSecurity
public class SfUsermanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SfUsermanagementApplication.class, args);
	}

}
