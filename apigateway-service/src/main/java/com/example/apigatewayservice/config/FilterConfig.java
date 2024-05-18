package com.example.apigatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

public class FilterConfig {

    Environment env;

    public FilterConfig(Environment env) {
        this.env = env;
    }
}
