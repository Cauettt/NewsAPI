package projeto.infonews.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import projeto.infonews.dto.NewsSourcesResponseDTO;

import java.util.Optional;

@Service
public class SourcesService {

    private final WebClient webClient;

    @Value("${NEWS_API_KEY}")
    private String apiKey;

    public SourcesService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://newsapi.org/v2/").build();
    }

    public NewsSourcesResponseDTO buscarFontes(String language, String category, String country) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("sources")
                        .queryParamIfPresent("language", Optional.ofNullable(language))
                        .queryParamIfPresent("category", Optional.ofNullable(category))
                        .queryParamIfPresent("country", Optional.ofNullable(country))
                        .queryParam("apiKey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(NewsSourcesResponseDTO.class)
                .block();
    }
}
