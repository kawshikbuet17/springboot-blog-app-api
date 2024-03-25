package com.kawshikbuet17.blog.config;

import com.kawshikbuet17.blog.security.CustomUserDetailService;
import com.kawshikbuet17.blog.security.JwtAuthenticationEntryPoint;
import com.kawshikbuet17.blog.security.JwtAuthenticationFilter;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable())
                .cors(cors->cors.disable())
                .authorizeHttpRequests(
                        auth->
                                auth.requestMatchers("/home/**").authenticated()
                                .requestMatchers("/api/v1/auth/login").permitAll()
                                .requestMatchers(HttpMethod.GET).permitAll()
                                .anyRequest().authenticated())
                .exceptionHandling(ex->ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception{
        return builder.getAuthenticationManager();
    }
}
