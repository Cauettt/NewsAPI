package projeto.infonews.dto;

import java.util.List;

public record NewsSourcesResponseDTO(
        String status,
        List<NewsSourceDTO> sources
) {
    public record NewsSourceDTO(
            String id,
            String name,
            String description,
            String category,
            String language,
            String country
    ) {}
}
