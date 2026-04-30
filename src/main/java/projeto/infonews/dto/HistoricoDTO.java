package projeto.infonews.dto;

import java.time.LocalDateTime;

public record HistoricoDTO(
        Long id,
        String termoBusca,
        LocalDateTime dataConsulta
) {}