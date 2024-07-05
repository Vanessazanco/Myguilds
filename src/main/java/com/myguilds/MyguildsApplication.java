package com.myguilds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyguildsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyguildsApplication.class, args);
	}

}
