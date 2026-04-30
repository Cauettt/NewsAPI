package projeto.infonews.dto;

public record FavoritosDTO(
        Long id,
        String titulo,
        String descricao,
        String url,
        String fonteNome
) {}