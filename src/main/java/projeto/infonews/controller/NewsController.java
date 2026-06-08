package projeto.infonews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.infonews.dto.NewsApiResponseDTO;
import projeto.infonews.service.HistoricoService;
import projeto.infonews.service.NewsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private HistoricoService historicoService;

    @GetMapping("/destaques")
    public ResponseEntity<NewsApiResponseDTO> destaques(
            @RequestParam(required = false) String idioma,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String category) {

        String valorRecebido = (idioma != null) ? idioma : country;
        String codigoPais = mapearIdiomaParaPais(valorRecebido);

        return ResponseEntity.ok(newsService.buscarDestaques(codigoPais, category));
    }

    @GetMapping("/buscar")
    public ResponseEntity<NewsApiResponseDTO> buscar(
            @RequestParam String q,
            @RequestParam(required = false) String fontes,
            @RequestParam(required = false) String language,
            @RequestHeader Long usuarioId) {

        historicoService.registrarBusca(q, usuarioId);

        return ResponseEntity.ok(newsService.buscarNoticias(q, fontes, language));
    }

    private String mapearIdiomaParaPais(String idioma) {
        if (idioma == null) return "br";

        return switch (idioma.toLowerCase()) {
            case "en" -> "us";
            case "es" -> "mx";
            case "pt" -> "br";
            case "zh" -> "cn";
            case "ar" -> "ae";
            default -> idioma;
        };
    }
}