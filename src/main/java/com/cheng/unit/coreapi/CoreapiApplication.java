package com.cheng.unit.coreapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cheng.unit.coreapi.dao")
public class CoreapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreapiApplication.class, args);
    }

}
