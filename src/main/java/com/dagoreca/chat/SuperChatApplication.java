package com.dagoreca.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SuperChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperChatApplication.class, args);
	}

}
