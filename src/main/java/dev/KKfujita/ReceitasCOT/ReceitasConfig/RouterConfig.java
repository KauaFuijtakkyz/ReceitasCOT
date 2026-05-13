package dev.KKfujita.ReceitasCOT.ReceitasConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> htmlRouter() {
        // Redireciona de forma reativa a rota raiz '/' para o arquivo static/index.html
        return RouterFunctions.route()
                .resources("/**", new ClassPathResource("static/"))
                .build();
    }
}
