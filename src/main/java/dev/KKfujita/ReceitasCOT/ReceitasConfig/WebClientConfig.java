package dev.KKfujita.ReceitasCOT.ReceitasConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${chatgpt.api.url:https://api.openai.com/v1/chat/completions}")
    private String chatGPTrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder() // Chama o builder diretamente aqui
                .baseUrl("https://openai.com")
                .build();
    }
}
