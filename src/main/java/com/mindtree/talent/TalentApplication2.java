package com.mindtree.talent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class TalentApplication2 {

	public static void main(String[] args) {
		SpringApplication.run(TalentApplication2.class, args);
	}

}
