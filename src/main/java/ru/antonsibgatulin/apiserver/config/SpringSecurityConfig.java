package ru.antonsibgatulin.apiserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {
    private final JwtAuthencationFilter jwtAuthencationFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests().requestMatchers("/api/v1/auth/**","/api/v1/reg/**").permitAll().anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authenticationProvider(authenticationProvider).addFilterBefore(jwtAuthencationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}