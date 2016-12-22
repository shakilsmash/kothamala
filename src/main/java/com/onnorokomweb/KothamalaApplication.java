package com.onnorokomweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.inject.Inject;

@SpringBootApplication
public class KothamalaApplication {

    private static final Logger logger = LoggerFactory.getLogger(KothamalaApplication.class);

    @Inject
    private Environment environment;

    public static void main(String[] args) {
        Environment environment = SpringApplication.run(KothamalaApplication.class, args).getEnvironment();
        logger.info("log testing");
    }
}
