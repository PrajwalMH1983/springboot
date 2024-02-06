package com.learnJPA.OAuthresourceserver.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ResourceServerConfig {

    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .mvcMatcher("/api/**")
//                .authorizeHttpRequests()
//                .mvcMatchers("/api/**")
//                .access("hasAuthority('SCOPE_api.read')")
//                .and()
//                .oauth2ResourceServer()
//                .jwt();

        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .mvcMatchers("/api/**")
                                .access("hasAuthority('SCOPE_api.read')")
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}
