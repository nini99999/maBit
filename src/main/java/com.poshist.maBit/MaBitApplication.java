package com.poshist.maBit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by poshist on 18-1-26.
 */
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