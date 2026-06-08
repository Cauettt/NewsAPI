package projeto.infonews.dto;

import java.util.List;

public record MonitoramentoFeedItemDTO(
        Long monitoramentoId,
        String nome,
        String tipo,
        List<NewsApiResponseDTO.NewsArticleDTO> artigos
) {}
