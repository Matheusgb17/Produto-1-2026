package br.ifmg.projeto1_2026.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI getApiConfig(){
        return new OpenAPI().info(
                new Info()
                        .title("IFMG Produto API")
                        .description("Essa API vai fornecer dados para o front-end do sistema de vendas da lojinha do grêmio do IFMG.")
                        .summary("Loja do Grêmio IFMG")
                        .version("")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://gremio.ifmg.edu.br")
                        )
        );

    }
}
