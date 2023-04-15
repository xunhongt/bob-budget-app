package com.in6225.assignment.budgetapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
    			.requestMatchers(
    					HttpMethod.GET, 
    					"/categories", 
    					"/categories/**").hasAnyAuthority(ADMIN, USER)
                .requestMatchers(
                		"/budget", 
                		"/budget/**", 
                		"/budgets", 
                		"/budgets/**", 
                		"/budgetcount/", 
                		"/budgetcount/**").hasAnyAuthority(ADMIN, USER)
                .requestMatchers(
                		HttpMethod.GET,
                		"/users", 
                		"/users/**",
                		"/allbudgets").hasAuthority(ADMIN)
                .requestMatchers(
                		HttpMethod.POST,
                		"/categories").hasAuthority(ADMIN)
                .requestMatchers(
                		HttpMethod.PUT,
                		"/categories/**",
                		"/users/**").hasAuthority(ADMIN)
                .requestMatchers(
                		HttpMethod.DELETE,
                		"/categories/**", 
                		"/users/**").hasAuthority(ADMIN)
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/", "/error", "/csrf").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic(withDefaults());
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.cors(withDefaults()).csrf().disable();
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
}