package com.onnorokomweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.inject.Inject;

@SpringBootApplication
public class KothamalaApplication {

    @Inject
    private Environment environment;

	public static void main(String[] args) {
		Environment environment = SpringApplication.run(KothamalaApplication.class, args).getEnvironment();
    }
}
