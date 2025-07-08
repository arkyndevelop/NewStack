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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Permite acesso público a estas rotas
                        .requestMatchers("/", "/v1/login", "/auth/**","/CSS/**", "/JS/**", "/img/**")
                        .permitAll()

                        // Permite que usuários anônimos e autenticados acessem o registro de clientes
                        .requestMatchers("/v1/clients/register", "/clients/register").permitAll()

                        // Regras de acesso para o ADMIN
                        .requestMatchers("/v1/home/admin", "/admin/**").hasRole("ADMIN")

                        // Regras de acesso para o CLIENT
                        .requestMatchers("/v1/home/client", "/v1/clients/profile").hasRole("CLIENT")

                        // Regras de acesso para funcionários
                        .requestMatchers("/v1/home/employee", "/employee/**").hasAnyRole("LIBRARIAN", "LIBRARY_ASSISTANT", "RECEPTIONIST", "EMPLOYEE")

                        // Regra para ADMIN gerenciar livros, clientes e funcionários
                        .requestMatchers("/books/**", "/v1/clients/**", "/v1/employees/**").hasAnyRole("ADMIN", "LIBRARIAN", "LIBRARY_ASSISTANT", "RECEPTIONIST", "EMPLOYEE")

                        .requestMatchers("/v1/clients/profile", "/v1/loans/**").hasAnyRole("CLIENT", "ADMIN", "LIBRARIAN", "LIBRARY_ASSISTANT", "RECEPTIONIST", "EMPLOYEE")

                        // Permite acesso ao Swagger
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**" ).permitAll()

                        // Qualquer outra requisição precisa de autenticação
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    Utilizar esse metodo somente para testar Swagger
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest()
//                        .permitAll())
//                .csrf(AbstractHttpConfigurer::disable);
//        return http.build();
//    }

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
