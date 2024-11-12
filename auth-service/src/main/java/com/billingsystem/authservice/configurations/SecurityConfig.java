package com.billingsystem.authservice.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.zalando.problem.jackson.ProblemModule;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

@Configuration
@EnableWebSecurity
@Import(SecurityProblemSupport.class)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    private final SecurityProblemSupport problemSupport;
//
//    @Autowired
//    public SecurityConfig(@Lazy SecurityProblemSupport problemSupport){
//        this.problemSupport = problemSupport;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
//                        .requestMatchers("/auth/").permitAll()
//                        .anyRequest().authenticated())
//                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(problemSupport)
//                        .accessDeniedHandler(problemSupport))
//                .formLogin(Customizer.withDefaults()).build();
//    }
//
//    @Bean
//    public ObjectMapper objectMapper() {
//        return new ObjectMapper().registerModules(
//                new ProblemModule(),
//                new ConstraintViolationProblemModule());
//    }
}
