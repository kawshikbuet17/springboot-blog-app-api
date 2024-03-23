package com.kawshikbuet17.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((c)->c.disable())
                .authorizeRequests(requests ->
                        requests
                                .requestMatchers("/api/posts**").permitAll() // Permit access to api/posts resources
                                .anyRequest().authenticated() // Require authentication for all other requests
                )
                .formLogin(form -> form
                        .permitAll() // Allow anyone to access the login page
                );

        return http.build();
    }
}
