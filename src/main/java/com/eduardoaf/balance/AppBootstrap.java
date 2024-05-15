package com.eduardoaf.balance;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.boot.SpringApplication;
import com.eduardoaf.balance.boot_config.WebSecurityConfig;

@Import(WebSecurityConfig.class)
@SpringBootApplication
public class AppBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(AppBootstrap.class, args);
    }

}