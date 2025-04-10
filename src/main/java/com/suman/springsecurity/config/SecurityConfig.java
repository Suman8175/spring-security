package com.suman.springsecurity.config;

import com.suman.springsecurity.config.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetails customUserDetails;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(request->request.requestMatchers(HttpMethod.POST,"/api/v1/users").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/users/login").permitAll()
                        .anyRequest().authenticated())
                .csrf(csrf ->csrf.disable())
                .httpBasic(Customizer.withDefaults());

    return httpSecurity.build();
    }
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetails);
        return provider;
    }




}
