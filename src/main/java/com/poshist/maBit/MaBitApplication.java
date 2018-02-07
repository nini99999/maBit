package com.poshist.maBit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by poshist on 18-1-26.
 */
@EnableAutoConfiguration
@SpringBootApplication
public class MaBitApplication  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MaBitApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MaBitApplication.class, args);

    }
}