package com.wildpulse.backend;

import static com.wildpulse.backend.constants.WPDefaultConstants.WP_PACKAGE_SCAN_PATH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan(WP_PACKAGE_SCAN_PATH)
@EnableAspectJAutoProxy
public class WPBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WPBackendApplication.class, args);
    }
}
