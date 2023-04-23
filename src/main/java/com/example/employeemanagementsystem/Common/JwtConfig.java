package com.example.employeemanagementsystem.Common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Value("${yy.jwt.key}")
    private String key;

    @Bean
    public JwtUtils jwtUtils(){
        return new JwtUtils(key);
    }
}
