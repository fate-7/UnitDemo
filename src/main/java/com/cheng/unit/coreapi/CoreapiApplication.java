package com.cheng.unit.coreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CoreapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreapiApplication.class, args);
    }

}
