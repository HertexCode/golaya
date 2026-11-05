package com.devPortes.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final MySecurityFilter mySecurityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/api/v1/field/new").hasRole("ADMIN")
                        .requestMatchers("/api/v1/field/edit/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/field/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/field/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/location/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/location/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/location/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/location/new-location").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/field/all").permitAll()
                        .requestMatchers("/api/v1/location/locations").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/statistics/public").permitAll()
                        .requestMatchers("/api/v1/auth/register").permitAll()
                        .requestMatchers("/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/post/all").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/post/new").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/post/edit/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/post/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/admin/clients").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/reservations/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/payment-method/reservations/final-payment").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())
                .addFilterBefore(mySecurityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(@Value("${app.cors.allowed-origins}") String allowedOrigin) {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList(allowedOrigin.split(",")));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
