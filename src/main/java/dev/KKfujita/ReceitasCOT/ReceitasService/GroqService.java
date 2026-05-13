package dev.KKfujita.ReceitasCOT.ReceitasService;

import dev.KKfujita.ReceitasCOT.ReceitasModel.FoodItemModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GroqService {

    private final WebClient webClient;
    // Mude o nome da variável de ambiente no seu IntelliJ para GROQ_API_KEY
    private final String apiKey = System.getenv("GROQ_API_KEY");

    public GroqService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateRecipe(List<FoodItemModel> foodItemModels) {

        String alimentos = foodItemModels.stream()
                .map(item -> String.format("%s (%s) - Quantidade: %d, Validade: %s",
                        item.getNome(),item.getCategorias(), item.getQuantidade(), item.getValidade(),item.getCategorias()))
                .collect(Collectors.joining("/n"));


        String prompt = "Baseado no meu banco de dados faça uma receita com os seguintes itens:/n" + alimentos;

        Map<String, Object> requestBody = Map.of(
                "model", "llama-3.3-70b-versatile", // Modelo gratuito e muito potente
                "messages", List.of(
                        Map.of("role", "system", "content", "Você é um assistente que cria receitas."),
                        Map.of("role", "user", "content", prompt)
                )
        );

        return webClient.post()
                // MUDANÇA PARA O ENDPOINT DO GROQ (Totalmente compatível)
                .uri("https://api.groq.com/openai/v1/chat/completions")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    // A extração do JSON é exatamente igual à da OpenAI!
                    var choices = (List<Map<String, Object>>) response.get("choices");
                    var message = (Map<String, Object>) choices.get(0).get("message");
                    return (String) message.get("content");
                });
    }
}

