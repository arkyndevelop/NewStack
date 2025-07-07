package com.examplenewstack.newstack.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http
//                .csrf(csrf -> csrf.disable())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/", "/clients/register", "/employees/register","/login", "/auth/**","/CSS/*", "/JS/*", "/img/*")
//                            .permitAll()
//
//                        .requestMatchers("/v1/home/admin").hasRole("ADMIN")
//                        //.requestMatchers("/v1/home/employee").hasRole("EMPLOYEE")
//                        .requestMatchers("/v1/home/client").hasRole("CLIENT")
//                        .requestMatchers("/v1/home/**").authenticated()
//
//                        .requestMatchers("/client/**").hasRole("CLIENT")
//                        .requestMatchers("/books/**").hasRole("EMPLOYEE")
//                        .requestMatchers("/admin/**", "/books/**").hasRole("ADMIN")
//
//                        .requestMatchers("/swagger-ui/", "//v3/api-docs/" ).permitAll()
//                        .anyRequest().authenticated()
//
//                )
//                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }


    // Utilizar esse metodo somente para testar Swagger
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest()
                        .permitAll())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/swagger-ui/**", "/v3/api-docs/**");
    }
}