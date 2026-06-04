package projeto.infonews.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import projeto.infonews.dto.NewsApiResponseDTO;

import java.util.Optional;

@Service
public class NewsService {

    private final WebClient webClient;

    @Value("${NEWS_API_KEY}")
    private String apiKey;

    public NewsService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://newsapi.org/v2/").build();
    }

    public NewsApiResponseDTO buscarNoticias(String query, String fontes, String language) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("everything")
                        .queryParam("q", query)
                        .queryParamIfPresent("sources", Optional.ofNullable(fontes))
                        .queryParamIfPresent("language", Optional.ofNullable(language))
                        .queryParam("apiKey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(NewsApiResponseDTO.class)
                .block();
    }
}