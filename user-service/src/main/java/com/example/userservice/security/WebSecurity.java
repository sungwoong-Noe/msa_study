package com.example.userservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

@Configuration
public class WebSecurity{


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http



                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests.requestMatchers("/users/**", "/h2-console/**", "/health_check/**", "/user-service/**").permitAll()

                )


                .headers(headersConfigurer ->
                        headersConfigurer
                                .frameOptions(
                                        HeadersConfigurer.FrameOptionsConfig::sameOrigin
                                )
                )



        ;
        return http.build();
    }

}
