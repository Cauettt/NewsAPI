package projeto.infonews.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import projeto.infonews.dto.NewsApiResponseDTO;

@Service
public class NewsService {

    private final WebClient webClient;

    @Value("${api.news.key}")
    private String apiKey;

    public NewsService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://newsapi.org/v2/").build();
    }

    public NewsApiResponseDTO buscarNoticias(String query, String fontes) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("everything")
                        .queryParam("q", query)
                        .queryParam("sources", fontes)
                        .queryParam("apiKey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(NewsApiResponseDTO.class)
                .block();
    }
}