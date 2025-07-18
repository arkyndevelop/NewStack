package com.examplenewstack.newstack.config.swag;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Biblioteca")
                        .version("1.0")
                        .description("Documentação da API de cadastro e gerenciamento de livros"));
    }
}
