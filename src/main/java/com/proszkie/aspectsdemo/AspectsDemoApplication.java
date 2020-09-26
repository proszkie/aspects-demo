package com.proszkie.aspectsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class AspectsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AspectsDemoApplication.class, args);
	}

}
