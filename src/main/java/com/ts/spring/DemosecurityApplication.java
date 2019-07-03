package com.ts.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.ts.spring.dao")
@ServletComponentScan("com.ts.spring")
public class DemosecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemosecurityApplication.class, args);
    }

}
