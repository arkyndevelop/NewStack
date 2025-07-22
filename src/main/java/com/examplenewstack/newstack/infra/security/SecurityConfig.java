package com.examplenewstack.newstack.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Futuramente é interessante habilitarmos o CSRF para mantermos um site
                // mais seguro e protegido para os usuários!
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Permite acesso público a estas rotas
                        .requestMatchers(
                                "/",
                                "/v1/login",
                                "/auth/**",
                                "/CSS/**",
                                "/JS/**",
                                "/img/**"
                        ).permitAll()

                        // Permite que usuários anônimos e autenticados acessem o registro de clientes
                        .requestMatchers(
                                "/v1/clients/register",
                                "/clients/register"
                        ).permitAll()

                        // Regras de acesso para administradores
                        .requestMatchers(
                                "/v1/home/admin",
                                "/admin/**",
                                "/v1/admins/register"
                        ).hasRole("ADMIN")

                        // Regras de acesso para clientes
                        .requestMatchers(
                                "/v1/home/client",
                                "/v1/clients/profile"
                                //"/v1/loans/register"
                        ).hasRole("CLIENT")

                        // Regras de acesso para funcionários
                        .requestMatchers(
                                "/v1/home/employee",
                                "/employee/**"
                        ).hasAnyRole("LIBRARIAN", "LIBRARY_ASSISTANT", "RECEPTIONIST", "EMPLOYEE")

                        // Regra para Admin gerenciar livros, clientes e funcionários
                        .requestMatchers(
                                "/v1/clients/**",
                                "/v1/employees/**"
                        ).hasAnyRole("ADMIN", "LIBRARIAN", "LIBRARY_ASSISTANT", "RECEPTIONIST", "EMPLOYEE")

                        // Regra de acesso para que todos possam acessar essas páginas, porém é feito,
                        // na regra de negócio, a filtragem para acessar funcionalidades dependendo da ROLE!
                        .requestMatchers(
                                "/v1/clients/profile",
                                "/v1/loans/**",
                                "/v1/books/reports"
                        ).hasAnyRole("CLIENT", "ADMIN", "LIBRARIAN", "LIBRARY_ASSISTANT", "RECEPTIONIST", "EMPLOYEE")

                        // Permite acesso ao Swagger, somente em ambientes de teste!
                        // .requestMatchers("/swagger-ui/**", "/v3/api-docs/**" ).permitAll()

                        // Qualquer outra requisição precisa de autenticação
                        .anyRequest().authenticated()
                )

                // Configura o formulário de login padrão do Spring Security para as PÁGINAS WEB
                .formLogin(form -> form
                        .loginPage("/v1/login") // Aponta para sua página de login customizada
                        .defaultSuccessUrl("/v1/home", true) // Redireciona para a home após o sucesso
                        .permitAll() // Permite que todos acessem a URL de processamento de login
                )
                // Configura o logout
                .logout(logout -> logout
                        .logoutUrl("/v1/logout")
                        .logoutSuccessUrl("/v1/login?logout") // Volta para a página de login com uma mensagem
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                // Adiciona o filtro JWT para proteger a API, sem interferir na navegação web
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
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
