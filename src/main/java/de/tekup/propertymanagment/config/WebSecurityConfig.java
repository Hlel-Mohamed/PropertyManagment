package de.tekup.propertymanagment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((requests)->requests
                        //Allow access to swagger documentation
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        //Allow access to Property, Owner and RentalContract endpoints
                        .requestMatchers("/api/property/**").permitAll()
                        .requestMatchers("/api/owner/**").permitAll()
                        .requestMatchers("/api/contract/**").permitAll()
                        //All other requests must be authenticated
                        .anyRequest().authenticated()
                )
                //Disable CSRF protection
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
