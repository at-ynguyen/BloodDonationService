package com.congybk;

import com.congybk.service.StorageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import javax.annotation.Resource;

/**
 * @author YNC
 */
@SpringBootApplication
public class BaseApp extends SpringBootServletInitializer{
    @Resource
    StorageService storageService;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BaseApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BaseApp.class, args);
    }

}
