package projeto.infonews.dto;

import java.util.List;

public record NewsApiResponseDTO(
        String status,
        Integer totalResults,
        List<NewsArticleDTO> articles
) {

    public record NewsArticleDTO(
            SourceDTO source,
            String title,
            String description,
            String url,
            String publishedAt
    ) {}

    public record SourceDTO(
            String id,
            String name
    ) {}
}